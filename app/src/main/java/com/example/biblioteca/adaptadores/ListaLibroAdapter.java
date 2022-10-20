package com.example.biblioteca.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.biblioteca.AdminActualizarLibro;
import com.example.biblioteca.R;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.LibrosPrestados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaLibroAdapter extends RecyclerView.Adapter<ListaLibroAdapter.LibrosViewHolder> {

    ArrayList<Libros> listalibros;
    ArrayList<Libros> listalibrosoriginal;

    public ListaLibroAdapter(ArrayList<Libros> listalibros){
        this.listalibros = listalibros;
        listalibrosoriginal = new ArrayList<>();
        listalibrosoriginal.addAll(listalibros);
    }



    @NonNull
    @Override
    public LibrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_libro, null, false);
        return new LibrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosViewHolder holder, int position) {;

        Glide.with(holder.imageBook)
                .load(listalibros.get(position).getImagenlibro())
                .circleCrop()
                .into(holder.imageBook);
        holder.viewnombre.setText(listalibros.get(position).getNombrelibro());
        holder.viewautor.setText(listalibros.get(position).getAutorlibro());

    }

    public void filtradoadmin(String txtbuscar){
        int longitud = txtbuscar.length();
        if( longitud ==0){
            listalibros.clear();
            listalibros.addAll(listalibrosoriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Libros> collecion = listalibros.stream().filter(i -> i.getNombrelibro().toLowerCase().contains(txtbuscar.toLowerCase())).collect(Collectors.toList());

                listalibros.clear();
                listalibros.addAll(collecion);
            }else{
                for(Libros c: listalibrosoriginal){
                    if (c.getNombrelibro().toLowerCase().contains(txtbuscar.toLowerCase())){
                        listalibros.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listalibros.size();
    }

    public class LibrosViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBook;
        TextView viewnombre;
        TextView viewautor;


         public LibrosViewHolder(@NonNull View itemView) {
            super(itemView);

            imageBook = itemView.findViewById(R.id.imageBook);
            viewnombre = itemView.findViewById(R.id.viewnombre);
            viewautor = itemView.findViewById(R.id.viewautor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = viewautor.getContext();
                    Intent intent = new Intent(context, AdminActualizarLibro.class);

                    intent.putExtra("ID", listalibros.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
