package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.adaptadores.ListaLibroPrestamoAdapter;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.LibrosPrestados;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;

public class Vista_usuario extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener{

    TextView nombreusuario;
    SearchView txtbuscarusuario;
    Usuarios usuarios;
    RecyclerView listalibrosprestados;
    ArrayList<LibrosPrestados> listaArrayLibrosprestados;
    Button btnDisponible;
    SharedPreference sp;
    ListaLibroPrestamoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_usuario);

        nombreusuario = findViewById(R.id.nombreusuario);
        txtbuscarusuario = findViewById(R.id.txtbuscarusuario);
        btnDisponible = (Button) findViewById(R.id.btnDisponible);

        listalibrosprestados = findViewById(R.id.listalibrosprestados);
        listalibrosprestados.setLayoutManager(new GridLayoutManager(this, 2));
        Metodos mtd = new Metodos(this);
        usuarios = new Usuarios();

        sp = new SharedPreference(this);


        listaArrayLibrosprestados = new ArrayList<>();

        String prueba = sp.getCorreoUsuario();
        usuarios = mtd.vernombreusuario(prueba);
        adapter = new ListaLibroPrestamoAdapter(mtd.mostrarlibrosprestados(prueba));
        listalibrosprestados.setAdapter(adapter);


        btnDisponible.setOnClickListener(this);


        if(usuarios != null){
            nombreusuario.setText(usuarios.getNombre());

        }

        txtbuscarusuario.setOnQueryTextListener(this);
    }

    public void mostrarpopupuser(View v){
        ImageView iv1 = findViewById(R.id.menupopup);
        PopupMenu pu = new PopupMenu(this,iv1);
        pu.getMenuInflater().inflate(R.menu.menupopupuser, pu.getMenu());
        pu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.salir:
                        Toast.makeText(Vista_usuario.this, "Cerrando Sesion", Toast.LENGTH_SHORT).show();
                        Intent i4 = new Intent(Vista_usuario.this, Login.class);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDisponible:
                Intent i=new Intent(Vista_usuario.this, UsuLibrosDisponibles.class);
                startActivity(i);
                finish();
                break;

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