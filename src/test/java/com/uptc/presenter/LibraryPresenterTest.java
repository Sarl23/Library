/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.uptc.presenter;

import com.uptc.exceptions.InvalidInputException;
import com.uptc.models.Genre;
import com.uptc.models.MembershipType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author capit
 */
public class LibraryPresenterTest {
     @Test
    void validateIsbn_removesHyphens() {
        assertEquals("9780132350884", LibraryPresenter.validateIsbn("978-0-13-235088-4"));
    }

    @Test
    void validateIsbn_blankThrows() {
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateIsbn("   "));
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateIsbn(null));
    }

    @Test
    void validateIsbn_lettersOrWrongLengthThrow() {
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateIsbn("12345ABCDE"));
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateIsbn("12345"));
    }

    @Test
    void validateText_trimsSpaces() {
        assertEquals("Clean Code", LibraryPresenter.validateText("El título", "  Clean Code "));
    }

    @Test
    void validateText_blankThrows() {
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateText("El autor", ""));
    }

    @Test
    void validateText_tooLongThrows() {
        String longText = "a".repeat(101);
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateText("El título", longText));
    }

    @Test
    void validateMemberId_acceptsAlphanumeric() {
        assertEquals("1052398", LibraryPresenter.validateMemberId(" 1052398 "));
    }

    @Test
    void validateMemberId_tooLongOrSymbolsThrow() {
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateMemberId("a".repeat(16)));
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.validateMemberId("ab#12"));
    }

    @Test
    void parseGenre_isCaseInsensitive() {
        assertEquals(Genre.SCIENCE, LibraryPresenter.parseGenre("science"));
    }

    @Test
    void parseGenre_unknownOrNullThrows() {
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.parseGenre("POETRY"));
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.parseGenre(null));
    }

    @Test
    void parseMembershipType_validAndInvalid() {
        assertEquals(MembershipType.TEACHER, LibraryPresenter.parseMembershipType("teacher"));
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.parseMembershipType("VIP"));
    }

    @Test
    void parseDate_validIsoDate() {
        assertEquals(LocalDate.of(2026, 11, 5), LibraryPresenter.parseDate("2026-11-05"));
    }

    @Test
    void parseDate_blankMeansToday() {
        assertEquals(LocalDate.now(), LibraryPresenter.parseDate(""));
    }

    @Test
    void parseDate_wrongFormatThrows() {
        assertThrows(InvalidInputException.class, () -> LibraryPresenter.parseDate("05/11/2026"));
    }
    
}
