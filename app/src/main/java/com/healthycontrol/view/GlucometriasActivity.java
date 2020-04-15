package com.healthycontrol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.healthycontrol.utilidades.GenerarFecha;
import com.healtycontrol.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlucometriasActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaRegistro)
    public TextView textViewFechaRegistro;

    @BindView(R.id.spHorarios)
    public Spinner spHorarios;

    String[] strHorarios;
    List<String> listaHorarios;
    ArrayAdapter<String> comboAdapter;
    String horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_glucometrias);
        ButterKnife.bind(this);
        iniciarComponentes();
    }


    public void iniciarComponentes(){
        obtenerFecha();
        cargarDatosSpinner();

    }

    public void goToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void cargarDatosSpinner() {
        listaHorarios = new ArrayList<>();
        strHorarios = new String[] {"Seleccione una opción","Antes de la comida", "Después de la comida"};
        Collections.addAll(listaHorarios, strHorarios);
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaHorarios);
        spHorarios.setAdapter(comboAdapter);
    }

    private void obtenerFecha() {
        textViewFechaRegistro.setText("Fecha y hora del registro: " + GenerarFecha.ejecutar());
    }








}
