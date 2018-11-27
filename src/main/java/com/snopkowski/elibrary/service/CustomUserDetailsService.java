package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.UserDao;
import com.snopkowski.elibrary.dao.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        UserDao userDao = userService.findBySso(ssoId);
        System.out.println("UserDao : " + userDao);
        if (userDao == null) {
            System.out.println("UserDao not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(userDao.getSsoId(), userDao.getPassword(),
                userDao.getState().equals("Active"), true, true, true, getGrantedAuthorities(userDao));
    }


    private List<GrantedAuthority> getGrantedAuthorities(UserDao userDao) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfileDao userProfileDao : userDao.getUserProfileDaos()) {
            System.out.println("UserProfileRepository : " + userProfileDao);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfileDao.getType()));
        }
        System.out.print("authorities :" + authorities);
        return authorities;
    }

}
