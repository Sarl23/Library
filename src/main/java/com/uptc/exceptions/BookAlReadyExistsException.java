package com.uptc.exceptions;

public class BookAlReadyExistsException extends RuntimeException{

    public BookAlReadyExistsException(String message) {
        super(message);
    }
    
}
