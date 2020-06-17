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
import com.healtycontrol.entidades.Glucometria;

public class GlucometriaAdapter extends FirestoreRecyclerAdapter<Glucometria,GlucometriaAdapter.ViewHolder > {


    public GlucometriaAdapter(@NonNull FirestoreRecyclerOptions<Glucometria> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, Glucometria glucometria) {
        holder.tvValor.setText(String.valueOf(glucometria.getValorGlucometria()));
        holder.tvHorario.setText(glucometria.getHorario());
        holder.tvResultado.setText(glucometria.getResultado());
        holder.tvfecha.setText(glucometria.getFechaRegistro().toString());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_glucometrias, parent, false );
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvValor;
        TextView tvHorario;
        TextView tvResultado;
        TextView tvfecha;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvValor = itemView.findViewById(R.id.tvValor);
            tvHorario = itemView.findViewById(R.id.tvHorario);
            tvResultado = itemView.findViewById(R.id.tvResultado);
            tvfecha = itemView.findViewById(R.id.tvfecha);

        }
    }

}
