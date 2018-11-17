package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
