package com.malcolm.oes.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.malcolm.oes.AppContext;
import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.UserService;
import com.malcolm.oes.util.StringUtil;

@Controller
@RequestMapping(Constants.APP_URL_USER_PREFIX)
public class UserController extends BaseController {

    private static final String LOGIN_JSP = "login";
    private static final String OES_PAGE = "dashBoard/myoes";

    private static final String LOG_HEAD = "path : " + Constants.APP_URL_DASHBOARD_PREFIX;

    private Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView goLogin(@RequestParam(value = "go", defaultValue = "") String go) {
        ModelAndView modelAndView = new ModelAndView();
        User user = this.getUser();
        if (null == user) {
            modelAndView.addObject("go", go);
            modelAndView.setViewName(LOGIN_JSP);
        } else {
            modelAndView.setView(this.getRedirectView("dashBoard/myoes"));
        }
        log.info(LOG_HEAD + "/login : Enter the login jsp.");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
                              @RequestParam(value = "userName") String userName,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "queryString", defaultValue = "") String queryString,
                               @RequestParam(value = "go", defaultValue = "") String go
                              ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.login(userName, password);
            // For safety, we should make the user's password be null.
            user.setPassword(null);

            if (!StringUtil.isEmpty(queryString)) {
                if (queryString.startsWith("#")) {
                    queryString = queryString.substring(1);
                }
                go = go + "?" + queryString;
            }
            this.addSession(Constants.USER, user);
            RedirectView redirectView = StringUtil.isEmpty(go) ? this.getRedirectView(OES_PAGE) : new RedirectView(AppContext.getContextPath() + "/" + go);
            modelAndView.setView(redirectView);
            log.info(LOG_HEAD + "/login : userName - " + userName + " :  Submit the login form.");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("password", password);
            modelAndView.setViewName(LOGIN_JSP);
            log.warn(LOG_HEAD + "/login : userName - " + userName + " : [ParameterException] : The parameter may be occured exception.");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("password", password);
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setViewName(LOGIN_JSP);
            log.error(LOG_HEAD + "/login : userName - " + userName + " : [ServiceException] : " + serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        log.info(LOG_HEAD + "/logout : user - " + this.getSession(Constants.USER) + " : Logout to the login jsp.");
        this.removeSession(Constants.USER);
        modelAndView.setViewName(LOGIN_JSP);
        return modelAndView;
    }
}
