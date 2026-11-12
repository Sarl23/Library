package com.uptc.models;

/**
 *
 * @author karol
 * @author martha
 */
public class Book {
    String isbn;
    String titulo;
    String autor;
    String genero;
    String estado;

    public Book(String isbn, String titulo, String autor, String genero, String estado) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.estado = estado;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Book" + "\nisbn=" + isbn + "\ntitulo=" + titulo + "\nautor=" + autor + "\ngenero=" + genero + "\nestado=" + estado;
    }
    
    
}
