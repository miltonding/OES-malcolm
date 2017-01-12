package com.malcolm.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.PageBean;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.StringUtil;

public class DashBoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String QUESTION_MANAGEMENT_PAGE = "/WEB-INF/jsp/question_management.jsp";

    public DashBoardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        QuestionService questionService = new QuestionService();
        String page = request.getParameter("page");
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        int nowPage = Integer.parseInt(page);
        try {
            PageBean pageBean = new PageBean();
            pageBean.setNowPage(nowPage);
            List<Question> questionList = questionService.findQuestionList(pageBean);
            request.setAttribute("pageBean", pageBean);
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher(QUESTION_MANAGEMENT_PAGE).forward(request, response);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.setAttribute(Constants.ERROR_FIELDS, errorFields);
            request.getRequestDispatcher(QUESTION_MANAGEMENT_PAGE).forward(request, response);
            return;
        } catch (ServiceException serviceException) {
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            request.getRequestDispatcher(QUESTION_MANAGEMENT_PAGE).forward(request, response);
            return;
        }
    }
}
