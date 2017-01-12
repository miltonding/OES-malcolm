package com.malcolm.oes.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.malcolm.oes.dao.ExamDao;
import com.malcolm.oes.model.Exam;
import com.malcolm.oes.model.ExamVo;
import com.malcolm.oes.model.PaginationVo;

public class ExamDaoImpl extends SqlSessionDaoSupport implements ExamDao {

    private static final String CLASS_NAME = Exam.class.getName();

    private static final String SQL_ID_CREATE_EXAM = ".saveExam";
    private static final String SQL_ID_SAVE_EXAM_PAPER = ".saveExamPaper";
    private static final String SQL_ID_FIND_EXAM_LIST = ".findExamList";
    private static final String SQL_ID_GET_RECORD_COUNT = ".getExamRecordCount";
    private static final String SQL_ID_UPDATE_EXAM_STATUS = ".updateExamStatus";

    @Override
    public int saveExam(int userId, Exam exam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("exam", exam);
        getSqlSession().insert(CLASS_NAME + SQL_ID_CREATE_EXAM, params);
        return exam.getId();
    }

    @Override
    public int saveExamPaper(int examId, List<Integer> questionIds) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("examId", examId);
        params.put("userId", 1);
        params.put("questionIds", questionIds);
        return getSqlSession().insert(CLASS_NAME + SQL_ID_SAVE_EXAM_PAPER, params);
    }

    @Override
    public List<ExamVo> findExamList(PaginationVo paginationVo) {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_EXAM_LIST, paginationVo);
    }

    @Override
    public int getRecordCount(String keyword) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_RECORD_COUNT, keyword);
    }

    @Override
    public int updateExamStatus(String ids) {
        return getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE_EXAM_STATUS, ids);
    }
}