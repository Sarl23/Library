package com.uptc.logic;

import com.uptc.models.Loan;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio de préstamos. Los demás módulos dependen de esta interfaz.
 *
 * @author johann
 */
public interface LoanService {

    /**
     * Presta un libro a un socio.
     *
     * @param isbn     ISBN del libro
     * @param memberId id del socio
     * @param loanDate fecha del préstamo
     * @return el préstamo creado
     * @throws com.uptc.exceptions.BookNotFoundException       si no existe el libro
     * @throws com.uptc.exceptions.MemberNotFoundException     si no existe el socio
     * @throws com.uptc.exceptions.BookNotAvailableException   si el libro no está AVAILABLE
     * @throws com.uptc.exceptions.LoanLimitExceededException  si el socio superó su máximo
     */
    Loan lend(String isbn, String memberId, LocalDate loanDate);

    /**
     * Devuelve un libro prestado.
     *
     * @param isbn       ISBN del libro
     * @param returnDate fecha de devolución
     * @return el préstamo ya cerrado
     * @throws com.uptc.exceptions.LoanNotFoundException       si el libro no tiene préstamo activo
     * @throws com.uptc.exceptions.InvalidReturnDateException  si la fecha es anterior al préstamo
     */
    Loan returnBook(String isbn, LocalDate returnDate);

    /**
     * @param memberId id del socio
     * @return préstamos activos del socio
     */
    List<Loan> listActiveByMember(String memberId);

    /**
     * @return todos los préstamos, activos y devueltos
     */
    List<Loan> listAll();
}
