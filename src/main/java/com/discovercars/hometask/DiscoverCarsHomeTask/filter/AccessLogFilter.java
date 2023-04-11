package com.discovercars.hometask.DiscoverCarsHomeTask.filter;

import com.discovercars.hometask.DiscoverCarsHomeTask.model.AccessLog;
import com.discovercars.hometask.DiscoverCarsHomeTask.repository.AccessLogRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;

public class AccessLogFilter implements Filter {

    private final AccessLogRepository accessLogRepository;

    public AccessLogFilter(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        AccessLog accessLog = new AccessLog();
        accessLog.setRequestTimestamp(LocalDateTime.now());
        accessLog.setIpAddress(request.getRemoteAddr());
        accessLog.setMethod(httpRequest.getMethod());
        accessLog.setPath(httpRequest.getRequestURI());
        accessLog.setUserAgent(httpRequest.getHeader("User-Agent"));

        accessLogRepository.save(accessLog);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
