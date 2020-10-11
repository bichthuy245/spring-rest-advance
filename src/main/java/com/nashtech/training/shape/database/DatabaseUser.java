package com.nashtech.training.shape.database;

import com.nashtech.training.shape.model.UserInfo;
import com.nashtech.training.shape.repository.UserDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUser implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUser.class);

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public void run(String... args) throws Exception {
        //Init Customer Database
        userDetailsRepository.save
                (new UserInfo("admin", "admin@123", "ADMIN", true));
        userDetailsRepository.save
                (new UserInfo("user1", "user@123", "USER", true));
    }
}