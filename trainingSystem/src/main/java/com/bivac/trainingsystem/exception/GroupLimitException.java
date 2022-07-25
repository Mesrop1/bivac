package com.bivac.trainingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GroupLimitException extends RuntimeException {

    public GroupLimitException(String message) {
        super(message);
    }

}
