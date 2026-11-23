package com.uptc.models;

import com.uptc.exceptions.InvalidReturnDateException;
import java.time.LocalDate;

/**
 * Préstamo de un libro a un socio. La fecha de devolución es null mientras
 * el préstamo siga activo.
 *
 * @author Grupo 3
 */
public class Loan {

    private final Book book;
    private final Member member;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    /**
     * Crea un préstamo activo. El vencimiento es la fecha del préstamo más los
     * días de préstamo del tipo de socio.
     *
     * @param book     libro prestado
     * @param member   socio que lo recibe
     * @param loanDate fecha del préstamo
     */
    public Loan(Book book, Member member, LocalDate loanDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = loanDate.plusDays(member.getType().getLoanDays());
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Guarda la fecha de devolución (setter con validación).
     *
     * @param returnDate fecha de devolución; null deja el préstamo activo
     * @throws InvalidReturnDateException si es anterior a la fecha del préstamo
     */
    public void setReturnDate(LocalDate returnDate) {
        if (returnDate != null && returnDate.compareTo(loanDate) < 0) {
            throw new InvalidReturnDateException(returnDate, loanDate);
        }
        this.returnDate = returnDate;
    }

    /**
     * @return true si el libro todavía no se ha devuelto
     */
    public boolean isActive() {
        return returnDate == null;
    }

    @Override
    public String toString() {
        return "Loan{isbn='" + book.getIsbn() + "', member='" + member.getId()
                + "', loanDate=" + loanDate + ", dueDate=" + dueDate
                + ", returnDate=" + returnDate + "}";
    }
}
