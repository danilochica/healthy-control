package com.healtycontrol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.healtycontrol.utilidades.GenerarFecha;
import com.healtycontrol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PesoActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaPeso)
    public TextView textViewFechaPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_peso);
        ButterKnife.bind(this);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        obtenerFecha();
    }

    private void obtenerFecha() {
        textViewFechaPeso.setText("Fecha y hora del registro: " + GenerarFecha.ejecutar());
    }


    public void goToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
