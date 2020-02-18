package com.example.firestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MiAdaptador extends FirestoreRecyclerAdapter<Producto,MiAdaptador.ViewHolder> {

    public MiAdaptador(@NonNull FirestoreRecyclerOptions<Producto> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Producto producto) {
        holder.precio.setText(producto.getPrecio());
        holder.nombre.setText(producto.getNombre());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView precio;
        TextView nombre;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            precio=itemView.findViewById(R.id.id_text);
            nombre=itemView.findViewById(R.id.nombre);
        }

    }
}
