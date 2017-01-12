package com.malcolm.oes.service;

import java.util.List;

import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Exam;
import com.malcolm.oes.model.ExamVo;
import com.malcolm.oes.model.PaginationVo;

public interface ExamService {

    public int createExam(int userId, Exam exam) throws ParameterException, ServiceException;

    public int createExamDraft(int userId, Exam exam) throws ParameterException, ServiceException;

    public List<ExamVo> findExamList(PaginationVo paginationVo) throws ParameterException, ServiceException;

    public int deleteExamByIds(int ids[]) throws ParameterException, ServiceException;
}