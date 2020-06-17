package com.healtycontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.healtycontrol.R;
import com.healtycontrol.utilidades.GenerarFecha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlucometriasActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaRegistro)
    public TextView textViewFechaRegistro;

    @BindView(R.id.txtValorGlucosa)
    public TextView txtValorGlucosa;

    @BindView(R.id.spHorarios)
    public Spinner spHorarios;

    @BindView(R.id.btnGuardar)
    public Button btnGuardar;

    String[] strHorarios;
    public List<String> listaHorarios;
    public ArrayAdapter<String> comboAdapter;

    private String resultadoGlicemia;

    public static final int VALOR_GLUCOMETRIA_ALTA = 120;
    public static final int VALOR_GLUCOMETRIA_BAJA = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_glucometrias);
        ButterKnife.bind(this);
        iniciarComponentes();
    }


    public void iniciarComponentes() {
        obtenerFecha();
        cargarDatosSpinner();
    }

    public void guardarRegistro(View view) {

        validarCompletitudDeCampos();

        resultadoGlicemia = generarResultadoDeGlicemia(Double.valueOf(txtValorGlucosa.getText().toString()));

        Map<String, Object> registro = new HashMap<>();
        registro.put("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        registro.put("valorGlucometria", Double.valueOf(txtValorGlucosa.getText().toString()));
        registro.put("resultado", resultadoGlicemia);
        registro.put("horario", spHorarios.getSelectedItem());
        registro.put("fechaRegistro", new Timestamp(new Date()));

        FirebaseFirestore.getInstance().collection("Glucometrias")
                .add(registro)
                .addOnSuccessListener( peso -> {
                    Toast.makeText(GlucometriasActivity.this, "Registro guardado, sus niveles de glucosa es " + resultadoGlicemia, Toast.LENGTH_LONG).show();
                    goToHome();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(GlucometriasActivity.this, "Ups! Su registro no fue guardado", Toast.LENGTH_SHORT).show();
                });
    }

    private void validarCompletitudDeCampos() {

        if(txtValorGlucosa.getText().toString().isEmpty()){
            Toast.makeText(GlucometriasActivity.this, "Todos los campos deben de estar completos", Toast.LENGTH_SHORT).show();
        }

    }


    private String generarResultadoDeGlicemia(Double valorGlucometro) {

        if (valorGlucometro > VALOR_GLUCOMETRIA_ALTA) {
            return "ALTO";
        } else {
            if (valorGlucometro < VALOR_GLUCOMETRIA_BAJA) {
                return "BAJO";
            } else {
                return "NORMAL";
            }
        }
    }

    public void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void cargarDatosSpinner() {
        listaHorarios = new ArrayList<>();
        strHorarios = new String[]{"Seleccione una opción", "Antes de la comida", "Después de la comida"};
        Collections.addAll(listaHorarios, strHorarios);
        comboAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaHorarios);
        spHorarios.setAdapter(comboAdapter);
    }

    private void obtenerFecha() {
        textViewFechaRegistro.setText("Fecha y hora del registro: " + GenerarFecha.ejecutar());
    }


}
