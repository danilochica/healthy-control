package com.healtycontrol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.healtycontrol.R;
import com.healtycontrol.entidades.Peso;

public class PesoAdapter  extends FirestoreRecyclerAdapter<Peso, PesoAdapter.ViewHolder> {

    public PesoAdapter(@NonNull FirestoreRecyclerOptions<Peso> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PesoAdapter.ViewHolder holder, int position, @NonNull Peso peso) {
        holder.tvValor.setText("Valor: No aplica" );
        holder.tvResultado.setText("Resultado: "  + peso.getPeso() + " Kg");
        holder.tvfecha.setText("Fecha y hora:" + peso.getFechaRegistro().toString());
    }

    @NonNull
    @Override
    public PesoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_resultados, parent, false );
        return new PesoAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvValor;
        TextView tvResultado;
        TextView tvfecha;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvValor = itemView.findViewById(R.id.tvValor);
            tvResultado = itemView.findViewById(R.id.tvResultado);
            tvfecha = itemView.findViewById(R.id.tvfecha);
        }
    }
}
