/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.run;

import com.uptc.fake.FakeCatalog;
import com.uptc.fake.FakeLoans;
import com.uptc.fake.FakeReports;
import com.uptc.logic.CatalogService;
import com.uptc.logic.LoanService;
import com.uptc.logic.MemberRegistry;
import com.uptc.logic.MemberService;
import com.uptc.logic.ReportService;
import com.uptc.presenter.LibraryPresenter;
import com.uptc.view.LibraryView;

import javax.swing.SwingUtilities;
/**
 *
 * @author capit
 */
public class Main {
    
     public static void main(String[] args) {
        CatalogService catalog = new FakeCatalog();                // TODO g1: new Catalog()
        MemberService members = new MemberRegistry();              // real (g2)
        LoanService loans = new FakeLoans(catalog, members);       // TODO g3: new <clase real>(catalog, members)
        ReportService reports = new FakeReports(loans, catalog);   // TODO g4: new <clase real>(...)

        LibraryPresenter presenter = new LibraryPresenter(catalog, members, loans, reports);
        SwingUtilities.invokeLater(() -> new LibraryView(presenter).start());
    }
}
