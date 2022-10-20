package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.LibrosPrestados;
import com.example.biblioteca.modelos.Usuarios;

public class UsuMiLibro extends AppCompatActivity implements View.OnClickListener {

    TextView nombremilibro, autormilibro, descripcionmilibro;
    TextView nombreusuario;
    Usuarios usuarios;
    LibrosPrestados librosPrestados;
    Metodos mtd;
    Button btnDevolver, btnVer;
    SharedPreference sp;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_mi_libro);

        nombreusuario = findViewById(R.id.nombreusumilibro);
        nombremilibro = findViewById(R.id.nombremilibro);
        autormilibro = findViewById(R.id.autormilibro);
        descripcionmilibro = findViewById(R.id.descripcionmilibro);

        btnDevolver = findViewById(R.id.btnDevolver);
        btnVer = findViewById(R.id.btnVer);


        btnDevolver.setOnClickListener(this);
        btnVer.setOnClickListener(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");

        }
        librosPrestados = new LibrosPrestados();
        sp = new SharedPreference(this);
        usuarios = new Usuarios();


        Metodos mtd = new Metodos(this);
        sp = new SharedPreference(this);
        usuarios = new Usuarios();
        librosPrestados = mtd.verLibroPrestado(id);

        String prueba = sp.getCorreoUsuario();
        usuarios = mtd.vernombreusuario(prueba);

        if(librosPrestados != null){
            nombremilibro.setText(librosPrestados.getNombrelibroprestado());
            autormilibro.setText(librosPrestados.getAutorlibroprestado());
            descripcionmilibro.setText(librosPrestados.getDescripcionlibroprestado());
        }
        if(usuarios != null){
            nombreusuario.setText(usuarios.getNombre());

        }
    }

    public void regresarusurmilibro(View v){
        switch (v.getId()){
            case R.id.regresarmilibro:
                Intent i3 = new Intent(UsuMiLibro.this, Vista_usuario.class);
                startActivity(i3);
                finish();
                break;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnDevolver:

                mtd= new Metodos(this);
                Libros libros = new Libros();

                libros.setNombrelibro(librosPrestados.getNombrelibroprestado());
                libros.setAutorlibro(librosPrestados.getAutorlibroprestado());
                libros.setCantidadlibro(librosPrestados.getCantidadlibroprestado());
                libros.setUrllibro(librosPrestados.getUrllibroprestado());
                libros.setImagenlibro(librosPrestados.getImagenlibroprestado());
                libros.setDescripcionlibro(librosPrestados.getDescripcionlibroprestado());

                if(mtd.eliminarLibroprestados(librosPrestados)){
                    mtd.actualizarcantidadsuma(libros, librosPrestados);
                    Toast.makeText(this, "Libro Eliminado", Toast.LENGTH_LONG).show();
                    Intent i8 = new Intent(UsuMiLibro.this, Vista_usuario.class);
                    startActivity(i8);
                }else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnVer:
                Uri uri = Uri.parse(librosPrestados.getUrllibroprestado());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

        }
    }
}