package com.malcolm.oes.controller;

import java.util.List;
import java.util.Map;

import com.malcolm.oes.Constants;
import com.malcolm.oes.common.ModelAndView;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.PageBean;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.StringUtil;

public class QuestionController {

    private static String QUESTION_DETAIL_PAGE = "/WEB-INF/jsp/question_detail.jsp";
    private static String QUESTION_MANAGEMENT = "/WEB-INF/jsp/question_management.jsp";
    private static String EDIT_QUESTION_PAGE = "/WEB-INF/jsp/edit_question.jsp";

    public ModelAndView createQuestion(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        modelAndView.setView("/WEB-INF/jsp/create_question.jsp");
        return modelAndView;
    }

    public ModelAndView questionDetail(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        String id = (String) request.get("id");
        QuestionService questionService = new QuestionService();
        try {
            Question question = questionService.getQuestionById(Integer.parseInt(id));
            modelAndView.addObject("question", question);
            modelAndView.setView(QUESTION_DETAIL_PAGE);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView(QUESTION_DETAIL_PAGE);
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView(QUESTION_DETAIL_PAGE);
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView saveQuestion(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        modelAndView.setSessions(session);
        String answer = StringUtil.HtmlEncode((String) request.get("answer")).trim();
        String questionId = StringUtil.HtmlEncode((String) request.get("questionId")).trim();
        String questionTitle = StringUtil.HtmlEncode((String) request.get("questionTitle")).trim();
        String answerA = StringUtil.HtmlEncode((String) request.get("answerA")).trim();
        String answerB = StringUtil.HtmlEncode((String) request.get("answerB")).trim();
        String answerC = StringUtil.HtmlEncode((String) request.get("answerC")).trim();
        String answerD = StringUtil.HtmlEncode((String) request.get("answerD")).trim();
        int status = 1;
        Question question = new Question(questionTitle, questionId, answerA, answerB, answerC, answerD, answer, status);
        QuestionService questionService = new QuestionService();

        User user = (User) session.get(Constants.USER);
        try {
            int result = questionService.saveQuestion(question, user);
            if (result != 0) {
                session.put("question_flash_msg", "save is ok!");
            } else {
                session.put("question_flash_msg", "save is failure!");
            }
            modelAndView.setRedirect(true);
            modelAndView.setView(request.get("projectPath") + "/createQuestion.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView("/createQuestion.action");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView("/createQuestion.action");
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView searchQuestion(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        String keyword = (String) request.get("keyword");
        String page = (String) request.get("page");
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        int nowPage = Integer.parseInt(page);

        QuestionService questionService = new QuestionService();
        try {
            PageBean pageBean = new PageBean();
            pageBean.setNowPage(nowPage);
            List<Question> questionList = questionService.findQuestioinListByKeyword(keyword, pageBean);
            modelAndView.addObject("pageBean", pageBean);
            modelAndView.addObject("questionList", questionList);
            modelAndView.addObject("keyword", keyword);
            modelAndView.setView(QUESTION_MANAGEMENT);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView(QUESTION_MANAGEMENT);
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView(QUESTION_MANAGEMENT);
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView deleteQuestionByIds(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        QuestionService questionService = new QuestionService();
        String deleteIds[] = null;
        if (request.get("questionCheckbox") instanceof String[]) {
            deleteIds = (String[]) request.get("questionCheckbox");
        } else {
            deleteIds = new String[1];
            deleteIds[0] = (String) request.get("questionCheckbox");
        }
        try {
            int count = questionService.deleteQuestionByIds(StringUtil.revertToInteger(deleteIds));
            if (count != 0) {
                session.put("question_flash_msg", "delete is ok!");
            } else {
                session.put("question_flash_msg", "delete is failure!");
            }
            modelAndView.setRedirect(true);
            modelAndView.setView(request.get("projectPath") + "/myoes.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView("/myoes.action");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView("/myoes.action");
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView editQuestion(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        String id = (String) request.get("id");
        QuestionService questionService = new QuestionService();
        try {
            Question question = questionService.getQuestionById(StringUtil.myParseInt(id));
            modelAndView.addObject("question", question);
            modelAndView.setView(EDIT_QUESTION_PAGE);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView(EDIT_QUESTION_PAGE);
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView(EDIT_QUESTION_PAGE);
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView editQuestionToSave(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        modelAndView.setSessions(session);
        String answer = StringUtil.HtmlEncode((String) request.get("answer")).trim();
        String questionId = StringUtil.HtmlEncode((String) request.get("questionId")).trim();
        String questionTitle = ((String) request.get("questionTitle")).trim();
        String answerA = StringUtil.HtmlEncode((String) request.get("answerA")).trim();
        String answerB = StringUtil.HtmlEncode((String) request.get("answerB")).trim();
        String answerC = StringUtil.HtmlEncode((String) request.get("answerC")).trim();
        String answerD = StringUtil.HtmlEncode((String) request.get("answerD")).trim();
        // I get the old id for this question model.
        String oldId = (String) request.get("oldId");
        int status = 1;
        Question question = new Question(questionTitle, questionId, answerA, answerB, answerC, answerD, answer, status);
        QuestionService questionService = new QuestionService();

        User user = (User) session.get(Constants.USER);
        int result1 = 0;
        try {
            result1 = questionService.saveQuestion(question, user);
            // I update the old question's status to 0.
            int result2 = questionService.updateQuestionById(StringUtil.myParseInt(oldId));
            if (result1 != 0 && result2 == 1) {
                session.put("update_question_flash_msg", "update is ok!");
            } else {
                session.put("update_question_flash_msg", "update is failure!");
            }
            question.setId(result1);
            modelAndView.setRedirect(true);
            modelAndView.setView(request.get("projectPath") + "/questionDetail.action?id=" + result1);
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView("/questionDetail.action?id=" + result1);
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView("/questionDetail.action?id=" + result1);
            return modelAndView;
        }
        return modelAndView;
    }

    public ModelAndView questionDetailDelete(Map<String, Object> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView(request, session);
        modelAndView.setSessions(session);
        QuestionService questionService = new QuestionService();
        String questionRealId = (String) request.get("id");
        try {
            int count = questionService.deleteQuestionById(StringUtil.myParseInt(questionRealId));
            if (count != 0) {
                session.put("question_flash_msg", "delete is ok!");
            } else {
                session.put("question_flash_msg", "delete is failure!");
            }
            modelAndView.setRedirect(true);
            modelAndView.setView(request.get("projectPath") + "/myoes.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setView("/myoes.action");
            return modelAndView;
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE,
                    serviceException.getMessage() + "[" + serviceException.getCode() + "]");
            modelAndView.setView("/myoes.action");
            return modelAndView;
        }
        return modelAndView;
    }
}
