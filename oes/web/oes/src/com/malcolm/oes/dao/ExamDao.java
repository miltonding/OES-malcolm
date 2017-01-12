package com.malcolm.oes.dao;

import java.util.List;

import com.malcolm.oes.model.Exam;
import com.malcolm.oes.model.ExamVo;
import com.malcolm.oes.model.PaginationVo;

public interface ExamDao {

    public int saveExam(int userId, Exam exam);

    public int saveExamPaper(int examId, List<Integer> questionIds);

    public List<ExamVo> findExamList(PaginationVo paginationVo);

    public int getRecordCount(String keyword);

    public int updateExamStatus(String ids);
}
