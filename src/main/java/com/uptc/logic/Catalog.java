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
        List <Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
                
            }
            
        }
        return result;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        List <Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre()== genre ) {
                result.add(book);
                
            }
            
        }
        return result;
    }

    @Override
    public void changeStatus(String isbn, BookStatus status) {
        if (status==null) {
            throw new BookDataInvalidOrEmptyException("El estado no puede ser nulo");
            
        }
        Book book=findByIsbn(isbn);
        book.setStatus(status);
    }

    @Override
    public void deleteBook(String isbn) {
        Book book=findByIsbn(isbn);
        books.remove(book);
    }

    @Override
    public List<Book> listAll() {
        return new ArrayList<>(books);
    }
}
