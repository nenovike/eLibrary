package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findById(int id);

    User findBySso(String sso);

    List<User> findAllUsers();

}