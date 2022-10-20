package com.example.biblioteca.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteca.R;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.Usuarios;

import java.util.ArrayList;

public class ListaAdminHistorialAdapter extends RecyclerView.Adapter<ListaAdminHistorialAdapter.LibrosViewHolder> {
    ArrayList<Usuarios> listausuarios;

    public ListaAdminHistorialAdapter(ArrayList<Usuarios> listausuarios) {
        this.listausuarios = listausuarios;
    }

    @NonNull
    @Override
    public LibrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_libro_historial, null, false);
        return new ListaAdminHistorialAdapter.LibrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosViewHolder holder, int position) {
        holder.viewnombreuser.setText(listausuarios.get(position).getNombre());
        holder.viewtelefonouser.setText(listausuarios.get(position).getTelefono());
        holder.viewcorreouser.setText(listausuarios.get(position).getCorreo());
    }

    @Override
    public int getItemCount() {
        return listausuarios.size();
    }

    public class LibrosViewHolder extends RecyclerView.ViewHolder {

        TextView viewnombreuser;
        TextView viewtelefonouser;
        TextView viewcorreouser;

        public LibrosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewnombreuser  = itemView.findViewById(R.id.viewnombreuser);
            viewtelefonouser  = itemView.findViewById(R.id.viewtelefonouser);
            viewcorreouser  = itemView.findViewById(R.id.viewcorreouser);



        }
    }
}
