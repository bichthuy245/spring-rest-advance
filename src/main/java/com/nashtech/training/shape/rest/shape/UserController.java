package com.nashtech.training.shape.rest.shape;

import com.nashtech.training.shape.model.UserInfo;
import com.nashtech.training.shape.repository.UserDetailsRepository;
import com.nashtech.training.shape.service.TrainingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private TrainingDetailsService trainingDetailsService;

    @GetMapping("/user")
    public Object getAllUser(@RequestHeader HttpHeaders requestHeader) {
        List<UserInfo> userInfos = userDetailsRepository.findAll();
        if (userInfos == null || userInfos.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return userInfos;
    }

    @PostMapping("/user")
    public UserInfo addUser(@RequestBody UserInfo userRecord) {
        return userDetailsRepository.save(userRecord);
    }

    @PutMapping("/user/{id}")
    public UserInfo updateUser(@RequestBody UserInfo userRecord, @PathVariable Long id) {
        return userDetailsRepository.findById(id)
                .map(c -> {
                    c.setUserName(userRecord.getUserName());
                    c.setPassword(userRecord.getPassword());
                    c.setRole(userRecord.getRole());
                    c.setEnabled(userRecord.getEnabled());
                    return userDetailsRepository.save(c);
                })
                .orElseGet(() -> {
                    userRecord.setId(id);
                    return userDetailsRepository.save(userRecord);
                });
    }

    @PutMapping("/user/changePassword/{id}")
    public UserInfo updateUserPassword(@RequestBody UserInfo userRecord, @PathVariable Long id) {

        return userDetailsRepository.findById(id)
                .map(c -> {
                    c.setUserName(userRecord.getUserName());
                    c.setPassword(userRecord.getPassword());
                    c.setRole(userRecord.getRole());
                    c.setEnabled(userRecord.getEnabled());
                    return userDetailsRepository.save(c);
                })
                .orElseGet(() -> {
                    userRecord.setId(id);
                    return userDetailsRepository.save(userRecord);
                });
    }

    @PutMapping("/user/changeRole/{id}")
    public UserInfo updateUserRole(@RequestBody UserInfo userRecord, @PathVariable Long id) {

        return userDetailsRepository.findById(id)
                .map(c -> {
                    c.setUserName(userRecord.getUserName());
                    c.setPassword(userRecord.getPassword());
                    c.setRole(userRecord.getRole());
                    c.setEnabled(userRecord.getEnabled());
                    return userDetailsRepository.save(c);
                })
                .orElseGet(() -> {
                    userRecord.setId(id);
                    return userDetailsRepository.save(userRecord);
                });
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userDetailsRepository.deleteById(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Integer id) {

        UserInfo userInfo = trainingDetailsService.getUserInfoById(id);
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
