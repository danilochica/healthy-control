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

public class PresionActivity extends AppCompatActivity {

    @BindView(R.id.textViewFechaPresion)
    public TextView textViewFechaPresion;

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

    public void goToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


}
