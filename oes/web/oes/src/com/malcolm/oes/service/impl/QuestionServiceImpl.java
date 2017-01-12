package com.malcolm.oes.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.malcolm.oes.dao.QuestionDao;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.QuestionService;
import com.malcolm.oes.util.StringUtil;

public class QuestionServiceImpl implements QuestionService {

    private Logger log = Logger.getLogger(QuestionService.class);

    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public int saveQuestion(Question question, User user) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (!StringUtil.inCorrectLength(question.getQuestionId(), 1, 20)) {
            parameterException.addErrorField("questionId", "Question's id should in 1~20.");
        }
        if (!StringUtil.inCorrectLength(question.getQuestionTitle(), 1, 200)) {
            parameterException.addErrorField("question", "Question's length should in 1~200.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerA(), 1, 30)) {
            parameterException.addErrorField("answerA", "Answer A's length should in 1~30.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerB(), 1, 30)) {
            parameterException.addErrorField("answerB", "Answer B's length should in 1~30.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerC(), 1, 30)) {
            parameterException.addErrorField("answerC", "Answer C's length should in 1~30.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerD(), 1, 30)) {
            parameterException.addErrorField("answerD", "Answer D's length should in 1~30.");
        }
        if (parameterException.isErrorField()) {
            log.error("throw [parameterException] in saveQuestion(Question question, User user).");
            throw parameterException;
        }
        if (null == user ) {
            log.error("throw [parameterException] in saveQuestion(Question question, User user). bad user " + user);
            throw new ServiceException(1101, "The user is required.");
        }

        // This method will filter the special parameter.
        StringUtil.obecjtParamFilter(question);

        return questionDao.saveQuestion(question, user);
    }

    public List<Question> findQuestionList(String currentOrder, Pagination pagination) throws ParameterException,
            ServiceException {
        int totalRecord = questionDao.getQuestionRecordCount();
        pagination.setTotalRecord(totalRecord);
        return questionDao.findQuestionList(currentOrder, pagination);
    }

    public List<Question> findQuestioinListByKeyword(String keyword, String currentOrder, Pagination pagination)
            throws ParameterException, ServiceException {
        currentOrder = StringUtil.isOrder(currentOrder);
        int totalRecord = questionDao.getQuestionRecordCountByKeyword(keyword);
        pagination.setTotalRecord(totalRecord);
        return questionDao.findQuestioinListByKeyword(keyword, currentOrder, pagination);
    }

    public int deleteQuestionByIds(int ids[]) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (null == ids) {
            parameterException.addErrorField("ids", "You must select one checkbox.");
        }
        if (parameterException.isErrorField()) {
            log.error("throw [parameterException] in deleteQuestionByIds(int ids[]).");
            throw parameterException;
        }
        // I just modify the question's status is 0.
        int result = questionDao.updateQuestionStatusByIds(StringUtil.revertArrayToString(ids));
        if (result != ids.length || result == 0) {
            log.error("throw [ServiceException] in deleteQuestionByIds(int ids[]). bad result : " + result);
            throw new ServiceException(1103, "Delete questions by Ids[] is faliure.");
        }
        return result;
    }

    public Question getQuestionById(int id) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (id == 0) {
            parameterException.addErrorField("id", "Id is required.");
        }
        if (parameterException.isErrorField()) {
            log.error("throw [parameterException] in getQuestionById(int id).");
            throw parameterException;
        }
        Question question = questionDao.getQuestionById(id);
        if (null == question) {
            log.error("throw [ServiceException] in getQuestionById(int id). bad question : " + question);
            throw new ServiceException(1105, "Get question by id is failure.");
        }
        return question;
    }

    public int saveEditQuestion(Question question, User user, int oldId) throws ParameterException, ServiceException {
        int ids[] = { oldId };
        ParameterException parameterException = new ParameterException();
        if (!StringUtil.inCorrectLength(question.getQuestionId(), 1, 20)) {
            parameterException.addErrorField("questionId", "Question's id should in 1~20.");
        }
        if (!StringUtil.inCorrectLength(question.getQuestionTitle(), 1, 200)) {
            parameterException.addErrorField("question", "Question's length should in 1~200.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerA(), 1, 30)) {
            parameterException.addErrorField("answerA", "Answer A's length should in 1~30.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerB(), 1, 30)) {
            parameterException.addErrorField("answerB", "Answer B's length should in 1~30.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerC(), 1, 30)) {
            parameterException.addErrorField("answerC", "Answer C's length should in 1~30.");
        }
        if (!StringUtil.inCorrectLength(question.getAnswerD(), 1, 30)) {
            parameterException.addErrorField("answerD", "Answer D's length should in 1~30.");
        }
        if (parameterException.isErrorField()) {
            log.error("throw [parameterException] in saveEditQuestion(Question question, User user, int oldId).");
            throw parameterException;
        }
        // This method will filter the special parameter.
        StringUtil.obecjtParamFilter(question);

        int saveResult = questionDao.saveQuestion(question, user);
        int updateResult = questionDao.updateQuestionStatusByIds(StringUtil.revertArrayToString(ids));
        if (updateResult == 0) {
            log.error("throw [ServiceException] in saveEditQuestion(Question question, User user, int oldId). bad updateResult : " + updateResult);
            throw new ServiceException(1108, "Update question by id is failure.");
        }
        return saveResult;
    }

    @Override
    public Question verifyQuestionId(String questionId) {
        Question question = questionDao.getQuestionByName(questionId);
        return question;
    }

    @Override
    public int getQuestionQuantity() {
        return questionDao.getQuestionRecordCount();
    }

    @Override
    public int getAllQuestionCount() throws ParameterException, ServiceException {
        return questionDao.getAllQuestionCount();
    }
}
