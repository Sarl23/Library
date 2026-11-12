package com.uptc.logic;

import java.util.List;
import com.uptc.models.Book;
import com.uptc.models.BookStatus;
import com.uptc.models.Genre;

public interface CatalogService {
    
    void addBook(Book book);
    Book findByIsbn(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByGenre(Genre Genre);
    void changeStatus(String isbn, BookStatus status);
    void deleteBook(String isbn);
    List<Book> listAll();
    
}
