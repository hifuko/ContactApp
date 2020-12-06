package com.computacenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //then springboot will go to 404 page
public class KontaktNotFoundException extends RuntimeException{
    public KontaktNotFoundException() {
    }

    public KontaktNotFoundException(String message) {
        super(message);
    }
}
