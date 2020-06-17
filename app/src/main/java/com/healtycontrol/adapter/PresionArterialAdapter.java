package com.healtycontrol.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.healtycontrol.R;
import com.healtycontrol.entidades.PresionArterial;

public class PresionArterialAdapter extends FirestoreRecyclerAdapter<PresionArterial,PresionArterialAdapter.ViewHolder > {


    public PresionArterialAdapter(@NonNull FirestoreRecyclerOptions<PresionArterial> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PresionArterial presionArterial) {

        holder.tvValor.setText("Valor registrado: " + presionArterial.getValorPresion());
        holder.tvResultado.setText("Resultado: " + presionArterial.getResultado());
        asignarColorResultado(presionArterial.getResultado(), holder);
        holder.tvfecha.setText("Fecha y hora:" + presionArterial.getFechaRegistro().toString());

    }

    private void asignarColorResultado(String resultado, ViewHolder holder) {

        if (resultado.equals("NORMAL")) {
            holder.tvResultado.setTextColor(Color.parseColor("#74B80E"));

        } else {
            if (resultado.equals("ELEVADA")) {
                holder.tvResultado.setTextColor(Color.parseColor("#FDCB27"));
            } else {
                holder.tvResultado.setTextColor(Color.parseColor("#D0240A"));
            }
        }



        if (resultado.equals("NORMAL")) {
            holder.tvResultado.setTextColor(Color.parseColor("#74B80E"));

        } else {
            if (resultado.equals("ELEVADA")) {
                holder.tvResultado.setTextColor(Color.parseColor("#FDCB27"));
            } else {
                holder.tvResultado.setTextColor(Color.parseColor("#D0240A"));
            }
        }

    }

    @NonNull
    @Override
    public PresionArterialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_resultados, parent, false );
        return new PresionArterialAdapter.ViewHolder(view);
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
