package com.uptc.exceptions;

/** Datos inválidos al crear o registrar un socio (vacíos o nulos). Unchecked. */
public class InvalidMemberException extends RuntimeException {
    public InvalidMemberException(String message) {
        super(message);
    }
}
