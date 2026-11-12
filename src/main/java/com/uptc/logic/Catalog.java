package com.uptc.logic;

import com.uptc.exceptions.BookAlReadyExistsException;
import com.uptc.exceptions.BookDataInvalidOrEmptyException;
import com.uptc.exceptions.BookNotFoundException;
import com.uptc.models.Book;
import com.uptc.models.BookStatus;
import com.uptc.models.Genre;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements CatalogService {

    private List<Book> books;

    public Catalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new BookDataInvalidOrEmptyException("El libro no puede ser nulo");
        }
        if (book.getIsbn() == null || book.getIsbn().isBlank()
                || book.getTitle() == null || book.getTitle().isBlank()
                || book.getAutor() == null || book.getAutor().isBlank()
                || book.getGenre() == null || book.getStatus() == null) {

            throw new BookDataInvalidOrEmptyException("Todos los datos del libro son obligatorios");
        }
        if (existsIsbn(book.getIsbn())) {
            throw new BookAlReadyExistsException("Ya existe un libro con ese Isbn");
        }
        books.add(book);
    }
    
    private boolean existsIsbn(String isbn){
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return true;
            }
        }
        return false;
    }
    
    public Book findByIsbn(String isbn){
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        throw new BookNotFoundException("Nose encontro un libro con ese Isbn");
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByGenre(Genre Genre) {
        return null;
    }

    @Override
    public void changeStatus(String isbn, BookStatus status) {
    }

    @Override
    public void deleteBook(String isbn) {
    }

    @Override
    public List<Book> listAll() {
        return null;
    }
}
