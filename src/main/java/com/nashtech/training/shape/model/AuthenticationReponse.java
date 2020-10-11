package com.nashtech.training.shape.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuthenticationReponse implements Serializable {
    private final String jwt;


    public AuthenticationReponse(String jwt) {
        this.jwt = jwt;
    }

}
