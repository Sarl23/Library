package com.uptc.exceptions;

import java.time.LocalDate;

/**
 * La fecha de devolución es anterior a la fecha del préstamo. Unchecked.
 *
 * @author Grupo 3
 */
public class InvalidReturnDateException extends RuntimeException {

    /**
     * @param returnDate fecha de devolución recibida
     * @param loanDate   fecha en que se hizo el préstamo
     */
    public InvalidReturnDateException(LocalDate returnDate, LocalDate loanDate) {
        super("La fecha de devolución " + returnDate + " no puede ser anterior a la fecha del préstamo " + loanDate);
    }
}
