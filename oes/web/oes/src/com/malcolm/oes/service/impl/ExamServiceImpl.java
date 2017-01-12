package com.malcolm.oes.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.malcolm.oes.dao.ExamDao;
import com.malcolm.oes.dao.QuestionDao;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Exam;
import com.malcolm.oes.model.ExamVo;
import com.malcolm.oes.model.PaginationVo;
import com.malcolm.oes.service.ExamService;
import com.malcolm.oes.util.StringUtil;

public class ExamServiceImpl implements ExamService {

    private static final int isDraftNum = 1;
    private static final int isNotDraftNum = 1;

    private Logger log = Logger.getLogger(ExamService.class);

    private ExamDao examDao;
    private QuestionDao questionDao;

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public int createExam(int userId, Exam exam) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (!StringUtil.inCorrectLength(exam.getExamName(), 1, 20)) {
            parameterException.addErrorField("examName", "Exam name's length should in 1~20.");
        }
        if (!StringUtil.inCorrectLength(exam.getExamDescription(), 0, 200)) {
            parameterException.addErrorField("examDescription", "Description's length should in 0~200.");
        }
        if (StringUtil.isEmpty(exam.getExamDuration())) {
            parameterException.addErrorField("examDuration", "Exam duration is required.");
        }
        if (exam.getEffectiveTime() == null) {
            parameterException.addErrorField("effective time", "Exam effective time is required.");
        }
        if (!StringUtil.inCorrectLength(exam.getQuestionQuantity() + "", 1, 3)) {
            parameterException.addErrorField("questionQuantity", "Question quantity's length should in 1~3.");
        }
        if (!StringUtil.inCorrectLength(exam.getQuestionPoint() + "", 1, 2)) {
            parameterException.addErrorField("questionPoint", "Question point's length should in 1~2.");
        }
        if (!StringUtil.inCorrectLength(exam.getPassCriteria() + "", 1, 5)) {
            parameterException.addErrorField("passCriteria", "Pass criteria's length should in 1~5.");
        }
        if (exam.getIsDraft() != 0 && exam.getIsDraft() != 1) {
            parameterException.addErrorField("isDraft", "Is draft is required");
        }
        if (parameterException.isErrorField()) {
            log.error("throw [parameterException] in createExam(int userId, Exam exam).");
            throw parameterException;
        }

        StringUtil.obecjtParamFilter(exam);

        int examId = examDao.saveExam(userId, exam);
        List<Integer> questionIds = questionDao.getRandomQuestions(exam.getQuestionQuantity());
        questionDao.updateUsedQuestions(questionIds, 1);
        examDao.saveExamPaper(examId, questionIds);
        return examId;
    }

    @Override
    public List<ExamVo> findExamList(PaginationVo paginationVo) throws ParameterException, ServiceException {
        int totalRecord = examDao.getRecordCount(paginationVo.getKeyword());
        paginationVo.getPagination().setTotalRecord(totalRecord);
        return examDao.findExamList(paginationVo);
    }

    @Override
    public int deleteExamByIds(int[] ids) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (null == ids) {
            parameterException.addErrorField("ids", "You must select one checkbox.");
        }
        if (parameterException.isErrorField()) {
            log.error("throw [parameterException] in deleteExamByIds(int[] ids).");
            throw parameterException;
        }
        return examDao.updateExamStatus(StringUtil.revertArrayToString(ids));
    }

    @Override
    public int createExamDraft(int userId, Exam exam) throws ParameterException, ServiceException {
        ParameterException parameterException = new ParameterException();
        if (!StringUtil.inCorrectLength(exam.getExamName(), 1, 20)) {
            parameterException.addErrorField("examName", "Exam name's length should in 1~20.");
        }
        if (!StringUtil.inCorrectLength(exam.getExamDescription(), 0, 200)) {
            parameterException.addErrorField("examDescription", "Description's length should in 0~200.");
        }
        if (StringUtil.isEmpty(exam.getExamDuration())) {
            parameterException.addErrorField("examDuration", "Exam duration is required.");
        }
        if (exam.getEffectiveTime() == null) {
            parameterException.addErrorField("effective time", "Exam effective time is required.");
        }
        if (!StringUtil.inCorrectLength(exam.getQuestionQuantity() + "", 1, 3)) {
            parameterException.addErrorField("questionQuantity", "Question quantity's length should in 1~3.");
        }
        if (!StringUtil.inCorrectLength(exam.getQuestionPoint() + "", 1, 2)) {
            parameterException.addErrorField("questionPoint", "Question point's length should in 1~2.");
        }
        if (!StringUtil.inCorrectLength(exam.getPassCriteria() + "", 1, 5)) {
            parameterException.addErrorField("passCriteria", "Pass criteria's length should in 1~5.");
        }
        if (exam.getIsDraft() != isNotDraftNum && exam.getIsDraft() != isDraftNum) {
            parameterException.addErrorField("isDraft", "Is draft is required");
        }
        if (parameterException.isErrorField()) {
            throw parameterException;
        }

        StringUtil.obecjtParamFilter(exam);

        return examDao.saveExam(userId, exam);
    }
}
