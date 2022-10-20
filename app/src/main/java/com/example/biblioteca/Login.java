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

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, contraseña;
    Button btnEntrar, btnRegistrar;
    Metodos mtd;
    SharedPreference sp;
    Administrador administrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario=(EditText) findViewById(R.id.correo);
        contraseña=(EditText) findViewById(R.id.contrasena);
        btnEntrar =(Button) findViewById(R.id.btnEntrar);
        btnRegistrar=(Button) findViewById(R.id.btnRegistro);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

         mtd = new Metodos(this);
         sp = new SharedPreference(this);
         administrador = new Administrador();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEntrar:
                String user = usuario.getText().toString();
                String pass = contraseña.getText().toString();
                String u = "1";
                String a ="0";

                if(user.isEmpty() && pass.isEmpty()){
                    Toast.makeText(this,"ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(mtd.loginuser(user, pass, u) == 1){
                    sp.setCorreoUsuario(user);
                    Usuarios ux = mtd.getCorreo(user,pass);
                    Toast.makeText(this,"Datos correctos", Toast.LENGTH_SHORT).show();
                    Intent i3=new Intent(Login.this, Vista_usuario.class);
                    i3.putExtra("id", ux.getId());
                    startActivity(i3);
                    finish();
                }else if(mtd.loginadmin(user, pass, a)==1){
                    sp.setCorreoAdmin(user);
                    Usuarios ux = mtd.getCorreo(user, pass);
                    Toast.makeText(this,"Datos correctos", Toast.LENGTH_LONG).show();

                    Intent i4=new Intent(Login.this, Vista_admin.class);
                    i4.putExtra("id", ux.getId());
                    startActivity(i4);
                    finish();
                }else {
                    Toast.makeText(this,"Usuario y/o Contraseña INCORRECTOS", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnRegistro:
                Intent i=new Intent(Login.this, Registerusuario.class);
                startActivity(i);
                break;
        }
    }


}