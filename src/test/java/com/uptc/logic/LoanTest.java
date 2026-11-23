package com.uptc.logic;

import com.uptc.exceptions.InvalidReturnDateException;
import com.uptc.models.Book;
import com.uptc.models.BookStatus;
import com.uptc.models.Genre;
import com.uptc.models.Loan;
import com.uptc.models.Member;
import com.uptc.models.MembershipType;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del modelo Loan: vencimiento y setter con validación.
 *
 * @author johann
 */
public class LoanTest {

    private Loan loan;
    private LocalDate loanDate;

    @BeforeEach
    public void setUp() {
        Book book = new Book("ISBN1", "Java", "Autor", Genre.TECHNOLOGY, BookStatus.BORROWED);
        Member member = new Member("S1", "Ana", MembershipType.STUDENT);
        loanDate = LocalDate.of(2026, 11, 21);
        loan = new Loan(book, member, loanDate);
    }

    @Test
    public void newLoanIsActiveWithoutReturnDate() {
        assertTrue(loan.isActive());
        assertNull(loan.getReturnDate());
    }

    @Test
    public void setReturnDateOnSameDayIsValid() {
        loan.setReturnDate(loanDate);

        assertEquals(loanDate, loan.getReturnDate());
        assertFalse(loan.isActive());
    }

    @Test
    public void setReturnDateBeforeLoanDateThrows() {
        assertThrows(InvalidReturnDateException.class,
                () -> loan.setReturnDate(loanDate.minusDays(1)));
        assertNull(loan.getReturnDate());
    }
}
