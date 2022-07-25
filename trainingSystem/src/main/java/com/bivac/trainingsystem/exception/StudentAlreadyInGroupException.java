package com.bivac.trainingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentAlreadyInGroupException extends RuntimeException {

    public StudentAlreadyInGroupException(String message) {
        super(message);
    }
}
