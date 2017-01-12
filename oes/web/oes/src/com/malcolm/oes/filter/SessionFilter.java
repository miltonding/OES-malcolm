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
import com.malcolm.oes.util.PathUtil;
import com.malcolm.oes.util.StringUtil;

public class SessionFilter implements Filter {

    private static final String NOT_NEED_LOGIN_PAGES = "notNeedLoginPages";

    private String notNeedLoginPages;
    private FilterConfig filterConfig;

    public SessionFilter() {}

    public void destroy() {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length() + 1);

        String[] pages = notNeedLoginPages.split(",");
        boolean isAllow = false;
        for (String page : pages) {
            if (page.equals(requestUri)) {
                isAllow = true;
                break;
            }
        }
        if (isAllow) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            if (null == user) {
                if (request.getMethod().toLowerCase().equals("get")) {
                    String queryString = request.getQueryString();
                    String go = requestUri;
                    if (!StringUtil.isEmpty(queryString)) {
                        go = go + "#" + queryString;
                    }
                    response.sendRedirect(PathUtil.getFullPath("user/login?go=" + go));
                } else {
                    response.sendRedirect(PathUtil.getFullPath("user/login"));
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
        if (null != filterConfig.getInitParameter(NOT_NEED_LOGIN_PAGES)) {
            notNeedLoginPages = fConfig.getInitParameter(NOT_NEED_LOGIN_PAGES);
        }
    }

}
