package com.malcolm.oes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.malcolm.oes.Constants;
import com.malcolm.oes.model.User;

public class SessionFilter implements Filter {

    private String notNeedLoginPages;
    private FilterConfig filterConfig;

    public SessionFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String requestUrl = url.substring(request.getContextPath().length() + 1);
        String [] pages = notNeedLoginPages.split(",");
        boolean isAllow = false;
        for (String page: pages) {
            if (page.equals(requestUrl)) {
                isAllow = true;
                break;
            }
        }

        if (isAllow) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.action");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
        if (filterConfig.getInitParameter("notNeedLoginPages") != null) {
            notNeedLoginPages = fConfig.getInitParameter("notNeedLoginPages");
        }
    }

}
