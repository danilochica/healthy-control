package com.healtycontrol.adapter;

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
import com.healtycontrol.entidades.Temperatura;

public class TemperaturaAdapter extends FirestoreRecyclerAdapter<Temperatura,TemperaturaAdapter.ViewHolder > {


    public TemperaturaAdapter(@NonNull FirestoreRecyclerOptions<Temperatura> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TemperaturaAdapter.ViewHolder holder, int position, @NonNull Temperatura temperatura) {


        holder.tvResultado.setText("Resultado: " + temperatura.getResultado());
        holder.tvValor.setText("Valor registrado: " + temperatura.getValorTemperatura().toString() + " °C");
        asignarColorResultado(temperatura.getResultado(), holder);
        holder.tvfecha.setText("Fecha y hora:" + temperatura.getFechaRegistro().toString());

    }

    private void asignarColorResultado(String resultado, ViewHolder holder) {

        if (resultado.equals("SIN FIEBRE")) {
            holder.tvResultado.setTextColor(Color.parseColor("#74B80E"));
        } else {
            if (resultado.equals("PICO DE FIEBRE")) {
                holder.tvResultado.setTextColor(Color.parseColor("#FDCB27"));
            } else {
                if(resultado.equals("FIEBRE")){
                    holder.tvResultado.setTextColor(Color.parseColor("#D0240A"));
                } else {
                    holder.tvResultado.setTextColor(Color.parseColor("#015EAB"));
                }

            }
        }

    }

    @NonNull
    @Override
    public TemperaturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_resultados, parent, false );
        return new TemperaturaAdapter.ViewHolder(view);
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
