package com.malcolm.oes.controller;

import javax.servlet.http.HttpSession;

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
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:oes-mvc.xml" })
public class QuestionControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        AppContext.setContextPath("/oes");
        AppContext appContext = AppContext.getContext();
        User user = new User();
        user.setId(2);
        user.setUserName("lisi");
        HttpSession session = new MockHttpSession();
        session.setAttribute(Constants.USER, user);
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, new MockHttpServletRequest());
    }

    @After
    public void tearDown() throws Exception {
        AppContext appContext = AppContext.getContext();
        appContext.clear();
    }

    @Test
    public void testSaveQuestion() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        Question question = new Question("A", "Q007", "which is the answer", "A", "B", "C", "D");
        questionController.saveQuestion(question);
        // Assert.assertNotSame(0, question.getId());
        Assert.assertEquals(SessionUtil.getSession(Constants.QUESTION_SUCCESS_FLASH_MSG), "Save question is ok!");
    }
}
