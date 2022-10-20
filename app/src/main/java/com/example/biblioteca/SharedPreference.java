package com.example.biblioteca;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreference(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("librosprestados", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setCorreoUsuario(String correoUsuario){
        editor.putString("correo_usuario", correoUsuario);
        editor.apply();
    }

    public String getCorreoUsuario(){
        return sharedPreferences.getString("correo_usuario", "NO ENCONTRADO");
    }
    public void setCorreoAdmin(String correoAdmin){
        editor.putString("correo_Admin", correoAdmin);
        editor.apply();
    }

    public String getCorreoAdmin(){
        return sharedPreferences.getString("correo_Admin", "NO ENCONTRADO");
    }
}
