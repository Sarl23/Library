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
    
    public Response overdueReport(String dateText) {
        return run(() -> {
            LocalDate date = parseDate(dateText);
            return format("Préstamos vencidos al " + date, reports.overdueLoans(date));
        });
    }

    public Response memberHistory(String memberId) {
        return run(() -> {
            String id = validateMemberId(memberId);
            return format("Historial del socio " + id, reports.memberHistory(id));
        });
    }

    public Response sortedCatalog() {
        return run(() -> format("Catálogo ordenado por título", reports.catalogSortedByTitle()));
    }
    
    public static String validateIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new InvalidInputException("El ISBN no puede estar vacío.");
        }
        String digits = isbn.trim().replace("-", "");
        if (!digits.matches("\\d{10,13}")) {
            throw new InvalidInputException("El ISBN debe tener entre 10 y 13 dígitos (se permiten guiones).");
        }
        return digits;
    }
    
    public static String validateText(String field, String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidInputException(field + " no puede estar vacío.");
        }
        String clean = value.trim();
        if (clean.length() > MAX_TEXT) {
            throw new InvalidInputException(field + " no puede superar " + MAX_TEXT + " caracteres.");
        }
        return clean;
    }
    
    public static String validateMemberId(String id) {
        if (id == null || id.isBlank()) {
            throw new InvalidInputException("El id del socio no puede estar vacío.");
        }
        String clean = id.trim();
        if (!MEMBER_ID.matcher(clean).matches()) {
            throw new InvalidInputException("El id del socio debe tener de 1 a 15 letras o números.");
        }
        return clean;
    }
    
    public static Genre parseGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new InvalidInputException("Debe elegir un género.");
        }
        try {
            return Genre.valueOf(genre.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Género no válido: " + genre.trim());
        }
    }

    public static MembershipType parseMembershipType(String type) {
        if (type == null || type.isBlank()) {
            throw new InvalidInputException("Debe elegir un tipo de socio.");
        }
        try {
            return MembershipType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Tipo de socio no válido: " + type.trim());
        }
    }
    public static LocalDate parseDate(String text) {
        if (text == null || text.isBlank()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(text.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Fecha no válida. Use el formato aaaa-mm-dd (ej. 2026-11-05).");
        }
    }
    
    private static String format(String title, List<?> items) {
        if (items == null || items.isEmpty()) {
            return title + ": no hay resultados.";
        }
        StringBuilder sb = new StringBuilder(title).append(" (").append(items.size()).append("):\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i)).append('\n');
        }
        return sb.toString();
    }

}
