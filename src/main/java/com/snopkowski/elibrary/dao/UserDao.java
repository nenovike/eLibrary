package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User findById(int id);

    User findBySSO(String sso);

    List<User> findAllUsers();
}

