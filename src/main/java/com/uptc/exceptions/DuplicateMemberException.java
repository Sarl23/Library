package com.uptc.exceptions;

//
public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String id) {
        super("Ya existe un socio con id: " + id);
    }
}
