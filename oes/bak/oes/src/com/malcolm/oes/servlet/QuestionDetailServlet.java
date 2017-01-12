package com.malcolm.oes.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.service.QuestionService;

public class QuestionDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String QUESTION_DETAIL_PAGE = "/WEB-INF/jsp/question_detail.jsp";

    public QuestionDetailServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        QuestionService questionService = new QuestionService();
        try {
            Question question = questionService.getQuestionById(Integer.parseInt(id));
            request.setAttribute("question", question);
            request.getRequestDispatcher(QUESTION_DETAIL_PAGE).forward(request, response);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.setAttribute(Constants.ERROR_FIELDS, errorFields);
            request.getRequestDispatcher(QUESTION_DETAIL_PAGE).forward(request, response);
            return ;
        } catch (ServiceException serviceException) {
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            request.getRequestDispatcher(QUESTION_DETAIL_PAGE).forward(request, response);
            return ;
        }

    }

}
