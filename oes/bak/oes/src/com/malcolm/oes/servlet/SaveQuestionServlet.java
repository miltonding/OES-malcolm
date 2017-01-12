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

public class SaveQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SaveQuestionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String answer = StringUtil.HtmlEncode(request.getParameter("answer"));
        String questionId = StringUtil.HtmlEncode(request.getParameter("questionId"));
        String questionTitle = request.getParameter("questionTitle");
        String answerA = StringUtil.HtmlEncode(request.getParameter("answerA"));
        String answerB = StringUtil.HtmlEncode(request.getParameter("answerB"));
        String answerC = StringUtil.HtmlEncode(request.getParameter("answerC"));
        String answerD = StringUtil.HtmlEncode(request.getParameter("answerD"));
        int status = 1;
        Question question = new Question(questionTitle,questionId, answerA, answerB, answerC, answerD, answer, status);
        QuestionService questionService = new QuestionService();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        try {
            int result = questionService.saveQuestion(question, user);
            if (result != 0) {
                session.setAttribute("question_flash_msg", "save is ok!");
            }else{
                session.setAttribute("question_flash_msg", "save is failure!");
            }
            response.sendRedirect(request.getContextPath() + "/createQuestion.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            request.setAttribute(Constants.ERROR_FIELDS, errorFields);
            request.getRequestDispatcher("/createQuestion.action").forward(request, response);
            return;
        } catch (ServiceException serviceException) {
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            request.getRequestDispatcher("/createQuestion.action").forward(request, response);
            return;
        }
    }
}
