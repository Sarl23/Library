package com.uptc.exceptions;

/**
 * El socio ya tiene el máximo de préstamos activos de su tipo. Unchecked.
 *
 * @author johann
 */
public class LoanLimitExceededException extends RuntimeException {

    /**
     * @param memberId id del socio
     * @param maxLoans máximo de préstamos activos que permite su tipo
     */
    public LoanLimitExceededException(String memberId, int maxLoans) {
        super("El socio " + memberId + " ya alcanzó el máximo de " + maxLoans + " préstamos activos");
    }
}
