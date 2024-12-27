package com.network.utility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtility implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtility.class);

    public LoggerUtility() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final String RECEIVED_REQUEST_FOR_API_URL = "Received request for API URL: ";
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Log the full API URL for tracking and debugging purposes
        try {
            logger.info(RECEIVED_REQUEST_FOR_API_URL + httpRequest.getRequestURL().toString());
            // Continue with the filter chain
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Error occurred while logging the request: ", e.getMessage());
        }
    }

}
