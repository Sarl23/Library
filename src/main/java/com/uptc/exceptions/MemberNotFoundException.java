package com.uptc.exceptions;

//
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String id) {
        super("No se encontró un socio con id: " + id);
    }
}
