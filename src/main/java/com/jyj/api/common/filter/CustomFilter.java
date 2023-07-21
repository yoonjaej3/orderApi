package com.jyj.api.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class CustomFilter implements Filter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            log.info(">>>>>>>>>Filter Start>>>>>>>>>");

            String transid = UUID.randomUUID().toString();
            MDC.put("transid", transid);

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestURI = httpRequest.getRequestURI();
            MDC.put("requestURI", requestURI);

            log.info("transid : {}", MDC.get("transid"));
            log.info("requestURI : {}", MDC.get("requestURI"));

            chain.doFilter(request, response);
        } finally {
            log.info(">>>>>>>>>Filter End>>>>>>>>>");
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
