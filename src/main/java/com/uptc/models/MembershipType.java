package com.uptc.models;

/**
 * Tipos de socio con sus reglas de negocio (tabla del enunciado).
 */
public enum MembershipType {
    STUDENT(3, 7, 500),
    TEACHER(5, 15, 300),
    EXTERNAL(2, 5, 1000);

    private final int maxLoans;
    private final int loanDays;
    private final int finePerDay;

    MembershipType(int maxLoans, int loanDays, int finePerDay) {
        this.maxLoans = maxLoans;
        this.loanDays = loanDays;
        this.finePerDay = finePerDay;
    }

    /** Máximo de préstamos activos simultáneos. */
    public int getMaxLoans() {
        return maxLoans;
    }

    /** Días de préstamo (para calcular la fecha de vencimiento). */
    public int getLoanDays() {
        return loanDays;
    }

    /** Multa en pesos por cada día de retraso. */
    public int getFinePerDay() {
        return finePerDay;
    }
}
