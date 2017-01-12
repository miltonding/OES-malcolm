package com.malcolm.oes.dao;

import com.malcolm.oes.model.User;

public interface UserDao {

    public User getUserByName(String userName);

}
