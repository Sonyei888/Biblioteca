package com.example.biblioteca.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteca.AdminLibroHistorial;
import com.example.biblioteca.R;
import com.example.biblioteca.UsuMiLibro;
import com.example.biblioteca.modelos.LibrosPrestados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaAdminPrestamoAdapter extends RecyclerView.Adapter<ListaAdminPrestamoAdapter.LibrosViewHolder> {

    ArrayList<LibrosPrestados> listaadminlibrosprestados;
    ArrayList<LibrosPrestados> listalibrosoriginal;

    public ListaAdminPrestamoAdapter(ArrayList<LibrosPrestados> listaadminlibrosprestados) {
        this.listaadminlibrosprestados = listaadminlibrosprestados;
        listalibrosoriginal = new ArrayList<>();
        listalibrosoriginal.addAll(listaadminlibrosprestados);
    }

    @NonNull
    @Override
    public LibrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_libro, null, false);
        return new ListaAdminPrestamoAdapter.LibrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosViewHolder holder, int position) {
        holder.viewnombre.setText(listaadminlibrosprestados.get(position).getNombrelibroprestado());
        holder.viewautor.setText(listaadminlibrosprestados.get(position).getAutorlibroprestado());
    }
    public void filtrado(String txtbuscar){
        int longitud = txtbuscar.length();
        if( longitud ==0){
            listaadminlibrosprestados.clear();
            listaadminlibrosprestados.addAll(listalibrosoriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<LibrosPrestados> collecion = listaadminlibrosprestados.stream().filter(i -> i.getNombrelibroprestado().toLowerCase().contains(txtbuscar.toLowerCase())).collect(Collectors.toList());

                listaadminlibrosprestados.clear();
                listaadminlibrosprestados.addAll(collecion);
            }else{
                for(LibrosPrestados c: listalibrosoriginal){
                    if (c.getNombrelibroprestado().toLowerCase().contains(txtbuscar.toLowerCase())){
                        listaadminlibrosprestados.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaadminlibrosprestados.size();
    }

    public class LibrosViewHolder extends RecyclerView.ViewHolder {

        TextView viewnombre;
        TextView viewautor;
        public LibrosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewnombre = itemView.findViewById(R.id.viewnombre);
            viewautor = itemView.findViewById(R.id.viewautor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = viewautor.getContext();
                    Intent intent = new Intent(context, AdminLibroHistorial.class);

                    intent.putExtra("ID", listaadminlibrosprestados.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
