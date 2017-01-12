package com.malcolm.oes.service;

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
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:oes-mvc.xml" })
public class QuestionServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Before
    public void setUp() throws Exception {
        AppContext.setContextPath("/oes");
        AppContext appContext = AppContext.getContext();
        appContext.addObject(Constants.APP_CONTEXT_SESSION, new MockHttpSession());
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, new MockHttpServletRequest());
    }

    @After
    public void tearDown() throws Exception {
        AppContext appContext = AppContext.getContext();
        appContext.clear();
    }

    // Input the right question to save.
    @Test
    public void testSaveQuestion() {
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        Question question = new Question("Whch is the right answer", "Q007 8", "answerA", "answerB", "answerC",
                "answerD", "answer");
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            int result = questionService.saveQuestion(question, user);
            Assert.assertEquals(question.getId(), result);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // Input the wrong question to save. The question's Parameter is null.
    @Test
    public void testSaveQuestionBadParameter() {
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        Question question = new Question(null, null, null, null, null, null, "answer");
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            questionService.saveQuestion(question, user);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // Input the wrong user to save. The user is null.
    @Test
    public void testSaveQuestionBadUserId() {
        User user = null;
        Question question = new Question("Whch is the right answer", "Q007 8", "answerA", "answerB", "answerC",
                "answerD", "answer");
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            questionService.saveQuestion(question, user);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertNotNull(e);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    // The right situation.
    @Test
    public void testFindQuestionList() {
        String currentOrder = "ASC";
        Pagination pagination = new Pagination();
        pagination.setNowPage(1);
        pagination.setPageSize(10);
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            List<Question> questionList = questionService.findQuestionList(currentOrder, pagination);
            Assert.assertNotNull(questionList);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation
    @Test
    public void testFindQuestioinListByKeyword() {
        String keyword = "Q";
        String currentOrder = "ASC";
        Pagination pagination = new Pagination();
        pagination.setNowPage(1);
        pagination.setPageSize(10);
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            List<Question> questionList = questionService.findQuestioinListByKeyword(keyword, currentOrder, pagination);
            Assert.assertNotNull(questionList);
        } catch (ParameterException | ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation. The ids's id exist.
    @Test
    public void testDeleteQuestionByIds() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        int ids[] = { 145 };
        try {
            int result = questionService.deleteQuestionByIds(ids);
            Assert.assertEquals(ids.length, result);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation. The ids's id exist.
    @Test
    public void testDeleteQuestionByIdsEmptyIds() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        int ids[] = null;
        try {
            questionService.deleteQuestionByIds(ids);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. The ids's length is 0;
    @Test
    public void testDeleteQuestionByWrongId() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        int ids[] = {};
        try {
            questionService.deleteQuestionByIds(ids);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertEquals("Delete questions by Ids[] is faliure.", e.getMessage());
        }
    }

    // The wrong situation. The ids's length is not all finish.
    @Test
    public void testDeleteQuestionByWrongIds() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        int ids[] = { 10, 145 };
        try {
            questionService.deleteQuestionByIds(ids);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertEquals("Delete questions by Ids[] is faliure.", e.getMessage());
        }
    }

    // The right situation. The question's id is exist.
    @Test
    public void testGetQuestionById() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            Question question = questionService.getQuestionById(145);
            Assert.assertNotNull(question);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. The question's id is not exist.
    @Test
    public void testGetQuestionByIdBadId() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            questionService.getQuestionById(10);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertEquals("Get question by id is failure.", e.getMessage());
        }
    }

    // The wrong situation. The question's id is 0.
    @Test
    public void testGetQuestionByIdEmptyId() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            questionService.getQuestionById(0);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation.
    @Test
    public void testSaveEditQuestion() {
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        Question question = new Question("Whch is the right answer", "Q007 8", "answerA", "answerB", "answerC",
                "answerD", "answer");
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            int result = questionService.saveEditQuestion(question, user, 145);
            Assert.assertEquals(question.getId(), result);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. Bad question with null parameter
    @Test
    public void testSaveEditQuestionBadQuestion() {
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        Question question = new Question(null, null, null, null, null, null, "answer");
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            questionService.saveEditQuestion(question, user, 145);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The wrong situation. The bad question id will be update.
    @Test
    public void testSaveEditQuestionBadId() {
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        Question question = new Question("Whch is the right answer", "Q007 8", "answerA", "answerB", "answerC",
                "answerD", "answer");
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        try {
            questionService.saveEditQuestion(question, user, 10);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertNotNull(e);
        }
    }

    // The right situation. The question id is not exist.
    @Test
    public void testVerifyQuestionId() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        Question question;
        try {
            question = questionService.verifyQuestionId("Q0001");
            Assert.assertNotNull(question);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    // The right situation. Return the question quantity
    @Test
    public void verifyQuestionQuantity() {
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        int questionQuantity;
        try {
            questionQuantity = questionService.getQuestionQuantity();
            Assert.assertNotNull(questionQuantity);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
