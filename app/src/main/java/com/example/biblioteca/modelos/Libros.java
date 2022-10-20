package com.example.biblioteca.modelos;

public class Libros {
    int id;
    String nombrelibro, autorlibro, cantidadlibro, urllibro, imagenlibro, descripcionlibro;

    public Libros() {
    }

    public Libros(String nombrelibro, String autorlibro, String cantidadlibro, String urllibro, String imagenlibro, String descripcionlibro) {
        this.nombrelibro = nombrelibro;
        this.autorlibro = autorlibro;
        this.cantidadlibro = cantidadlibro;
        this.urllibro = urllibro;
        this.imagenlibro = imagenlibro;
        this.descripcionlibro = descripcionlibro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrelibro() {
        return nombrelibro;
    }

    public void setNombrelibro(String nombrelibro) {
        this.nombrelibro = nombrelibro;
    }

    public String getAutorlibro() {
        return autorlibro;
    }

    public void setAutorlibro(String autorlibro) {
        this.autorlibro = autorlibro;
    }

    public String getCantidadlibro() {
        return cantidadlibro;
    }

    public void setCantidadlibro(String cantidadlibro) {
        this.cantidadlibro = cantidadlibro;
    }

    public String getUrllibro() {
        return urllibro;
    }

    public void setUrllibro(String urllibro) {
        this.urllibro = urllibro;
    }

    public String getImagenlibro() {
        return imagenlibro;
    }

    public void setImagenlibro(String imagenlibro) {
        this.imagenlibro = imagenlibro;
    }

    public String getDescripcionlibro() {
        return descripcionlibro;
    }

    public void setDescripcionlibro(String descripcionlibro) {
        this.descripcionlibro = descripcionlibro;
    }

    public boolean isEmpty(){

        return nombrelibro.isEmpty() || autorlibro.isEmpty() || cantidadlibro.isEmpty() || urllibro.isEmpty() || imagenlibro.isEmpty() || descripcionlibro.isEmpty();
    }
}
