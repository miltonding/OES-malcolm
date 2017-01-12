package com.malcolm.oes.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.UserService;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        try {
            User user;
            UserService userService = new UserService();
            user = userService.Login(userName, password);
            //For safety, we should make the user's password be null.
            user.setPassword(null);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.USER, user);

            //This will solve the problem of duplicate submission of the form
            response.sendRedirect(request.getContextPath() + "/myoes.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.setAttribute(Constants.ERROR_FIELDS, errorFields);
            request.setAttribute("userName", userName);
            request.setAttribute("password", password);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);;
            return;
        } catch (ServiceException serviceException) {
            request.setAttribute("userName", userName);
            request.setAttribute("password", password);
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
    }
}
