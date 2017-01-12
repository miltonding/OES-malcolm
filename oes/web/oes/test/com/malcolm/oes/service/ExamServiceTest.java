package com.malcolm.oes.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.malcolm.oes.AppContext;
import com.malcolm.oes.Constants;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.Exam;
import com.malcolm.oes.model.ExamVo;
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.PaginationVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:oes-mvc.xml" })
public class ExamServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    private ExamService examService;

    @Before
    public void setUp() throws Exception {
        AppContext.setContextPath("/oes");
        AppContext appContext = AppContext.getContext();
        appContext.addObject(Constants.APP_CONTEXT_SESSION, new MockHttpSession());
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, new MockHttpServletRequest());
        examService = (ExamService) this.applicationContext.getBean("examService");
    }

    @After
    public void tearDown() throws Exception {
        AppContext appContext = AppContext.getContext();
        appContext.clear();
    }

    // The right situation.
    @Test
    public void testSaveExam() {
        int userId = 1;
        Exam exam = new Exam();
        exam.setExamDescription("this is a exam.");
        exam.setEffectiveTime(new Date());
        exam.setExamDuration("60");
        exam.setExamName("First Exam");
        exam.setPassCriteria(60);
        exam.setQuestionPoint(4);
        exam.setQuestionQuantity(25);
        exam.setTotalScore(100);
        exam.setIsDraft(0);
        try {
            examService.createExam(userId, exam);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation.
    @Test
    public void testSaveExamBadExam() {
        int userId = 1;
        Exam exam = new Exam();
        exam.setExamDescription(null);
        exam.setEffectiveTime(null);
        exam.setExamDuration(null);
        exam.setExamName("");
        exam.setPassCriteria(333333);
        exam.setQuestionPoint(333);
        exam.setQuestionQuantity(3333);
        exam.setTotalScore(3333 * 334);
        exam.setIsDraft(-1);
        try {
            examService.createExam(userId, exam);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. The isDraft is not in range.
    @Test
    public void testSaveExamBadIsDraft() {
        int userId = 1;
        Exam exam = new Exam();
        exam.setExamDescription(null);
        exam.setEffectiveTime(null);
        exam.setExamDuration(null);
        exam.setExamName("");
        exam.setPassCriteria(333333);
        exam.setQuestionPoint(333);
        exam.setQuestionQuantity(3333);
        exam.setTotalScore(3333 * 334);
        exam.setIsDraft(1);
        try {
            examService.createExam(userId, exam);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation.
    @Test
    public void testfinExamList() {
        Pagination pagination = new Pagination();
        pagination.setNowPage(1);
        pagination.setPageSize(10);
        PaginationVo paginationVo = new PaginationVo(pagination, "t", "DESC", "effective_time", "2016-08-15 20:20:25",
                "2016-12-29 20:20:25");
        try {
            List<ExamVo> count = examService.findExamList(paginationVo);
            System.out.println(count.size());
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    // The right situation.
    @Test
    public void testcreateExamDraft() {
        int userId = 1;
        Exam exam = new Exam();
        exam.setExamDescription("this is a exam.");
        exam.setEffectiveTime(new Date());
        exam.setExamDuration("60");
        exam.setExamName("First Exam");
        exam.setPassCriteria(60);
        exam.setQuestionPoint(4);
        exam.setQuestionQuantity(25);
        exam.setTotalScore(100);
        exam.setIsDraft(0);
        try {
            examService.createExamDraft(userId, exam);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. The exam is null.
    @Test
    public void testSaveExamDraftBadExam() {
        int userId = 1;
        Exam exam = new Exam();
        exam.setExamDescription(null);
        exam.setEffectiveTime(null);
        exam.setExamDuration(null);
        exam.setExamName("");
        exam.setPassCriteria(333333);
        exam.setQuestionPoint(333);
        exam.setQuestionQuantity(3333);
        exam.setTotalScore(3333 * 334);
        exam.setIsDraft(-1);
        try {
            examService.createExamDraft(userId, exam);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. The isDraft is not in range.
    @Test
    public void testSaveExamDraftBadIsDraft() {
        int userId = 1;
        Exam exam = new Exam();
        exam.setExamDescription(null);
        exam.setEffectiveTime(null);
        exam.setExamDuration(null);
        exam.setExamName("");
        exam.setPassCriteria(333333);
        exam.setQuestionPoint(333);
        exam.setQuestionQuantity(3333);
        exam.setTotalScore(3333 * 334);
        exam.setIsDraft(1);
        try {
            examService.createExamDraft(userId, exam);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation. The exam is null.
    @Test
    public void testdeleteExamByIds() {
        int[] ids = { 14, 15, 16 };
        try {
            int x = examService.deleteExamByIds(ids);
            System.out.println(x);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertNotNull(e);
        }
    }

    // The wrong situation. The exam is null.
    @Test
    public void testdeleteExamByIdsBadIds() {
        int[] ids = null;
        try {
            examService.deleteExamByIds(ids);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertNotNull(e);
        }
    }
}
