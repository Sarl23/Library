package com.uptc.exceptions;

/**
 * No existe un préstamo activo para el libro indicado. Unchecked.
 *
 * @author Grupo 3
 */
public class LoanNotFoundException extends RuntimeException {

    /**
     * @param isbn ISBN del libro que se quiso devolver
     */
    public LoanNotFoundException(String isbn) {
        super("No hay un préstamo activo para el libro con ISBN " + isbn);
    }
}
