package com.malcolm.oes.service.impl;

import org.apache.log4j.Logger;

import com.malcolm.oes.dao.UserDao;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.User;
import com.malcolm.oes.service.UserService;
import com.malcolm.oes.util.StringUtil;

public class UserServiceImpl implements UserService {

    private Logger log = Logger.getLogger(UserService.class);

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String userName, String password) throws ParameterException, ServiceException {

        ParameterException parameterException = new ParameterException();

        if (StringUtil.isEmpty(userName)) {
            parameterException.addErrorField("userName", "User Name is required.");
        }
        if (StringUtil.isEmpty(password)) {
            parameterException.addErrorField("password", "password is required.");
        }

        if (parameterException.isErrorField()) {
            throw parameterException;
        }
        User user = userDao.getUserByName(userName);
        if (null == user) {
            log.error("throw [ServiceException] in login(String userName, String password). bad user : " + user);
            throw new ServiceException(1000, "User name is not exist.");
        }
        if (!password.equals(user.getPassword())) {
            log.error("throw [ServiceException] in login(String userName, String password). bad password : " + password);
            throw new ServiceException(1001, "User name or password not right.");
        }
        return user;
    }
}
