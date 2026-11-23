package com.uptc.exceptions;

import com.uptc.models.BookStatus;

/**
 * El libro no se puede prestar porque su estado no es AVAILABLE. Unchecked.
 *
 * @author johann
 */
public class BookNotAvailableException extends RuntimeException {

    /**
     * @param isbn   ISBN del libro solicitado
     * @param status estado actual del libro
     */
    public BookNotAvailableException(String isbn, BookStatus status) {
        super("El libro con ISBN " + isbn + " no está disponible (estado: " + status + ")");
    }
}
