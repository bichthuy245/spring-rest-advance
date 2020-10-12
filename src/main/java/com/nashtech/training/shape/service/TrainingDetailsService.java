package com.nashtech.training.shape.service;

import java.util.Collections;

import com.nashtech.training.shape.model.UserInfo;
import com.nashtech.training.shape.repository.UserDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class TrainingDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        UserInfo userInfo = userDetailsRepository.findByUserName(userName);
        return new User(userInfo.getUserName(), userInfo.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(userInfo.getRole().toUpperCase())));
    }

    public UserInfo getUserInfoById (Integer id) {
        return userDetailsRepository.findById(id);
    }


}
