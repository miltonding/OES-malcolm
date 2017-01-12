package com.malcolm.oes.service;

import java.util.List;

import com.malcolm.oes.dao.QuestionDao;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.PageBean;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.StringUtil;

public class QuestionService {

    public int saveQuestion(Question question, User user) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (!StringUtil.verifyString(question.getQuestionId(), 1, 20)) {
            parameterException.addErrorField("questionId", "Question's id should in 1~20");
        }
        if (!StringUtil.verifyString(question.getQuestionTitle(), 1, 200)) {
            parameterException.addErrorField("question", "Question's length should in 1~200");
        }
        if (!StringUtil.verifyString(question.getAnswerA(), 1, 30)) {
            parameterException.addErrorField("answerA", "Answer A's length should in 1~30");
        }
        if (!StringUtil.verifyString(question.getAnswerB(), 1, 30)) {
            parameterException.addErrorField("answerB", "Answer B's length should in 1~30");
        }
        if (!StringUtil.verifyString(question.getAnswerC(), 1, 30)) {
            parameterException.addErrorField("answerC", "Answer C's length should in 1~30");
        }
        if (!StringUtil.verifyString(question.getAnswerD(), 1, 30)) {
            parameterException.addErrorField("answerD", "Answer D's length should in 1~30");
        }
        if (parameterException.isErrorField()) {
            throw parameterException;
        }
        QuestionDao questionDao = new QuestionDao();
        int result = questionDao.saveQuestion(question, user);
        if (result == 0) {
            throw new ServiceException(1100, "User is required");
        }
        return result;
    }

    public List<Question> findQuestionList(PageBean pageBean) throws ParameterException, ServiceException {
        QuestionDao questionDao = new QuestionDao();
        int totalRecord = questionDao.getquestionRecordCount();
        pageBean.setTotalRecord(totalRecord);
        List<Question> questionList = questionDao.findQuestionList(pageBean);
        if (questionList == null) {
            throw new ServiceException(1101, "Find Question list occur Exception");
        }
        return questionList;
    }

    public List<Question> findQuestioinListByKeyword(String keyword, PageBean pageBean) throws ParameterException,
            ServiceException {
        QuestionDao questionDao = new QuestionDao();
        int totalRecord = questionDao.getquestionRecordCount(keyword);
        pageBean.setTotalRecord(totalRecord);
        if (StringUtil.isEmpty(keyword)) {
            // If user coud not input any words, we should select all questions.
            return questionDao.findQuestionList(pageBean);
        }
        return questionDao.findQuestioinListByKeyword(keyword, pageBean);
    }

    public int deleteQuestionByIds(int ids[]) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (ids == null) {
            parameterException.addErrorField("ids", "You must select only one checkbox");
        }
        if (parameterException.isErrorField()) {
            throw parameterException;
        }
        QuestionDao questionDao = new QuestionDao();
        // I just modify the question's status is 0.
        int result = questionDao.updateQuestionStatusByIds(ids);
        if (result != ids.length) {
            throw new ServiceException(1103, "Id is required");
        }
        return result;
    }

    public int deleteQuestionById(int id) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (id == 0) {
            parameterException.addErrorField("id", "Id is required");
        }
        if (parameterException.isErrorField()) {
            throw parameterException;
        }
        QuestionDao questionDao = new QuestionDao();
        return questionDao.updateQuestionStatusById(id);
    }

    public Question getQuestionById(int id) throws ParameterException, ServiceException {
        QuestionDao questionDao = new QuestionDao();
        return questionDao.getQuestionById(id);
    }

    public int updateQuestionById(int id) throws ParameterException, ServiceException {
        QuestionDao questionDao = new QuestionDao();
        int ids[] = { id };
        int result = questionDao.updateQuestionStatusByIds(ids);
        if (result != ids.length) {
            throw new ServiceException(1105, "Id is required");
        }
        return result;
    }
}
