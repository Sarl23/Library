package com.uptc.models;

/**
 *
 * @author karol
 * @author martha
 */
public class Book {
    String isbn;
    String title;
    String autor;
    Genre genre;
    BookStatus status;

    public Book(String isbn, String title, String autor, Genre genre, BookStatus status) {
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
        this.genre = genre;
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public Genre getGenre() {
        return genre;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book" + "\nisbn=" + isbn + "\ntitulo=" + title + "\nautor=" + autor + "\ngenero=" + genre + "\nestado=" + status;
    }
    
    
}
