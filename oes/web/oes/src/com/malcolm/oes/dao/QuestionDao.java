package com.malcolm.oes.dao;

import java.util.List;

import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;

public interface QuestionDao {

    public int saveQuestion(Question question, User user);

    public List<Question> findQuestionList(String currentOrder, Pagination pagination);

    public List<Question> findQuestioinListByKeyword(String keyword, String currentOrder,
            Pagination pagination);

    // The question is not delete.
    public int getQuestionRecordCount();

    // That will be search all question.
    public int getAllQuestionCount();

    public int getQuestionRecordCountByKeyword(String keyword);

    // This method update the question's status used by ids.
    public int updateQuestionStatusByIds(String ids);

    public Question getQuestionById(int id);

    public Question getQuestionByName(String name);

    public List<Integer> getRandomQuestions(int questionQuantity);

    public int updateUsedQuestions(List<Integer> questionIds, int usedStatus);
}
