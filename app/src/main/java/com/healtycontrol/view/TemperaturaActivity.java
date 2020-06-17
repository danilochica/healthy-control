package com.healtycontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.healtycontrol.R;
import com.healtycontrol.utilidades.GenerarFecha;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemperaturaActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaTemperatura)
    public TextView textViewFechaTemperatura;

    @BindView(R.id.txtTemperatura)
    public EditText txtTemperatura;

    private String resultado;

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


    public void guardarRegistro(View view) {

        validarCompletitudDeCampos();

        resultado = generarResultadoTemperatura(Double.valueOf(txtTemperatura.getText().toString()));

        Map<String, Object> registro = new HashMap<>();
        registro.put("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        registro.put("valorTemperatura", Double.valueOf(txtTemperatura.getText().toString()));
        registro.put("resultado", resultado);
        registro.put("fechaRegistro", new Timestamp(new Date()));

        FirebaseFirestore.getInstance().collection("Temperatura")
                .add(registro)
                .addOnSuccessListener( peso -> {
                    Toast.makeText(TemperaturaActivity.this, "Registro guardado : " + resultado, Toast.LENGTH_LONG).show();
                    goToHome();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(TemperaturaActivity.this, "Ups! Su registro no fue guardado", Toast.LENGTH_SHORT).show();
                });
    }

    private String generarResultadoTemperatura(Double temperatura){

        if(temperatura < 37 && temperatura > 35.5 ) {
            return "SIN FIEBRE";
        }

        if(temperatura <= 37 && temperatura > 38 ) {
            return "PICO DE FIEBRE";
        }

        if(temperatura >= 38 ) {
            return "FIEBRE";
        }

        return "HIPOTERMIA";
    }

    private void validarCompletitudDeCampos() {
        if(txtTemperatura.getText().toString().isEmpty()){
            Toast.makeText(TemperaturaActivity.this, "Todos los campos deben de estar completos", Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerFecha() {
        textViewFechaTemperatura.setText("Fecha y hora del registro: " + GenerarFecha.ejecutar());
    }

    public void goToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
