package com.jyj.api.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
            chain.doFilter(wrappedRequest, wrappedResponse);

            logMessage(wrappedRequest, wrappedResponse);

        } finally {
            wrappedResponse.copyBodyToResponse();
            MDC.remove("threadId");
        }
    }

    private static void logMessage(ContentCachingRequestWrapper wrappedRequest, ContentCachingResponseWrapper wrappedResponse) {

        String threadId = UUID.randomUUID().toString();
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("HTTP ACCESS LOG FILTER:\n");
        logMessage.append("threadId: ").append(MDC.get("threadId")).append("\n");
        logMessage.append("method: ").append(wrappedRequest.getMethod()).append("\n");
        logMessage.append("url: ").append(wrappedRequest.getRequestURI()).append("\n");
        logMessage.append("userAgent: ").append(wrappedRequest.getHeader("User-Agent")).append("\n");
        logMessage.append("host: ").append(wrappedRequest.getHeader("host")).append("\n");
        logMessage.append("clientIp: ").append(wrappedRequest.getRemoteAddr()).append("\n");

        byte[] requestBodyBytes = wrappedRequest.getContentAsByteArray();
        String requestBody = new String(requestBodyBytes, StandardCharsets.UTF_8);
        logMessage.append("requestParams: ").append(requestBody).append("\n");

        byte[] responseBodyBytes = wrappedResponse.getContentAsByteArray();
        String responseBody = new String(responseBodyBytes, StandardCharsets.UTF_8);
        logMessage.append("responseParams: ").append(responseBody).append("\n");

        log.info(logMessage.toString());
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
