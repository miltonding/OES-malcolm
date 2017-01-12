package com.malcolm.oes.service;

import java.util.List;

import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;

public interface QuestionService {

    public int saveQuestion(Question question, User user) throws ParameterException, ServiceException;

    public List<Question> findQuestionList(String currentOrder, Pagination pagination) throws ParameterException,
            ServiceException;

    public List<Question> findQuestioinListByKeyword(String keyword, String currentOrder, Pagination pagination)
            throws ParameterException, ServiceException;

    public int deleteQuestionByIds(int ids[]) throws ParameterException, ServiceException;

    public Question getQuestionById(int id) throws ParameterException, ServiceException;

    public int saveEditQuestion(Question question, User user, int oldId) throws ParameterException, ServiceException;

    public Question verifyQuestionId(String questionId) throws ParameterException, ServiceException;

    public int getQuestionQuantity() throws ParameterException, ServiceException;

    public int getAllQuestionCount() throws ParameterException, ServiceException;
}
