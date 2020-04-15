package com.healthycontrol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.healthycontrol.utilidades.GenerarFecha;
import com.healtycontrol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemperaturaActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaTemperatura)
    public TextView textViewFechaTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_temperatura);
        ButterKnife.bind(this);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        obtenerFecha();
    }

    private void obtenerFecha() {
        textViewFechaTemperatura.setText("Fecha y hora del registro: " + GenerarFecha.ejecutar());
    }

    public void goToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}