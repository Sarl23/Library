package com.uptc.exceptions;

/** No existe un socio con el id buscado. Unchecked. */
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String id) {
        super("No se encontró un socio con id: " + id);
    }
}
