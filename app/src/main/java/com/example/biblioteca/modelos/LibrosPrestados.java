package com.example.biblioteca.modelos;

public class LibrosPrestados {
    int id;
    int idlibro;
    String nombrelibroprestado, autorlibroprestado, cantidadlibroprestado, urllibroprestado, imagenlibroprestado, descripcionlibroprestado;
    String correo_user;

    public LibrosPrestados() {

    }

    public LibrosPrestados(String nombrelibroprestado, String autorlibroprestado, String cantidadlibroprestado, String urllibroprestado, String imagenlibroprestado, String descripcionlibroprestado, String correo_user, int idlibro) {
        this.nombrelibroprestado = nombrelibroprestado;
        this.autorlibroprestado = autorlibroprestado;
        this.cantidadlibroprestado = cantidadlibroprestado;
        this.urllibroprestado = urllibroprestado;
        this.imagenlibroprestado = imagenlibroprestado;
        this.descripcionlibroprestado = descripcionlibroprestado;
        this.correo_user =correo_user;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getNombrelibroprestado() {
        return nombrelibroprestado;
    }

    public void setNombrelibroprestado(String nombrelibroprestado) {
        this.nombrelibroprestado = nombrelibroprestado;
    }

    public String getAutorlibroprestado() {
        return autorlibroprestado;
    }

    public void setAutorlibroprestado(String autorlibroprestado) {
        this.autorlibroprestado = autorlibroprestado;
    }

    public String getCantidadlibroprestado() {
        return cantidadlibroprestado;
    }

    public void setCantidadlibroprestado(String cantidadlibroprestado) {
        this.cantidadlibroprestado = cantidadlibroprestado;
    }

    public String getUrllibroprestado() {
        return urllibroprestado;
    }

    public void setUrllibroprestado(String urllibroprestado) {
        this.urllibroprestado = urllibroprestado;
    }

    public String getImagenlibroprestado() {
        return imagenlibroprestado;
    }

    public void setImagenlibroprestado(String imagenlibroprestado) {
        this.imagenlibroprestado = imagenlibroprestado;
    }

    public String getDescripcionlibroprestado() {
        return descripcionlibroprestado;
    }

    public void setDescripcionlibroprestado(String descripcionlibroprestado) {
        this.descripcionlibroprestado = descripcionlibroprestado;
    }

    public String getCorreo_user() {
        return correo_user;
    }

    public void setCorreo_user(String correo_user) {
        this.correo_user = correo_user;
    }
}
