package org.example.server;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/welcome"},
            initParams = {
        @WebInitParam(name = "encoding",
                value = "UTF-8",
                description = "Encoding Param") })

public class FilterAuthServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();
        Cookie user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                user = cookie;
            }
        }
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/registration");
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
