package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.Libros;

public class AdminAgregarLibro extends AppCompatActivity implements View.OnClickListener {
    EditText nombrel;
    TextView nombreadmin;
    Administrador administrador;
    EditText autorl;
    EditText cantidadl;
    EditText urll;
    EditText imagenl;
    EditText descripcionl;
    Button agregarl;
    Metodos mtd;
    SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_libro);

        nombreadmin = findViewById(R.id.nombreadminprestadosagregar);
        nombrel=(EditText) findViewById(R.id.nombrelibro);
        autorl=(EditText) findViewById(R.id.autorlibro);
        cantidadl=(EditText) findViewById(R.id.cantidadlibro);
        urll=(EditText) findViewById(R.id.urllibro);
        imagenl=(EditText) findViewById(R.id.imagenlibro);
        descripcionl=(EditText) findViewById(R.id.descripcionlibro);

        agregarl=(Button) findViewById(R.id.btnAgregar);
        agregarl.setOnClickListener(this);


        Metodos mtd = new Metodos(this);
        administrador = new Administrador();
        sp = new SharedPreference(this);
        String prueba = sp.getCorreoAdmin();
        administrador = mtd.vernombreadmin(prueba);

        Log.i("adminnombre", "valor nombre " + administrador.getAdminnombre());
        if(administrador != null){
            nombreadmin.setText(administrador.getAdminnombre());

        }
    }

    public void regresaragregar(View v){
        switch (v.getId()){
            case R.id.regresarprestado:
                Toast.makeText(AdminAgregarLibro.this, "Inicio", Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(AdminAgregarLibro.this, Vista_admin.class);
                startActivity(i3);
                finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                Libros l = new Libros();

                l.setNombrelibro(nombrel.getText().toString());
                l.setAutorlibro(autorl.getText().toString());
                l.setCantidadlibro(cantidadl.getText().toString());
                l.setUrllibro(urll.getText().toString());
                l.setImagenlibro(imagenl.getText().toString());
                l.setDescripcionlibro(descripcionl.getText().toString());

                if(l.isEmpty()){
                    Toast.makeText(this, "ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(mtd.insertLibro(l)){
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent i6 = new Intent(AdminAgregarLibro.this, Vista_admin.class);
                    startActivity(i6);
                }else{
                    Toast.makeText(this, "ERROR: Libro ya Registrado", Toast.LENGTH_LONG).show();
                }

        }


    }
}