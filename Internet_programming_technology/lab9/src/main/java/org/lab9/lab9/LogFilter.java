package org.lab9.lab9;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.time.LocalTime;

public class LogFilter implements Filter {
    static {
        new DOMConfigurator().doConfigure("D:\\study\\4_sem\\JAVA\\lab9\\src\\main\\java\\org\\lab9\\lab9\\log\\log4j.xml", LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String servletPath = httpRequest.getServletPath();
        String method = httpRequest.getMethod();
        String timeStamp = LocalTime.now().toString();
        String remoteAddress = servletRequest.getRemoteAddr();

        LOG.info("Log: " +servletPath + method + timeStamp + remoteAddress);

        chain.doFilter(servletRequest,servletResponse);
    }
}