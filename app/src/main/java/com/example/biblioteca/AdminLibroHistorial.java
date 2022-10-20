package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.adaptadores.ListaAdminHistorialAdapter;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.LibrosPrestados;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;

public class AdminLibroHistorial extends AppCompatActivity {
    TextView milibronombre, milibroautor, milibrodescripcion;
    TextView nombreadmin;
    Administrador administrador;
    Libros libros;
    LibrosPrestados librosPrestados;
    Metodos mtd;
    int id =0;
    RecyclerView listaadminhistorialadapter;
    ArrayList<Usuarios> listaarrayadminhistorialadapter;
    SharedPreference sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_libro_historial);

        nombreadmin = findViewById(R.id.adminnombrehistorial);
        milibronombre = findViewById(R.id.milibronombre);
        milibroautor = findViewById(R.id.milibroautor);
        milibrodescripcion = findViewById(R.id.milibrodescripcion);
        listaadminhistorialadapter = findViewById(R.id.listaadminhistorial);

        libros = new Libros();
        librosPrestados = new LibrosPrestados();
        Metodos mtd = new Metodos(this);
        administrador = new Administrador();
        sp = new SharedPreference(this);
        String prueba = sp.getCorreoAdmin();

        administrador = mtd.vernombreadmin(prueba);


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


        librosPrestados = mtd.verLibroPrestado(id);

        listaadminhistorialadapter.setLayoutManager(new LinearLayoutManager(this));
        listaarrayadminhistorialadapter = new ArrayList<>();
        ListaAdminHistorialAdapter adapter = new ListaAdminHistorialAdapter(mtd.selectadminlibroshistorial(id));
        listaadminhistorialadapter.setAdapter(adapter);

        /*librosPrestados = mtd.verLibroPrestado(id);*/


        if(librosPrestados != null){
            milibronombre.setText(librosPrestados.getNombrelibroprestado());
            milibroautor.setText(librosPrestados.getAutorlibroprestado());
            milibrodescripcion.setText(librosPrestados.getDescripcionlibroprestado());
        }

        if(administrador != null){
            nombreadmin.setText(administrador.getAdminnombre());

        }

    }

    public void regresarAdminHsitorial(View v){
        switch (v.getId()){
            case R.id.regresarhistorial:
                Intent i3 = new Intent(AdminLibroHistorial.this, AdminLibrosPrestados.class);
                startActivity(i3);
                finish();
        }
    }
}