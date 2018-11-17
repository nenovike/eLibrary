package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
