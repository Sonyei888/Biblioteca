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
import com.example.biblioteca.adaptadores.ListaLibroAdapter;
import com.example.biblioteca.adaptadores.ListaLibroDisponibleAdapter;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;

public class UsuLibrosDisponibles extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView listalibrosdisponibles;
    SearchView txtbuscardisponible;
    ArrayList<Libros> listalibrosArraydisponibles;
    TextView nombreusuario;
    Usuarios usuarios;
    SharedPreference sp;
    ListaLibroDisponibleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_libros_disponibles);

        nombreusuario = findViewById(R.id.nombreusudisponibles);
        listalibrosdisponibles = findViewById(R.id.listalibrosdisponibles);
        txtbuscardisponible = findViewById(R.id.txtbuscardisponible);
        listalibrosdisponibles.setLayoutManager(new GridLayoutManager(this, 2));

        Metodos mtd = new Metodos(this);
        usuarios = new Usuarios();
        sp = new SharedPreference(this);

        String prueba = sp.getCorreoUsuario();
        usuarios = mtd.vernombreusuario(prueba);

        listalibrosArraydisponibles = new ArrayList<>();

        adapter = new ListaLibroDisponibleAdapter(mtd.selectlibrosdisponibles());
        listalibrosdisponibles.setAdapter(adapter);

        if(usuarios != null){
            nombreusuario.setText(usuarios.getNombre());

        }

        txtbuscardisponible.setOnQueryTextListener(this);
    }

    public void regresardisponible(View v){
        switch (v.getId()){
            case R.id.regresardisponible:
                Toast.makeText(UsuLibrosDisponibles.this, "Inicio", Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(UsuLibrosDisponibles.this, Vista_usuario.class);
                startActivity(i3);
                finish();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtradodisponible(s);
        return false;
    }
}