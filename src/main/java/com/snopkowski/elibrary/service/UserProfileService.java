package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.UserProfileDao;
import com.snopkowski.elibrary.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userProfileService")
@Transactional
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public List<UserProfileDao> findAll() {
        return userProfileRepository.findAll();
    }

    public UserProfileDao findByType(String type) {
        return userProfileRepository.findByType(type);
    }

    public UserProfileDao findById(int id) {
        return userProfileRepository.findById(id);
    }
}
