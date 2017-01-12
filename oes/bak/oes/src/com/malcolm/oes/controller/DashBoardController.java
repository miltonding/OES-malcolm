package com.malcolm.oes.controller;

import java.util.List;
import java.util.Map;

import com.malcolm.oes.Constants;
import com.malcolm.oes.common.ModelAndView;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.PageBean;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.PropertyUtil;
import com.malcolm.oes.util.StringUtil;

public class DashBoardController {
    private static String QUESTION_MANAGEMENT_PAGE = "/WEB-INF/jsp/question_management.jsp";

    public ModelAndView dashBoard(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        QuestionService questionService = new QuestionService();
        String page = (String) request.get("page");
        String getpageSize = (String) request.get("pageSize");
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        if (StringUtil.isEmpty(getpageSize)) {
            getpageSize = PropertyUtil.getProperty("pageBean.pageSize");
        }
        int nowPage = Integer.parseInt(page);
        int pageSize = Integer.parseInt(getpageSize);
        try {
            PageBean pageBean = new PageBean();
            pageBean.setNowPage(nowPage);
            pageBean.setPageSize(pageSize);
            List<Question> questionList = questionService.findQuestionList(pageBean);
            modelAndView.addObject("pageBean", pageBean);
            modelAndView.addObject("questionList", questionList);
            modelAndView.addObject("pageSize", getpageSize);
            modelAndView.setView(QUESTION_MANAGEMENT_PAGE);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView(QUESTION_MANAGEMENT_PAGE);
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView(QUESTION_MANAGEMENT_PAGE);
            return modelAndView;
        }
        return modelAndView;
    }
}
