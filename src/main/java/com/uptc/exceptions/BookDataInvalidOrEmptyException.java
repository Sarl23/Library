package com.uptc.exceptions;

public class BookDataInvalidOrEmptyException extends RuntimeException{

    public BookDataInvalidOrEmptyException(String message) {
        super(message);
    }
    
}
