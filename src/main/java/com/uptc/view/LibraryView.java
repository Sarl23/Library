/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.view;

import com.uptc.presenter.LibraryPresenter;
import com.uptc.presenter.Response;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
/**
 *
 * @author capit
 */
public class LibraryView {
    
    private static final String TITLE = "Biblioteca";
    private static final String BACK = "Volver";

    private final LibraryPresenter presenter;

    public LibraryView(LibraryPresenter presenter) {
        this.presenter = presenter;
    }
    public void start() {
        boolean running = true;
        while (running) {
            int option = menu("Menú principal", "Catálogo", "Socios", "Préstamos", "Reportes", "Salir");
            switch (option) {
                case 0 -> catalogMenu();
                case 1 -> membersMenu();
                case 2 -> loansMenu();
                case 3 -> reportsMenu();
                default -> running = false;
            }
        }
        JOptionPane.showMessageDialog(null, "¡Hasta pronto!", TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
    private void catalogMenu() {
        boolean back = false;
        while (!back) {
            int option = menu("Catálogo", "Agregar libro", "Buscar por ISBN", "Listar libros", BACK);
            switch (option) {
                case 0 -> addBook();
                case 1 -> findBook();
                case 2 -> show(presenter.listBooks());
                default -> back = true;
            }
        }
    }

    private void membersMenu() {
        boolean back = false;
        while (!back) {
            int option = menu("Socios", "Registrar socio", "Buscar por id", BACK);
            switch (option) {
                case 0 -> registerMember();
                case 1 -> findMember();
                default -> back = true;
            }
        }
    }

    private void loansMenu() {
        boolean back = false;
        while (!back) {
            int option = menu("Préstamos", "Prestar libro", "Devolver libro", BACK);
            switch (option) {
                case 0 -> lendBook();
                case 1 -> returnBook();
                default -> back = true;
            }
        }
    }

    private void reportsMenu() {
        boolean back = false;
        while (!back) {
            int option = menu("Reportes", "Préstamos vencidos", "Historial de un socio",
                    "Catálogo ordenado por título", BACK);
            switch (option) {
                case 0 -> overdue();
                case 1 -> history();
                case 2 -> show(presenter.sortedCatalog());
                default -> back = true;
            }
        }
    }
    private void addBook() {
        String[] data = ask("ISBN (10 a 13 dígitos):", "Título:", "Autor:");
        if (data == null) return;
        String genre = choose("Género:", presenter.genreNames());
        if (genre == null) return;
        show(presenter.addBook(data[0], data[1], data[2], genre));
    }

    private void findBook() {
        String[] data = ask("ISBN:");
        if (data != null) show(presenter.findBook(data[0]));
    }

    private void registerMember() {
        String[] data = ask("Id del socio (1 a 15 letras o números):", "Nombre:");
        if (data == null) return;
        String type = choose("Tipo de socio:", presenter.membershipTypeNames());
        if (type == null) return;
        show(presenter.registerMember(data[0], data[1], type));
    }
     private void findMember() {
        String[] data = ask("Id del socio:");
        if (data != null) show(presenter.findMember(data[0]));
    }

    private void lendBook() {
        String[] data = ask("ISBN del libro:", "Id del socio:", "Fecha del préstamo (aaaa-mm-dd, vacío = hoy):");
        if (data != null) show(presenter.lendBook(data[0], data[1], data[2]));
    }

    private void returnBook() {
        String[] data = ask("ISBN del libro:", "Fecha de devolución (aaaa-mm-dd, vacío = hoy):");
        if (data != null) show(presenter.returnBook(data[0], data[1]));
    }

    private void overdue() {
        String[] data = ask("Vencidos a la fecha (aaaa-mm-dd, vacío = hoy):");
        if (data != null) show(presenter.overdueReport(data[0]));
    }

    private void history() {
        String[] data = ask("Id del socio:");
        if (data != null) show(presenter.memberHistory(data[0]));
    }
     private int menu(String title, String... options) {
        return JOptionPane.showOptionDialog(null, title, TITLE, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
     private String[] ask(String... labels) {
        String[] values = new String[labels.length];
        for (int i = 0; i < labels.length; i++) {
            String value = JOptionPane.showInputDialog(null, labels[i], TITLE, JOptionPane.QUESTION_MESSAGE);
            if (value == null) return null;
            values[i] = value;
        }
        return values;
    }
      private String choose(String label, String[] options) {
        Object selected = JOptionPane.showInputDialog(null, label, TITLE,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return selected == null ? null : selected.toString();
    }

    private void show(Response response) {
        int type = response.ok() ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        String message = response.message();
        Object content = message;
        if (message.lines().count() > 8) {
            JTextArea area = new JTextArea(message);
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(520, 300));
            content = scroll;
        }
        JOptionPane.showMessageDialog(null, content, TITLE, type);
    }
}
