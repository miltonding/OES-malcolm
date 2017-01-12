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
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.StringUtil;

public class EditQuestionToSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditQuestionToSaveServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        String answer = StringUtil.HtmlEncode(request.getParameter("answer")).trim();
        String questionId = StringUtil.HtmlEncode(request.getParameter("questionId")).trim();
        String questionTitle = request.getParameter("questionTitle").trim();
        String answerA = StringUtil.HtmlEncode(request.getParameter("answerA")).trim();
        String answerB = StringUtil.HtmlEncode(request.getParameter("answerB")).trim();
        String answerC = StringUtil.HtmlEncode(request.getParameter("answerC")).trim();
        String answerD = StringUtil.HtmlEncode(request.getParameter("answerD")).trim();
        // I get the old id for this question model.
        String oldId = request.getParameter("oldId");
        int status = 1;
        Question question = new Question(questionTitle, questionId, answerA, answerB, answerC, answerD, answer, status);
        QuestionService questionService = new QuestionService();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        int result1 = 0;
        try {
            result1 = questionService.saveQuestion(question, user);
            // I update the old question's status to 0.
            int result2 = questionService.updateQuestionById(StringUtil.myParseInt(oldId));
            if (result1 != 0 && result2 == 1) {
                session.setAttribute("update_question_flash_msg", "update is ok!");
            } else {
                session.setAttribute("update_question_flash_msg", "update is failure!");
            }
            question.setId(result1);
            response.sendRedirect(request.getContextPath() + "/questionDetail.action?id=" + result1);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.setAttribute(Constants.ERROR_FIELDS, errorFields);
            request.getRequestDispatcher("/questionDetail.action?id=" + result1).forward(request, response);
            return;
        } catch (ServiceException serviceException) {
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            request.getRequestDispatcher("/questionDetail.action?id=" + result1).forward(request, response);
            return;
        }
    }
}
