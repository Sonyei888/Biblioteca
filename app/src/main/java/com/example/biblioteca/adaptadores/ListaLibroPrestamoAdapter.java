package com.example.biblioteca.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteca.R;
import com.example.biblioteca.UsuMiLibro;
import com.example.biblioteca.UsuPrestarLibro;
import com.example.biblioteca.Vista_usuario;
import com.example.biblioteca.modelos.Libros;
import com.example.biblioteca.modelos.LibrosPrestados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaLibroPrestamoAdapter extends RecyclerView.Adapter<ListaLibroPrestamoAdapter.LibrosViewHolder> {

    ArrayList<LibrosPrestados> listalibrosprestado;
    ArrayList<LibrosPrestados> listalibrosoriginal;


    public ListaLibroPrestamoAdapter(ArrayList<LibrosPrestados> listalibrosprestado) {
        this.listalibrosprestado = listalibrosprestado;
        listalibrosoriginal = new ArrayList<>();
        listalibrosoriginal.addAll(listalibrosprestado);
    }

    @NonNull
    @Override
    public ListaLibroPrestamoAdapter.LibrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_libro, null, false);
        return new LibrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaLibroPrestamoAdapter.LibrosViewHolder holder, int position) {
        holder.viewnombre.setText(listalibrosprestado.get(position).getNombrelibroprestado());
        holder.viewautor.setText(listalibrosprestado.get(position).getAutorlibroprestado());

    }

    public void filtrado(String txtbuscar){
        int longitud = txtbuscar.length();
        if( longitud ==0){
            listalibrosprestado.clear();
            listalibrosprestado.addAll(listalibrosoriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<LibrosPrestados> collecion = listalibrosprestado.stream().filter(i -> i.getNombrelibroprestado().toLowerCase().contains(txtbuscar.toLowerCase())).collect(Collectors.toList());

                listalibrosprestado.clear();
                listalibrosprestado.addAll(collecion);
            }else{
                for(LibrosPrestados c: listalibrosoriginal){
                    if (c.getNombrelibroprestado().toLowerCase().contains(txtbuscar.toLowerCase())){
                     listalibrosprestado.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listalibrosprestado.size();
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
                    Intent intent = new Intent(context, UsuMiLibro.class);

                    intent.putExtra("ID", listalibrosprestado.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}

