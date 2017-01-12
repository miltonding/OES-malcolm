package com.malcolm.oes.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.malcolm.oes.AppContext;
import com.malcolm.oes.Constants;
import com.malcolm.oes.util.PathUtil;
import com.malcolm.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:oes-mvc.xml" })
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

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

    @Test
    public void testLogin() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.login("lisi", "1234", "", "");
        RedirectView redirectView = (RedirectView) modelAndView.getView();
        Assert.assertEquals(PathUtil.getFullPath("dashBoard/myoes"), redirectView.getUrl());
        Assert.assertNotNull(SessionUtil.getSession(Constants.USER));
    }
}
