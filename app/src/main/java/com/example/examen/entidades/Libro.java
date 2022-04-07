package com.example.examen.entidades;

public class Libro {
    private Integer id;
    private String nombreLibro;
    private String autor;
    private String editorial;

    public Libro(Integer id, String nombreLibro, String autor, String editorial) {
        this.id = id;
        this.nombreLibro = nombreLibro;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
