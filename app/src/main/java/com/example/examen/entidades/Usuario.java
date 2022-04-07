package com.example.examen.entidades;

public class Usuario {
    private Integer id;
    private String nombre;
    private String pwd;

    public Usuario(Integer id, String nombre, String pwd) {
        this.id = id;
        this.nombre = nombre;
        this.pwd = pwd;
    }

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
