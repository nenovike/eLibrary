package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.UserDao;
import com.snopkowski.elibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void save(UserDao userDao) {
        userDao.setPassword(passwordEncoder.encode(userDao.getPassword()));
        userRepository.save(userDao);
    }

    public UserDao findById(int id) {
        return userRepository.findById(id);
    }

    public UserDao findBySso(String sso) {
        return userRepository.findBySSO(sso);
    }

    public List<UserDao> findAllUsers() {
        return userRepository.findAllUsers();
    }

}
