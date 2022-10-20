package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.biblioteca.DB.DBiblioteca;

public class MainActivity extends AppCompatActivity {
    Button btncrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*btncrear = findViewById(R.id.button2);

        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBiblioteca dbiblioteca = new DBiblioteca(MainActivity.this);
                SQLiteDatabase db = dbiblioteca.getWritableDatabase();
                if(db != null){
                    Toast.makeText(MainActivity.this, "Base de datos creeda", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

            }
        });*/


    }
}