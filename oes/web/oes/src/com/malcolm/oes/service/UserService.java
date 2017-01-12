package com.malcolm.oes.service;

import com.malcolm.oes.exception.ParameterException;
import com.malcolm.oes.exception.ServiceException;
import com.malcolm.oes.model.User;

public interface UserService {

    public User login(String userName, String password) throws ParameterException, ServiceException;
}