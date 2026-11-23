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
import java.util.Arrays;
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

    /** Acción que puede lanzar cualquier excepción; run() se encarga de capturarla. */
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
    
    
}
