package com.healtycontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

public class PesoActivity extends AppCompatActivity {


    @BindView(R.id.textViewFechaPeso)
    public TextView textViewFechaPeso;

    @BindView(R.id.txtPeso)
    public EditText txtPeso;

    @BindView(R.id.btnGuardar)
    public Button btnGuardar;

    private String fechaRegistro;


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
        fechaRegistro = GenerarFecha.ejecutar();
        textViewFechaPeso.setText("Fecha y hora del registro: " + fechaRegistro);
    }

    public void guardarRegistro(View view) {

        Map<String, Object> registro = new HashMap<>();
        registro.put("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        registro.put("peso", new Double(txtPeso.getText().toString()));
        registro.put("fechaRegistro", new Timestamp(new Date()));

        FirebaseFirestore.getInstance().collection("Peso")
                .add(registro)
                .addOnSuccessListener((doc) -> {
                    Toast.makeText(PesoActivity.this, "Registro guardado con éxito", Toast.LENGTH_SHORT).show();
                    goToHome();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(PesoActivity.this, "Ups! Su registro no fue guardado", Toast.LENGTH_SHORT).show();
                });
    }


    public void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
