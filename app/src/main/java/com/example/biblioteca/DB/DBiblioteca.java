package com.example.biblioteca.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBiblioteca extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Biblioteca.db";
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_LIBROS = "libros";
    public static final String TABLE_LIBROSPRESTADOS = "librosprestados";


    public DBiblioteca(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT  NOT NULL, " +
                "correo TEXT NOT NULL, " +
                "telefono TEXT NOT NULL," +
                "direccion TEXT," +
                "contrasena TEXT NOT NULL," +
                "rol TEXT NOT NULL )");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_LIBROS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombrelibro TEXT  NOT NULL, " +
                "autorlibro TEXT NOT NULL, " +
                "cantidadlibro TEXT NOT NULL," +
                "urllibro TEXT," +
                "imagenlibro TEXT NOT NULL," +
                "descripcionlibro TEXT NOT NULL )");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_LIBROSPRESTADOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idlibro INTEGER," +
                "nombrelibro TEXT  NOT NULL, " +
                "autorlibro TEXT NOT NULL, " +
                "cantidadlibro TEXT NOT NULL," +
                "urllibro TEXT," +
                "imagenlibro TEXT NOT NULL," +
                "descripcionlibro TEXT NOT NULL," +
                "correo_usuario TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_LIBROS);
        sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_LIBROSPRESTADOS);
        onCreate(sqLiteDatabase);
    }
}
