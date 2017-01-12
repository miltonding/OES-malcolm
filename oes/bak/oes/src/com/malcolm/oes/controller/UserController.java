package com.malcolm.oes.controller;

import java.util.Map;

import com.malcolm.oes.Constants;
import com.malcolm.oes.common.ModelAndView;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.UserService;

public class UserController {

    private static String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    public ModelAndView login(Map<String, Object> request, Map<String, Object> session) {
        String userName = (String) request.get("userName");
        String password = (String) request.get("password");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setSessions(session);
        try {
            User user;
            UserService userService = new UserService();
            user = userService.Login(userName, password);
            // For safety, we should make the user's password be null.
            user.setPassword(null);

            session.put(Constants.USER, user);

            modelAndView.setRedirect(true);
            // This will solve the problem of duplicate submission of the form
            modelAndView.setView((String) request.get("projectPath") + "/myoes.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.put(Constants.ERROR_FIELDS, errorFields);
            request.put("userName", userName);
            request.put("password", password);
            modelAndView.setView(LOGIN_PAGE);
            return modelAndView;
        } catch (ServiceException serviceException) {
            request.put("userName", userName);
            request.put("password", password);
            request.put(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView(LOGIN_PAGE);
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView logout(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.removeSessionAttribute(Constants.USER);
        // The top code replace the behind code.
        // session.remove(Constants.USER);

        modelAndView.setRedirect(true);
        modelAndView.setView((String) request.get("projectPath"));
        return modelAndView;
    }
}
