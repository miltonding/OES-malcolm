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

public class PageEncodingFilter implements Filter {

    private static final String ENCODING = "encoding";

    private String encoding = "UTF-8";
    private FilterConfig filterConfig;

    public void destroy() {
        this.encoding = null;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
        response.setCharacterEncoding(encoding);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (null == filterConfig.getInitParameter(ENCODING)) {
            encoding = this.filterConfig.getInitParameter(ENCODING);
        }
    }

}
