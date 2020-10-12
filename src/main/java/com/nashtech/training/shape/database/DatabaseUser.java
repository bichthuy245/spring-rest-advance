package com.nashtech.training.shape.database;

import com.nashtech.training.shape.model.UserInfo;
import com.nashtech.training.shape.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUser implements CommandLineRunner {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //Init Customer Database
        userDetailsRepository.save
                (new UserInfo("admin", passwordEncoder.encode("admin@123"), "ADMIN", true));
        userDetailsRepository.save
                (new UserInfo("user1", passwordEncoder.encode("user@123"), "USER", true));
    }
}