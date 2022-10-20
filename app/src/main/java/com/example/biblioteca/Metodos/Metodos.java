package com.example.biblioteca.Metodos;


import static com.example.biblioteca.DB.DBiblioteca.TABLE_LIBROS;
import static com.example.biblioteca.DB.DBiblioteca.TABLE_LIBROSPRESTADOS;
import static com.example.biblioteca.DB.DBiblioteca.TABLE_USUARIOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.util.PatternsCompat;

import com.example.biblioteca.DB.DBiblioteca;
import com.example.biblioteca.SharedPreference;
import com.example.biblioteca.modelos.Administrador;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.LibrosPrestados;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metodos {
    Context c;
    ArrayList<Usuarios> listauser;
    ArrayList<Libros> listalibro;
    ArrayList<LibrosPrestados> listalibroprestado;
    SQLiteDatabase sql;
    SharedPreference sp;

    public Metodos(Context c) {
        this.c = c;
        sp = new SharedPreference(c);
    }

    //Insertar Usuario en la tabla usuarios

    public boolean insertUsuario(Usuarios u){
        if(buscar(u.getCorreo())==0) {


            boolean id = false;

            try {


                DBiblioteca dBiblioteca = new DBiblioteca(c);
                SQLiteDatabase db = dBiblioteca.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put("nombre", u.getNombre());
                values.put("correo", u.getCorreo());
                values.put("telefono", u.getTelefono());
                values.put("direccion", u.getDireccion());
                values.put("contrasena", u.getContrasena());
                values.put("rol", "1");

                id = (db.insert(TABLE_USUARIOS, null, values)>0);

            } catch (Exception ex) {
                ex.toString();
            }
            return id;
        }else{
            return false;
        }
    }



    //buscar usuario ya registrado
    public int buscar (String u){
        int x=0;
        listauser=selectUsuarios();
        for (Usuarios us:listauser) {
            if(us.getCorreo().equals(u)){
                x++;
            }

        }
        return x;
    }

    //buscar libro ya registrado
    public int buscarlibro (String l){
        int x=0;
        listalibro=selectlibros();
        for (Libros lb:listalibro) {
            if(lb.getNombrelibro().equals(l)){
                x++;
            }

        }
        return x;
    }

    //buscar libroprestados ya registrado
    public int buscarlibroprestado (String n){
        int x=0;
        listalibroprestado=selectlibrosprestados();
        for (LibrosPrestados lb:listalibroprestado) {
            if(sp.getCorreoUsuario().equals(n)){
                x++;
            }

        }
        return x;
    }


    //recorrer tabla usuarios
    public ArrayList<Usuarios> selectUsuarios(){

        Cursor user;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        ArrayList<Usuarios> listauser =new ArrayList<>();
        listauser.clear();

        user = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS, null);

        if(user != null && user.moveToFirst()){
            do{
                Usuarios u = new Usuarios();
                u.setId(user.getInt(0));
                u.setNombre(user.getString(1));
                u.setCorreo(user.getString(2));
                u.setTelefono(user.getString(3));
                u.setDireccion(user.getString(4));
                u.setContrasena(user.getString(5));


                listauser.add(u);
            }while (user.moveToNext());

        }
        return listauser;
    }

    //recorrer tabla libro
    public ArrayList<Libros> selectlibros(){
        Cursor libro;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        ArrayList<Libros> listalibro =new ArrayList<>();
        listalibro.clear();

        libro = db.rawQuery("SELECT * FROM " + TABLE_LIBROS, null);
        if(libro != null && libro.moveToFirst()){
            do{
                Libros l = new Libros();
                l.setId(libro.getInt(0));
                l.setNombrelibro(libro.getString(1));
                l.setAutorlibro(libro.getString(2));
                l.setCantidadlibro(libro.getString(3));
                l.setUrllibro(libro.getString(4));
                l.setImagenlibro(libro.getString(5));
                l.setDescripcionlibro(libro.getString(6));


                listalibro.add(l);
            }while (libro.moveToNext());

        }
        return listalibro;
    }

    //login usuario
    public int loginuser(String u, String p,String us) {
        int a = 0;
        Cursor user;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();

        user = db.rawQuery(" SELECT * FROM "+ TABLE_USUARIOS, null);
        if (user != null && user.moveToFirst()) {
            do {

                if (user.getString(2).equals(u) && user.getString(5).equals(p) && user.getString(6).equals(us)) {
                    a++;
                }
            } while (user.moveToNext());
        }
        return a;
    }

    //login administradores
    public int loginadmin(String u, String p, String ad) {
        int a = 0;
        Cursor admin;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();

        admin = db.rawQuery(" SELECT * FROM "+ TABLE_USUARIOS, null);
        if (admin != null && admin.moveToFirst()) {
            do {

                if (admin.getString(2).equals(u) && admin.getString(5).equals(p) && admin.getString(6).equals(ad)) {
                    a++;
                }
            } while (admin.moveToNext());
        }
        return a;
    }

    //validar contraseña en la tabla usuarios
    public Usuarios getCorreo(String ur, String p){
        listauser =selectUsuarios();
        for(Usuarios nombre:listauser){
            if(nombre.getCorreo().equals(ur)&&nombre.getContrasena().equals(p)){
                return nombre;
            }
        }
        return  null;
    }



    //validar contrasena en el registro
    public boolean validarcontrasena(String contrasena){
        if(contrasena.length() > 8 && contrasena.length() < 15){
            boolean mayuscula = false;
            boolean numero = false;
            char c;

            for(int  i=0; i<contrasena.length(); i++){
                c = contrasena.charAt(i);

                if(Character.isDigit(c))
                    numero = true;
                if(Character.isUpperCase(c))
                    mayuscula = true;

            }
            if(numero && mayuscula)
                return  true;
            else
                return false;
        }else {
            return false;
        }

    }

    //validar correo en el registro
    public boolean validarcorreo(String correo){
        Pattern pattern = Pattern.compile( "(\\W|^)[\\w.\\-]{0,25}@(hotmail|gmail|outlook)\\.com(\\W|$)");
        Matcher matcher = pattern.matcher(correo);
        /*PatternsCompat.EMAIL_ADDRESS.matcher("f").matches();*/
        if (matcher.find()){
            return true;
        }else{
            return false;
        }


    }



    //insertar libro

    public boolean insertLibro(Libros l){
        if(buscarlibro(l.getNombrelibro())==0) {


            boolean id = false;

            try {


                DBiblioteca dBiblioteca = new DBiblioteca(c);
                SQLiteDatabase db = dBiblioteca.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put("nombrelibro", l.getNombrelibro());
                values.put("autorlibro", l.getAutorlibro());
                values.put("cantidadlibro", l.getCantidadlibro());
                values.put("urllibro", l.getUrllibro());
                values.put("imagenlibro", l.getImagenlibro());
                values.put("descripcionlibro", l.getDescripcionlibro());

                id = (db.insert(TABLE_LIBROS, null, values)>0);

            } catch (Exception ex) {
                ex.toString();
            }
            return id;
        }else{
            return false;
        }
    }


    //ver libro en actulizar libros

    public Libros verLibro(int id){
        Cursor libro;
        Libros l = null;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();



        libro = db.rawQuery("SELECT * FROM " + TABLE_LIBROS + " WHERE id=" + id + " LIMIT 1", null);

        if(libro != null && libro.moveToFirst()){
                 l = new Libros();
                l.setId(libro.getInt(0));
                l.setNombrelibro(libro.getString(1));
                l.setAutorlibro(libro.getString(2));
                l.setCantidadlibro(libro.getString(3));
                l.setUrllibro(libro.getString(4));
                l.setImagenlibro(libro.getString(5));
                l.setDescripcionlibro(libro.getString(6));
        }
        libro.close();

        return l;
    }

    public boolean editarlibro(int id, Libros l){

            boolean correcto = false;

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();

            try {
                db.execSQL("UPDATE " + TABLE_LIBROS + " SET nombrelibro= '"+l.getNombrelibro()+"', autorlibro= '"+l.getAutorlibro()+"', cantidadlibro= '"+l.getCantidadlibro()+"', urllibro= '"+l.getUrllibro()+"', imagenlibro= '"+l.getImagenlibro()+"', descripcionlibro= '"+l.getDescripcionlibro()+"' WHERE id = '"+ id +"' " );

                correcto = true;

            } catch (Exception ex) {
                ex.toString();
                correcto = false;
            } finally {
                db.close();
            }
            return correcto;
    }

    //mostrar libros solo si la cantidad es superior a 0
    public ArrayList<Libros> selectlibrosdisponibles(){
        Cursor libro;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        ArrayList<Libros> listalibro =new ArrayList<>();
        listalibro.clear();

        libro = db.rawQuery("SELECT * FROM " + TABLE_LIBROS + " WHERE cantidadlibro !=" + "0" , null);
        if(libro != null && libro.moveToFirst()){
            do{
                Libros l = new Libros();
                l.setId(libro.getInt(0));
                l.setNombrelibro(libro.getString(1));
                l.setAutorlibro(libro.getString(2));
                l.setCantidadlibro(libro.getString(3));
                l.setUrllibro(libro.getString(4));
                l.setImagenlibro(libro.getString(5));
                l.setDescripcionlibro(libro.getString(6));


                listalibro.add(l);
            }while (libro.moveToNext());

        }
        return listalibro;
    }

    //mostrar libros prestados sharedPreferences
    public ArrayList<LibrosPrestados> mostrarlibrosprestados(String correo_user){
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();

        ArrayList<LibrosPrestados> listaprestados = new ArrayList<>();
        LibrosPrestados librosPrestados;
        Cursor cursorlibrosprestados;

        cursorlibrosprestados = db.rawQuery(" SELECT * FROM " + TABLE_LIBROSPRESTADOS + " WHERE correo_usuario = '"+ correo_user+"' ", null );
        if(cursorlibrosprestados.moveToFirst()){
            do{
                librosPrestados = new LibrosPrestados();
                librosPrestados.setId(cursorlibrosprestados.getInt(0));
                librosPrestados.setNombrelibroprestado(cursorlibrosprestados.getString(2));
                librosPrestados.setAutorlibroprestado(cursorlibrosprestados.getString(3));
                librosPrestados.setUrllibroprestado(cursorlibrosprestados.getString(4));
                librosPrestados.setImagenlibroprestado(cursorlibrosprestados.getString(5));
                librosPrestados.setDescripcionlibroprestado(cursorlibrosprestados.getString(6));

                listaprestados.add(librosPrestados);
            }while (cursorlibrosprestados.moveToNext());
        }
        cursorlibrosprestados.close();
        return listaprestados;
    }


    //insertar libros prestados
    public boolean insertLibroprestados(LibrosPrestados librosPrestados){



            boolean id = false;

            try {

                DBiblioteca dBiblioteca = new DBiblioteca(c);
                Libros libros = new Libros();
                SQLiteDatabase db = dBiblioteca.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("idlibro", librosPrestados.getId());
                values.put("nombrelibro", librosPrestados.getNombrelibroprestado());
                values.put("autorlibro", librosPrestados.getAutorlibroprestado());
                values.put("cantidadlibro", librosPrestados.getCantidadlibroprestado());
                values.put("urllibro", librosPrestados.getUrllibroprestado());
                values.put("imagenlibro", librosPrestados.getImagenlibroprestado());
                values.put("descripcionlibro", librosPrestados.getDescripcionlibroprestado());
                values.put("correo_usuario", sp.getCorreoUsuario());


                id = (db.insert(TABLE_LIBROSPRESTADOS, null, values)>0);




            } catch (Exception ex) {
                ex.toString();
            }
            return id;


    }

    //actualizar cantidad

    public boolean actualizarcantidad(Libros libros, LibrosPrestados librosPrestados){
        boolean correcto = false;


        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();
        int num = Integer.parseInt(libros.getCantidadlibro());
        int result = num - 1;
        String actualizada = Integer.toString(result);
        libros.setCantidadlibro(actualizada);


        try{
            db.execSQL("UPDATE " + TABLE_LIBROS + " SET cantidadlibro= '"+ libros.getCantidadlibro() + "' WHERE nombrelibro = '"+ librosPrestados.getNombrelibroprestado() +"' " );
            correcto = true;
        }catch (Exception ex){

        }finally {
            db.close();
        }
        return correcto;
    }

    //recorrer tabla libros prestados

    public ArrayList<LibrosPrestados> selectlibrosprestados(){

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        ArrayList<LibrosPrestados> listalibro =new ArrayList<>();
        LibrosPrestados librosPrestados;
        Cursor libroprestado;

        libroprestado = db.rawQuery("SELECT * FROM " + TABLE_LIBROSPRESTADOS, null);

            if(libroprestado.moveToFirst()){
                do{
                    librosPrestados = new LibrosPrestados();
                    librosPrestados.setId(libroprestado.getInt(0));
                    librosPrestados.setNombrelibroprestado(libroprestado.getString(2));
                    librosPrestados.setAutorlibroprestado(libroprestado.getString(3));
                    librosPrestados.setCantidadlibroprestado(libroprestado.getString(4));
                    librosPrestados.setUrllibroprestado(libroprestado.getString(5));
                    librosPrestados.setImagenlibroprestado(libroprestado.getString(6));
                    librosPrestados.setDescripcionlibroprestado(libroprestado.getString(7));


                    listalibro.add(librosPrestados);
                }while (libroprestado.moveToNext());

            }
            return listalibro;
    }

    //recorrer tabla libros prestados

    public ArrayList<LibrosPrestados> selectadminlibrosprestados(){

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        ArrayList<LibrosPrestados> listalibro =new ArrayList<>();
        LibrosPrestados librosPrestados;
        Cursor libroprestado;

        libroprestado = db.rawQuery("SELECT * FROM " + TABLE_LIBROSPRESTADOS + " GROUP BY idlibro " , null);

        if(libroprestado.moveToFirst()){

                do{
                    librosPrestados = new LibrosPrestados();
                    librosPrestados.setId(libroprestado.getInt(0));
                    librosPrestados.setNombrelibroprestado(libroprestado.getString(2));
                    librosPrestados.setAutorlibroprestado(libroprestado.getString(3));
                    librosPrestados.setCantidadlibroprestado(libroprestado.getString(4));
                    librosPrestados.setUrllibroprestado(libroprestado.getString(5));
                    librosPrestados.setImagenlibroprestado(libroprestado.getString(6));
                    librosPrestados.setDescripcionlibroprestado(libroprestado.getString(7));


                    listalibro.add(librosPrestados);
                }while (libroprestado.moveToNext());

        }
        return listalibro;
    }

    public ArrayList<String> selectadmincorreos(int id){

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        ArrayList<String> listalibroprestado =new ArrayList<>();
        Cursor libroprestado;

        libroprestado = db.rawQuery("SELECT * FROM " + TABLE_LIBROSPRESTADOS + " WHERE idlibro=" + id, null);

        if(libroprestado.moveToFirst()){
                listalibroprestado.add(libroprestado.getString(8));
        }
        return listalibroprestado;
    }


    //recorrer tabla usuarios

    public ArrayList<Usuarios> selectadminlibroshistorial(int id){

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        ArrayList<Usuarios> listausuarioshistorial =new ArrayList<>();
        Usuarios usuarios;
        Cursor cursorusuarios;
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();
        ArrayList<String> correos = new ArrayList<>();
        correos = selectadmincorreos(id);
        if(correos != null){
            for(int i = 0; i < correos.size();i++){

                cursorusuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE  correo = '" + correos.get(i)+"' ", null);

                if(cursorusuarios.moveToFirst()){
                    do{
                        usuarios = new Usuarios();
                        usuarios.setId(cursorusuarios.getInt(0));
                        usuarios.setNombre(cursorusuarios.getString(1));
                        usuarios.setCorreo(cursorusuarios.getString(2));
                        usuarios.setTelefono(cursorusuarios.getString(3));
                        usuarios.setDireccion(cursorusuarios.getString(4));
                        usuarios.setContrasena(cursorusuarios.getString(5));

                        listausuarioshistorial.add(usuarios);
                    }while (cursorusuarios.moveToNext());
                }
            }
        }
        return listausuarioshistorial;
    }
    //ver libro prestado
    public LibrosPrestados verLibroPrestado(int id){
        Cursor libroprestados;
        LibrosPrestados librosPrestados = null;
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();



        libroprestados = db.rawQuery("SELECT * FROM " + TABLE_LIBROSPRESTADOS + " WHERE id=" + id + " LIMIT 1", null);

        if(libroprestados != null && libroprestados.moveToFirst()){
            librosPrestados = new LibrosPrestados();
            librosPrestados.setId(libroprestados.getInt(0));
            librosPrestados.setNombrelibroprestado(libroprestados.getString(2));
            librosPrestados.setAutorlibroprestado(libroprestados.getString(3));
            librosPrestados.setCantidadlibroprestado(libroprestados.getString(4));
            librosPrestados.setUrllibroprestado(libroprestados.getString(5));
            librosPrestados.setImagenlibroprestado(libroprestados.getString(6));
            librosPrestados.setDescripcionlibroprestado(libroprestados.getString(7));
        }
        libroprestados.close();

        return librosPrestados;
    }

    //eliminar libros prestados
    public boolean eliminarLibroprestados(LibrosPrestados librosPrestados){

        boolean correcto = false;

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();


        try {
            db.execSQL( " DELETE FROM " + TABLE_LIBROSPRESTADOS + " WHERE nombrelibro = '"+ librosPrestados.getNombrelibroprestado() +"' "  );
            correcto = true;

        } catch (Exception ex) {
            ex.toString();
        }
        return correcto;

    }

    //sumar libro prestado

    public boolean actualizarcantidadsuma(Libros libros, LibrosPrestados librosPrestados){
        boolean correcto = false;


        DBiblioteca dBiblioteca = new DBiblioteca(c);
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();
        int num = Integer.parseInt(libros.getCantidadlibro());
        int result = num + 1;
        String actualizada = Integer.toString(result);
        libros.setCantidadlibro(actualizada);


        try{
            db.execSQL("UPDATE " + TABLE_LIBROS + " SET cantidadlibro= '"+ libros.getCantidadlibro() + "' WHERE nombrelibro = '"+ librosPrestados.getNombrelibroprestado() +"' " );
            correcto = true;
        }catch (Exception ex){

            ex.toString();
        }finally {
            db.close();
        }
        return correcto;
    }

    //ver nombre ususarios
    //ver libro prestado
    public Usuarios vernombreusuario(String correo_user){

        DBiblioteca dBiblioteca = new DBiblioteca(c);
        ArrayList<Usuarios> listausuarioshistorial =new ArrayList<>();
        Usuarios usuarios = null;
        Cursor cursorusuarios;
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();

                cursorusuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE  correo = '" + correo_user +"' ", null);

                if(cursorusuarios.moveToFirst()){
                    do{
                        usuarios = new Usuarios();
                        usuarios.setId(cursorusuarios.getInt(0));
                        usuarios.setNombre(cursorusuarios.getString(1));
                        usuarios.setCorreo(cursorusuarios.getString(2));
                        usuarios.setTelefono(cursorusuarios.getString(3));
                        usuarios.setDireccion(cursorusuarios.getString(4));
                        usuarios.setContrasena(cursorusuarios.getString(5));

                        listausuarioshistorial.add(usuarios);
                    }while (cursorusuarios.moveToNext());
                }
        return usuarios;
    }
    //ver nombre de admin en la vista asmin

    public Administrador vernombreadmin(String correo){
        DBiblioteca dBiblioteca = new DBiblioteca(c);
        ArrayList<Administrador> listanombreadmin =new ArrayList<>();
        Administrador admin = null;
        Cursor cursoradmin;
        SQLiteDatabase db = dBiblioteca.getWritableDatabase();

        cursoradmin = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE  correo = '" + correo +"' ", null);
        if (cursoradmin.moveToFirst()) {
            do {

                admin = new Administrador();
                admin.setId(cursoradmin.getInt(0));
                admin.setAdminnombre(cursoradmin.getString(1));
                listanombreadmin.add(admin);
            } while (cursoradmin.moveToNext());
        }
        return admin;
    }
}
