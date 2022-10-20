package com.example.biblioteca.modelos;

import android.util.Log;

public class Administrador {
    int id;
    String adminnombre, admincorreo, admintelefono, admindireccion, admincontrasena;

    public Administrador() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminnombre() {
/*        Log.i("adminnombre", "valor nombre " + adminnombre);*/
        return adminnombre;
    }

    public void setAdminnombre(String adminnombre) {
        this.adminnombre = adminnombre;
    }

    public String getAdmincorreo() {
        return admincorreo;
    }

    public void setAdmincorreo(String admincorreo) {
        this.admincorreo = admincorreo;
    }

    public String getAdmintelefono() {
        return admintelefono;
    }

    public void setAdmintelefono(String admintelefono) {
        this.admintelefono = admintelefono;
    }

    public String getAdmindireccion() {
        return admindireccion;
    }

    public void setAdmindireccion(String admindireccion) {
        this.admindireccion = admindireccion;
    }

    public String getAdmincontrasena() {
        return admincontrasena;
    }

    public void setAdmincontrasena(String admincontrasena) {
        this.admincontrasena = admincontrasena;
    }

    public boolean isEmpty(){

        return adminnombre.isEmpty() || admincorreo.isEmpty() || admindireccion.isEmpty() || admincontrasena.isEmpty();
    }
}
