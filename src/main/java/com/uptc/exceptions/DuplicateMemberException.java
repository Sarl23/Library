package com.uptc.exceptions;

/** Ya existe un socio con el mismo id. Unchecked. */
public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String id) {
        super("Ya existe un socio con id: " + id);
    }
}
