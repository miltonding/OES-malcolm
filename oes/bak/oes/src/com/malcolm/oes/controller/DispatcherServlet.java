package com.malcolm.oes.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.malcolm.oes.common.ActionConfig;
import com.malcolm.oes.common.ModelAndView;
import com.malcolm.oes.util.StringUtil;

public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Map<String, ActionConfig> actionConfigs = new HashMap<String, ActionConfig>();

    public DispatcherServlet() {
        super();
        actionConfigs.put("login.action", new ActionConfig("com.malcolm.oes.controller.UserController", "login"));
        actionConfigs.put("logout.action", new ActionConfig("com.malcolm.oes.controller.UserController", "logout"));
        actionConfigs.put("myoes.action", new ActionConfig("com.malcolm.oes.controller.DashBoardController", "dashBoard"));
        actionConfigs.put("createQuestion.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "createQuestion"));
        actionConfigs.put("questionDetail.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "questionDetail"));
        actionConfigs.put("saveQuestion.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "saveQuestion"));
        actionConfigs.put("searchQuestion.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "searchQuestion"));
        actionConfigs.put("deleteQuestionByIds.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "deleteQuestionByIds"));
        actionConfigs.put("editQuestion.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "editQuestion"));
        actionConfigs.put("editQuestionToSave.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "editQuestionToSave"));
        actionConfigs.put("questionDetailDelete.action", new ActionConfig("com.malcolm.oes.controller.QuestionController", "questionDetailDelete"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        String requestUrl = url.substring(request.getContextPath().length() + 1);
        request.setAttribute("projectPath", request.getContextPath());
        if (StringUtil.isEmpty(requestUrl)) {
            requestUrl = "login.action";
        }
        ActionConfig actionConfig = actionConfigs.get(requestUrl);
        if (actionConfig != null) {
            String className = actionConfig.getClassName();
            try {
                Class<?>[] params = new Class[2];
                params[0] = Map.class;
                params[1] = Map.class;

                Class<?> cls = Class.forName(className);
                Object controller = cls.newInstance();
                String methodName = actionConfig.getMethodName();
                Method method = cls.getMethod(methodName, params);


                Map<String, Object> sessionMap = new HashMap<String, Object>();
                Enumeration<String> toSessionKeys = session.getAttributeNames();
                while (toSessionKeys.hasMoreElements()) {
                    String toKey = toSessionKeys.nextElement();
                    sessionMap.put(toKey, session.getAttribute(toKey));
                }

                Map<String, Object> parameterMap = new HashMap<String, Object>();
                Enumeration<String> toRequestKeys = request.getParameterNames();
                while (toRequestKeys.hasMoreElements()) {
                    String toKey = toRequestKeys.nextElement();
                    parameterMap.put(toKey, request.getParameter(toKey));
                }


                Object[] objects = new Object[2];
                objects[0] = parameterMap;
                objects[1] = sessionMap;

                ModelAndView modelAndView = (ModelAndView) method.invoke(controller, objects);

                Map<String, Object> fromControllerSession = modelAndView.getSessions();
                Set<String> keySessions = fromControllerSession.keySet();
                for (String key : keySessions) {
                    session.setAttribute(key, fromControllerSession.get(key));
                }

                Map<String, Object> fromControllerRequest = modelAndView.getRequests();
                Set<String> keyRequests = fromControllerRequest.keySet();
                for (String key : keyRequests) {
                    request.setAttribute(key, fromControllerRequest.get(key));
                }


                System.out.println(session.getAttribute(Constants.USER));
                String view = modelAndView.getView();
                if (modelAndView.isRedirect()) {
                    response.sendRedirect(view);
                }else{
                    request.getRequestDispatcher(view).forward(request, response);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendError(404);
        }*/
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        String requestUrl = url.substring(request.getContextPath().length() + 1);
        request.setAttribute("projectPath", request.getContextPath());
        if (StringUtil.isEmpty(requestUrl)) {
            requestUrl = "login.action";
        }
        ActionConfig actionConfig = actionConfigs.get(requestUrl);
        if (actionConfig != null) {
            String className = actionConfig.getClassName();
            try {
                Class<?>[] params = new Class[2];
                params[0] = Map.class;
                params[1] = Map.class;

                Class<?> cls = Class.forName(className);
                Object controller = cls.newInstance();
                String methodName = actionConfig.getMethodName();
                Method method = cls.getMethod(methodName, params);


                Map<String, Object> sessionMap = new HashMap<String, Object>();
                Enumeration<String> toSessionKeys = session.getAttributeNames();
                while (toSessionKeys.hasMoreElements()) {
                    String toKey = toSessionKeys.nextElement();
                    sessionMap.put(toKey, session.getAttribute(toKey));
                }

                Map<String, Object> parameterMap = new HashMap<String, Object>();
                Enumeration<String> toRequestKeys = request.getParameterNames();
                while (toRequestKeys.hasMoreElements()) {
                    String toKey = toRequestKeys.nextElement();
                    String []paramList = request.getParameterValues(toKey);
                    if (paramList.length == 1) {
                        parameterMap.put(toKey, request.getParameter(toKey));
                    } else {
                        parameterMap.put(toKey, request.getParameterValues(toKey));
                    }
                }
                toRequestKeys = request.getAttributeNames();
                while (toRequestKeys.hasMoreElements()) {
                    String toKey = toRequestKeys.nextElement();
                    parameterMap.put(toKey, request.getAttribute(toKey));
                }


                Object[] objects = new Object[2];
                objects[0] = parameterMap;
                objects[1] = sessionMap;

                ModelAndView modelAndView = (ModelAndView) method.invoke(controller, objects);

                Map<String, Object> fromControllerSession = modelAndView.getSessions();
                Set<String> keySessions = fromControllerSession.keySet();
                for (String key : keySessions) {
                    session.setAttribute(key, fromControllerSession.get(key));
                }

                Map<String, Object> fromControllerRequest = modelAndView.getRequests();
                Set<String> keyRequests = fromControllerRequest.keySet();
                for (String key : keyRequests) {
                    request.setAttribute(key, fromControllerRequest.get(key));
                }

                //System.out.println(session.getAttribute(Constants.USER));
                String view = modelAndView.getView();
                if (modelAndView.isRedirect()) {
                    response.sendRedirect(view);
                }else{
                    request.getRequestDispatcher(view).forward(request, response);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendError(404);
        }
    }

}
