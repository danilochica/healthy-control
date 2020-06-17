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

public class PresionActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaPresion)
    public TextView textViewFechaPresion;

    @BindView(R.id.txtPresionAlta)
    public EditText txtPresionAlta;

    @BindView(R.id.txtValorPresionBaja)
    public EditText txtValorPresionBaja;

    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_presion);
        ButterKnife.bind(this);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        obtenerFecha();
    }

    private void obtenerFecha() {
        textViewFechaPresion.setText("Fecha y hora del registro: " + GenerarFecha.ejecutar());
    }

    public String generarResultadoPresionArterial (Double presionAlta, Double presionBaja){

        if(presionAlta < 120 && presionBaja < 80){
            return "NORMAL";
        } else{
            if((presionAlta < 129 && presionAlta> 120) && (presionBaja < 80)){
                return "ELEVADA";
            }else{
                return "ALTA";
            }
        }

    }

    public void guardarRegistro(View view) {

        validarCompletitudDeCampos();

        resultado = generarResultadoPresionArterial(Double.valueOf(txtPresionAlta.getText().toString()), Double.valueOf(txtValorPresionBaja.getText().toString()) );

        Map<String, Object> registro = new HashMap<>();
        registro.put("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        registro.put("valorPresion", txtPresionAlta.getText().toString() +  " - " + txtValorPresionBaja.getText().toString());
        registro.put("resultado", resultado);
        registro.put("fechaRegistro", new Timestamp(new Date()));

        FirebaseFirestore.getInstance().collection("PresionArterial")
                .add(registro)
                .addOnSuccessListener( peso -> {
                    Toast.makeText(PresionActivity.this, "Registro guardado, su presion arterial es: " + resultado, Toast.LENGTH_LONG).show();
                    goToHome();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(PresionActivity.this, "Ups! Su registro no fue guardado", Toast.LENGTH_SHORT).show();
                });
    }

    private void validarCompletitudDeCampos() {

        if(txtPresionAlta.getText().toString().isEmpty() && txtValorPresionBaja.getText().toString().isEmpty()){
            Toast.makeText(PresionActivity.this, "Todos los campos deben de estar completos", Toast.LENGTH_SHORT).show();
        }

    }


    public void goToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


}
