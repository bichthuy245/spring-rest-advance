package com.nashtech.training.shape.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequest implements Serializable {

    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
