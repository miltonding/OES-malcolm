package com.malcolm.oes.service;

import com.malcolm.oes.dao.UserDao;
import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.StringUtil;

public class UserService {
    public User Login(String userName, String password) throws ParameterException, ServiceException {

        ParameterException parameterException = new ParameterException();

        if (StringUtil.isEmpty(userName)) {
            parameterException.addErrorField("userName", "User Name is required");
        }
        if (StringUtil.isEmpty(password)) {
            parameterException.addErrorField("password", "password is required");
        }

        if (parameterException.isErrorField()) {
            throw parameterException;
        }

        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(userName);
        if (user == null) {
            throw new ServiceException(1000, "User name is not exist");
        }
        if (!password.equals(user.getPassword())) {
            throw new ServiceException(1001, "User name or password not right");
        }
        return user;
    }
}