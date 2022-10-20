package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.DB.DBiblioteca;
import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.Libros;

public class AdminActualizarLibro extends AppCompatActivity {

    EditText txtnombre, txtautor, txtcantidad, txturl, txtimagen, txtdescripcion;
    TextView nombreadmin;
    Administrador administrador;
    Button btnActualizar;
    Libros libro;
    boolean correcto = false;
    Metodos mtd;
    String nombre, autor, cantidad, url, imagen, descripcion;
    SharedPreference sp;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_actualizar_libro);

        nombreadmin = findViewById(R.id.nombreadminactualizar);
        txtnombre = findViewById(R.id.nombrelibroActualizar);
        txtautor = findViewById(R.id.autorlibroActualizar);
        txtcantidad = findViewById(R.id.cantidadlibroActualizar);
        txturl = findViewById(R.id.urllibroActualizar);
        txtimagen = findViewById(R.id.imagenlibroActualizar);
        txtdescripcion = findViewById(R.id.descripcionlibroActualizar);

        btnActualizar = findViewById(R.id.btnActualizar);
        mtd = new Metodos(AdminActualizarLibro.this);
        libro = new Libros();
        administrador = new Administrador();
        sp = new SharedPreference(this);
        String prueba = sp.getCorreoAdmin();
        administrador = mtd.vernombreadmin(prueba);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if(extras == null){
                id= Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }


        if(administrador != null){
            nombreadmin.setText(administrador.getAdminnombre());

        }

        DBiblioteca dBiblioteca = new DBiblioteca(AdminActualizarLibro.this);
        libro = mtd.verLibro(id);


        if(libro != null){
            txtnombre.setText(libro.getNombrelibro());
            txtautor.setText(libro.getAutorlibro());
            txtcantidad.setText(libro.getCantidadlibro());
            txturl.setText(libro.getUrllibro());
            txtimagen.setText(libro.getImagenlibro());
            txtdescripcion.setText(libro.getDescripcionlibro());
        }

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nombre = txtnombre.getText().toString();
                libro.setNombrelibro(nombre);
                autor = txtautor.getText().toString();
                libro.setAutorlibro(autor);
                cantidad = txtcantidad.getText().toString();
                libro.setCantidadlibro(cantidad);
                url = txturl.getText().toString();
                libro.setUrllibro(url);
                imagen = txtimagen.getText().toString();
                libro.setImagenlibro(imagen);
                descripcion = txtdescripcion.getText().toString();
                libro.setDescripcionlibro(descripcion);


                if(!txtnombre.getText().toString().isEmpty() && !txtautor.getText().toString().isEmpty() && !txtcantidad.getText().toString().isEmpty() && !txturl.getText().toString().isEmpty() && !txtimagen.getText().toString().isEmpty() && !txtdescripcion.getText().toString().isEmpty()){
                    correcto = mtd.editarlibro(id, libro);

                    if(correcto){
                        Toast.makeText(AdminActualizarLibro.this, "Libro Modificado", Toast.LENGTH_SHORT).show();
                        vistaAdmin();
                    }else{
                        Toast.makeText(AdminActualizarLibro.this, "Error al Modificar Libro", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AdminActualizarLibro.this, "Debe Llenar los Campos Obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void regresaractualizar(View v){
        switch (v.getId()){
            case R.id.regresarprestado:
                Toast.makeText(AdminActualizarLibro.this, "Inicio", Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(AdminActualizarLibro.this, Vista_admin.class);
                startActivity(i3);
                finish();
        }
    }

    private void vistaAdmin(){
        Intent intent = new Intent(this, Vista_admin.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }


}