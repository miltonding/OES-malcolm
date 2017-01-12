package com.malcolm.oes.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.malcolm.oes.dao.QuestionDao;
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.PaginationVo;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.model.UserQuestionVo;

public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {

    private static final String CLASS_NAME = Question.class.getName();

    private static final String SQL_ID_FIND_QUESTION_LIST = ".findQuestionList";
    private static final String SQL_ID_FIND_QUESTION_LIST_BY_KEYWORD = ".findQuestioinListByKeyword";

    private static final String SQL_ID_SAVE_QUESTION = ".saveQuestion";

    private static final String SQL_ID_GET_RANDOM_QUESTIONS = ".getRandomQuestions";
    private static final String SQL_ID_GET_QUESTION_RECORD_COUNT = ".getQuestionRecordCount";
    private static final String SQL_ID_GET_QUESTION_RECORD_COUNT_BY_KEYWORD = ".getQuestionRecordCountByKeyword";
    private static final String SQL_ID_GET_QUESTION_BY_ID = ".getQuestionById";
    private static final String SQL_ID_GET_QUESTION_BY_NAME = ".getQuestionByName";
    private static final String SQL_ID_GET_ALL_QUESTION_COUNT = ".getAllQuestionCount";

    private static final String SQL_ID_UPDATE_QUESTION_STATUS_BY_IDS = ".updateQuestionStatusByIds";
    private static final String SQL_ID_UPDATE_USED_QUESTION = ".updateUsedQuestions";

    @Override
    public int saveQuestion(Question question, User user) {
        UserQuestionVo userQuestionVo = new UserQuestionVo();
        userQuestionVo.setQuestion(question);
        userQuestionVo.setUser(user);
        getSqlSession().insert(CLASS_NAME + SQL_ID_SAVE_QUESTION, userQuestionVo);
        return question.getId();
    }

    @Override
    public List<Question> findQuestionList(String currentOrder, Pagination pagination) {
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setCurrentOrder(currentOrder);
        paginationVo.setPagination(pagination);
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_QUESTION_LIST, paginationVo);
    }

    @Override
    public List<Question> findQuestioinListByKeyword(String keyword, String currentOrder, Pagination pagination) {
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setCurrentOrder(currentOrder);
        paginationVo.setPagination(pagination);
        paginationVo.setKeyword(keyword);
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_QUESTION_LIST_BY_KEYWORD, paginationVo);
    }

    @Override
    public int getQuestionRecordCount() {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_QUESTION_RECORD_COUNT);
    }

    @Override
    public int getQuestionRecordCountByKeyword(String keyword) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_QUESTION_RECORD_COUNT_BY_KEYWORD, keyword);
    }

    @Override
    public int updateQuestionStatusByIds(String ids) {
        return getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE_QUESTION_STATUS_BY_IDS, ids);
    }

    @Override
    public Question getQuestionById(int id) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_QUESTION_BY_ID, id);
    }

    @Override
    public Question getQuestionByName(String name) {
        List<Question> questionList = getSqlSession().selectList(CLASS_NAME + SQL_ID_GET_QUESTION_BY_NAME, name);
        if (questionList != null && questionList.size() > 0) {
            return questionList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Integer> getRandomQuestions(int questionQuantity) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_GET_RANDOM_QUESTIONS, questionQuantity);
    }

    @Override
    public int updateUsedQuestions(List<Integer> questionIds, int usedStatus) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("usedStatus", usedStatus);
        param.put("questionIds", questionIds);
        return getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE_USED_QUESTION, param);
    }

    @Override
    public int getAllQuestionCount() {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_ALL_QUESTION_COUNT);
    }
}
