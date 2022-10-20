package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.adaptadores.ListaAdminPrestamoAdapter;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.LibrosPrestados;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;

public class AdminLibrosPrestados extends AppCompatActivity implements SearchView.OnQueryTextListener {
    TextView nombreadmin;
    SearchView  txtbuscaradminprestados;
    Administrador administrador;
    RecyclerView listalibrosadminprestados;
    ArrayList<Usuarios> listaArrayadminLibrosprestados;
    SharedPreference sp;
    ListaAdminPrestamoAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_libros_prestados);

        nombreadmin = findViewById(R.id.nombreadminprestados);
        listalibrosadminprestados = findViewById(R.id.listalibrosadminprestados);
        txtbuscaradminprestados = findViewById(R.id.txtbuscaradminprestados);
        listalibrosadminprestados.setLayoutManager(new GridLayoutManager(this, 2));
        Metodos mtd = new Metodos(this);
        administrador = new Administrador();
        sp = new SharedPreference(this);
        String prueba = sp.getCorreoAdmin();
        administrador = mtd.vernombreadmin(prueba);

        listaArrayadminLibrosprestados = new ArrayList<>();
        adapter = new ListaAdminPrestamoAdapter(mtd.selectadminlibrosprestados());
        listalibrosadminprestados.setAdapter(adapter);

        if(administrador != null){
            nombreadmin.setText(administrador.getAdminnombre());

        }

        txtbuscaradminprestados.setOnQueryTextListener(this);
    }

    public void regresarprestados(View v){
        switch (v.getId()){
            case R.id.regresarprestado:
                Toast.makeText(AdminLibrosPrestados.this, "Inicio", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent( AdminLibrosPrestados.this, Vista_admin.class);
                startActivity(i2);
                finish();

                
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}