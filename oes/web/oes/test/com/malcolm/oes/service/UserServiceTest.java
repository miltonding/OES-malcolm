package com.malcolm.oes.service;

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
import com.malcolm.oes.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:oes-mvc.xml" })
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

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

    // Input the right user name and password
    @Test
    public void testRightUserNamePassword() {
        UserService userService = (UserService) this.applicationContext.getBean("userService");
        User user = null;
        try {
            user = userService.login("lisi", "1234");
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(user);
    }

    // Input a not exist's user name.
    @Test
    public void testNotExistUserName() {
        UserService userService = (UserService) this.applicationContext.getBean("userService");
        User user = null;
        try {
            user = userService.login("lisi1", "1234");
            Assert.assertNull(user);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertEquals("User name is not exist.", e.getMessage());
        }
    }

    // Input this right user name and wrong password.
    @Test
    public void testWrongPassword() {
        UserService userService = (UserService) this.applicationContext.getBean("userService");
        User user = null;
        try {
            user = userService.login("lisi", "234");
            Assert.assertNotNull(user);
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            Assert.assertEquals("User name or password not right.", e.getMessage());
        }
    }

    // Input this empty user name and password.
    @Test
    public void testWrongUserNamePassword() {
        UserService userService = (UserService) this.applicationContext.getBean("userService");
        User user = null;
        try {
            user = userService.login(null, null);
        } catch (ParameterException e) {
            Assert.assertNotNull(e);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
