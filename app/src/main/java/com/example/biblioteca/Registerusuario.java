package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biblioteca.Metodos.Metodos;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.Usuarios;

public class Registerusuario extends AppCompatActivity implements View.OnClickListener{
    EditText nombre;
    EditText correo;
    EditText telefono;
    EditText direccion;
    EditText contrasena;
    Button reg, can;
    Metodos mtd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre=(EditText) findViewById(R.id.nombrer);
        correo=(EditText) findViewById(R.id.correor);
        telefono= (EditText) findViewById(R.id.telefonor);
        direccion=(EditText) findViewById(R.id.direccionr);
        contrasena=(EditText) findViewById(R.id.contrasenar);

        reg =(Button) findViewById(R.id.btnRegistrar);
        can=(Button) findViewById(R.id.btnCancelar);



        reg.setOnClickListener(this);
        can.setOnClickListener(this);

        mtd = new Metodos(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistrar:
                Usuarios usuarios = new  Usuarios();
                Administrador admin = new Administrador();
                mtd = new Metodos(this);

                //Guardar informacion en usuario
                usuarios.setNombre(nombre.getText().toString());
                usuarios.setCorreo(correo.getText().toString());
                usuarios.setTelefono(telefono.getText().toString());
                usuarios.setDireccion(direccion.getText().toString());
                usuarios.setContrasena(contrasena.getText().toString());


                if(usuarios.isEmpty()){
                    Toast.makeText(this, "ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(telefono.length() != 10){
                    Toast.makeText(this, "Error Telefono", Toast.LENGTH_LONG).show();

                }else if(!mtd.validarcorreo(correo.getText().toString())){
                    Toast.makeText(this, "ERROR: Correo debe ser email Gmail, Hotmail, Outlook", Toast.LENGTH_LONG).show();

                } else if (!mtd.validarcontrasena(contrasena.getText().toString())){
                    Toast.makeText(this, "ERROR: Contraseña debe ser alfanumérica, incluir caracteres especiales, un rango de mínimo 8 y máximo 15", Toast.LENGTH_LONG).show();

                }else if (mtd.insertUsuario(usuarios)){
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(Registerusuario.this, Login.class);
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this, "Usuario ya Registrado", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancelar:
                Intent i=new Intent(Registerusuario.this, Login.class);
                startActivity(i);
                finish();
                break;

        }
    }
}