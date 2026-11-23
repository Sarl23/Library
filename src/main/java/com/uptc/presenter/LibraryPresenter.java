/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.presenter;

import com.uptc.exceptions.DuplicateMemberException;
import com.uptc.exceptions.InvalidInputException;
import com.uptc.exceptions.InvalidMemberException;
import com.uptc.exceptions.MemberNotFoundException;
import com.uptc.logic.CatalogService;
import com.uptc.logic.LoanService;
import com.uptc.logic.MemberService;
import com.uptc.logic.ReportService;
import com.uptc.models.Book;
import com.uptc.models.BookStatus;
import com.uptc.models.Genre;
import com.uptc.models.Member;
import com.uptc.models.MembershipType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author capit
 */
public class LibraryPresenter {

    private static final Pattern MEMBER_ID = Pattern.compile("[A-Za-z0-9]{1,15}");
    private static final int MAX_TEXT = 100;

    private final CatalogService catalog;
    private final MemberService members;
    private final LoanService loans;
    private final ReportService reports;

    private interface Action {

        String execute() throws Exception;
    }

    public LibraryPresenter(CatalogService catalog, MemberService members,
            LoanService loans, ReportService reports) {
        this.catalog = catalog;
        this.members = members;
        this.loans = loans;
        this.reports = reports;
    }

    private Response run(Action action) {
        try {
            return Response.success(action.execute());
        } catch (InvalidInputException | InvalidMemberException
                | DuplicateMemberException | MemberNotFoundException e) {
            return Response.failure(e.getMessage());
        } catch (Exception e) {
            String msg = e.getMessage();
            return Response.failure(msg == null || msg.isBlank() ? e.getClass().getSimpleName() : msg);
        }
    }

    public String[] genreNames() {
        Genre[] values = Genre.values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return names;
    }

    public String[] membershipTypeNames() {
        MembershipType[] values = MembershipType.values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return names;
    }

    public Response addBook(String isbn, String title, String author, String genre) {
        return run(() -> {
            Book book = new Book(validateIsbn(isbn), validateText("El título", title),
                    validateText("El autor", author), parseGenre(genre), BookStatus.AVAILABLE);
            catalog.addBook(book);
            return "Libro agregado:\n" + book;
        });
    }

    public Response findBook(String isbn) {
        return run(() -> String.valueOf(catalog.findByIsbn(validateIsbn(isbn))));
    }

    public Response listBooks() {
        return run(() -> format("Catálogo", catalog.listAll()));
    }

    public Response registerMember(String id, String name, String type) {
        return run(() -> {
            Member member = new Member(validateMemberId(id), validateText("El nombre", name),
                    parseMembershipType(type));
            members.register(member);
            return "Socio registrado:\n" + member;
        });
    }

    public Response findMember(String id) {
        return run(() -> String.valueOf(members.findById(validateMemberId(id))));
    }

    public Response lendBook(String isbn, String memberId, String dateText) {
        return run(() -> {
            Object loan = loans.lendBook(validateIsbn(isbn), validateMemberId(memberId),
                    parseDate(dateText));
            return "Préstamo registrado:\n" + loan;
        });
    }

    public Response returnBook(String isbn, String dateText) {
        return run(() -> {
            Object loan = loans.returnBook(validateIsbn(isbn), parseDate(dateText));
            return "Devolución registrada:\n" + loan;
        });
    }

}
