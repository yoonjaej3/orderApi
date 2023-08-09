package com.jyj.api.common.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        String threadId = Long.toString(Thread.currentThread().getId());
        MDC.put("threadId", threadId);

        try {
            long requestTime = Instant.now().toEpochMilli();
            chain.doFilter(wrappedRequest, wrappedResponse);
            long responseTime = Instant.now().toEpochMilli();

            logMessage(wrappedRequest, wrappedResponse, requestTime, responseTime);

        } finally {
            wrappedResponse.copyBodyToResponse();
            MDC.remove("threadId");
        }
    }

    private static void logMessage(ContentCachingRequestWrapper wrappedRequest, ContentCachingResponseWrapper wrappedResponse, long requestTime, long responseTime) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        String threadId = UUID.randomUUID().toString();
        String requestParams = objectMapper.writeValueAsString(wrappedRequest.getParameterMap());
        String responseParams = new String(wrappedResponse.getContentAsByteArray());

        ZonedDateTime requestDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(requestTime), ZoneId.systemDefault());
        ZonedDateTime responseDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(responseTime), ZoneId.systemDefault());

        String logMessage = "{\"threadId\": \"" + threadId + "\", " +
                "\"method\": \"" + wrappedRequest.getMethod() + "\", " +
                "\"url\": \"" + wrappedRequest.getRequestURI() + "\", " +
                "\"userAgent\": \"" + wrappedRequest.getHeader("User-Agent") + "\", " +
                "\"host\": \"" + wrappedRequest.getHeader("host") + "\", " +
                "\"clientIp\": \"" + wrappedRequest.getRemoteAddr() + "\", " +
                "\"requestParams\": " + requestParams + ", " +
                "\"responseParams\": " + responseParams + ", " +
                "\"requestAt\": \"" + requestDateTime.format(dateFormatter) + "\"," +
                "\"responseAt\": \"" + responseDateTime.format(dateFormatter) + "\"," +
                "\"elapsedTimeInMS\": " + (responseTime - requestTime) + "}";

        log.info(logMessage);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
