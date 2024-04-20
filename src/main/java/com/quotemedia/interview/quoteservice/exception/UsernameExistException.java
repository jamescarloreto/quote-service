package com.quotemedia.interview.quoteservice.exception;

public class UsernameExistException extends RuntimeException {

    public UsernameExistException(String message) {
        super(message);
    }

}
