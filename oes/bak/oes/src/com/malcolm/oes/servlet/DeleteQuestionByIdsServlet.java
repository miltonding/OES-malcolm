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
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.StringUtil;

public class DeleteQuestionByIdsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteQuestionByIdsServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        String deleteIds [] = request.getParameterValues("questionCheckbox");
        HttpSession session = request.getSession();
        try {
            int count = questionService.deleteQuestionByIds(StringUtil.revertToInteger(deleteIds));
            if (count != 0) {
                session.setAttribute("question_flash_msg", "delete is ok!");
            }else{
                session.setAttribute("question_flash_msg", "delete is failure!");
            }
            response.sendRedirect(request.getContextPath() + "/myoes.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.setAttribute(Constants.ERROR_FIELDS, errorFields);
            request.getRequestDispatcher("/myoes.action").forward(request, response);
            return;
        } catch (ServiceException serviceException) {
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            request.getRequestDispatcher("/myoes.action").forward(request, response);
            return;
        }
    }
}
