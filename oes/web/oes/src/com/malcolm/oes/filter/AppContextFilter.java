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

import com.malcolm.oes.AppContext;
import com.malcolm.oes.Constants;
import com.malcolm.oes.model.User;

public class AppContextFilter implements Filter {

    public AppContextFilter() {}

    public void destroy() {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        AppContext appContext = AppContext.getContext();
        AppContext.setContextPath(request.getContextPath());

        appContext.addObject(Constants.APP_CONTEXT_REQUEST, request);
        appContext.addObject(Constants.APP_CONTEXT_RESPONSE, response);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        appContext.addObject(Constants.APP_CONTEXT_USER, user);

        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);

        try {
            filterChain.doFilter(request, response);
        } catch (IOException ioException) {
            throw ioException;
        } catch (ServletException servletException) {
            throw servletException;
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        } finally {
            appContext.clear();
        }

    }

    public void init(FilterConfig fConfig) throws ServletException {}

}
