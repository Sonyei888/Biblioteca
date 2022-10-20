package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.LibrosPrestados;
import com.example.biblioteca.modelos.Usuarios;

public class UsuPrestarLibro extends AppCompatActivity implements View.OnClickListener {

    TextView infnombre, infautor, infdescripcion;
    Libros libros;

    boolean correcto = false;
    Metodos mtd;
    Button btnPrestar;
    SharedPreference sp;
    TextView nombreusuario;
    Usuarios usuarios;

    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_prestar_libro);

        nombreusuario = findViewById(R.id.nombreusuprestar);
        infnombre = findViewById(R.id.infnombre);
        infautor =  findViewById(R.id.infautor);
        infdescripcion =  findViewById(R.id.infdescripcion);
        btnPrestar = findViewById(R.id.btnPrestar);

        btnPrestar.setOnClickListener(this);

        sp = new SharedPreference(this);

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
        libros = new Libros();

        Metodos mtd = new Metodos(this);
        libros = mtd.verLibro(id);

        String prueba = sp.getCorreoUsuario();
        usuarios = mtd.vernombreusuario(prueba);
        if (libros != null) {
            infnombre.setText(libros.getNombrelibro());
            infautor.setText(libros.getAutorlibro());
            infdescripcion.setText(libros.getDescripcionlibro());

            infnombre.setInputType(InputType.TYPE_NULL);
            infautor.setInputType(InputType.TYPE_NULL);
            /*infdescripcion.setInputType(InputType.TYPE_NULL);*/
        }

        if(usuarios != null){
            nombreusuario.setText(usuarios.getNombre());

        }
    }

    public void regresarusurprestrar(View v){
        switch (v.getId()){
            case R.id.regresarprestar:
                Intent i3 = new Intent(UsuPrestarLibro.this, UsuLibrosDisponibles.class);
                startActivity(i3);
                finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPrestar:
                mtd= new Metodos(this);
                LibrosPrestados librosPrestados = new LibrosPrestados();

                librosPrestados.setId(libros.getId());
                librosPrestados.setNombrelibroprestado(libros.getNombrelibro());
                librosPrestados.setAutorlibroprestado(libros.getAutorlibro());
                librosPrestados.setCantidadlibroprestado(libros.getCantidadlibro());
                librosPrestados.setUrllibroprestado(libros.getUrllibro());
                librosPrestados.setImagenlibroprestado(libros.getImagenlibro());
                librosPrestados.setDescripcionlibroprestado(libros.getDescripcionlibro());

                if(mtd.insertLibroprestados(librosPrestados)){

                    mtd.actualizarcantidad(libros, librosPrestados);
                    Toast.makeText(this, "Prestamo Exitoso", Toast.LENGTH_LONG).show();
                    Intent i7 = new Intent(UsuPrestarLibro.this, Vista_usuario.class);
                    startActivity(i7);
                }else{
                    Toast.makeText(this, "Libro ya Prestado", Toast.LENGTH_LONG).show();
                }
        }
    }

}
