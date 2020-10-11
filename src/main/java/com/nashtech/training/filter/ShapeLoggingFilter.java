package com.nashtech.training.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@Order(1)
public class ShapeLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing my logging filter");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        log.info("The logging Filter BEGIN!");
        HttpServletRequest httprequest = (HttpServletRequest) request;
        log.info("Request method {}, authtype: {}, mikes header: {}",
                httprequest.getMethod(),
                httprequest.getAuthType(),
                httprequest.getHeader("MIKES_HEADER")
        );
        log.info("The logging Filter END!");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("Destroying my logging filter");
    }
}
