package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.DB.DBiblioteca;
import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.adaptadores.ListaLibroAdapter;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;

public class Vista_admin extends AppCompatActivity implements SearchView.OnQueryTextListener {

    TextView nombreadmin;
    SearchView txtbuscaradmin;
    Usuarios usuarios;
    Libros libros;
    Administrador administrador;
    Metodos mtd;
    int id = 0;
    RecyclerView listalibros;
    ArrayList<Libros> listaArrayLibros;
    SharedPreference sp;
    ListaLibroAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_admin);

        txtbuscaradmin = findViewById(R.id.txtbuscaradmin);
        nombreadmin = findViewById(R.id.nombreadmin);
        listalibros = findViewById(R.id.listalibros);

        listalibros.setLayoutManager(new GridLayoutManager(this, 2));

        DBiblioteca dBiblioteca = new DBiblioteca(this);
        Metodos mtd = new Metodos(this);
        administrador = new Administrador();
        libros = new Libros();

        sp = new SharedPreference(this);

        listaArrayLibros = new ArrayList<>();

        String prueba = sp.getCorreoAdmin();
        administrador = mtd.vernombreadmin(prueba);

        adapter = new ListaLibroAdapter(mtd.selectlibros());
        listalibros.setAdapter(adapter);




        if(administrador != null){
            nombreadmin.setText(administrador.getAdminnombre());

        }

        txtbuscaradmin.setOnQueryTextListener(this);

    }

    public void mostrarpopup(View v){
        ImageView iv1 = findViewById(R.id.regresarprestado);
        PopupMenu pu = new PopupMenu(this,iv1);
        pu.getMenuInflater().inflate(R.menu.menupopup, pu.getMenu());
        pu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.prestados:
                        Intent i1 = new Intent(Vista_admin.this, AdminLibrosPrestados.class);
                        startActivity(i1);
                        finish();
                        return  true;
                    case R.id.agregar:
                        Intent i5 = new Intent(Vista_admin.this, AdminAgregarLibro.class);
                        startActivity(i5);
                        finish();
                        return true;
                    case R.id.salir:
                        Toast.makeText(Vista_admin.this, "Cerrando Sesion", Toast.LENGTH_SHORT).show();
                        Intent i4 = new Intent(Vista_admin.this, Login.class);
                        startActivity(i4);
                        finish();
                        return true;

                }
                return false;
            }
        });
        pu.show();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtradoadmin(s);
        return false;
    }
}