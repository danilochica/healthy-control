package com.healthycontrol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.healtycontrol.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
    }

    public void goToGlucometriasActivity(View view){
        Intent intent = new Intent(this, GlucometriasActivity.class);
        startActivity(intent);
        finish();
    }

    public  void goToPresionActivity(View view) {
        Intent intent = new Intent(this, PresionActivity.class);
        startActivity(intent);
        finish();

    }

    public  void goToPesoActivity(View view) {
        Intent intent = new Intent(this, PesoActivity.class);
        startActivity(intent);
        finish();

    }

    public void goToTemperaturaActivity(View view){
        Intent intent = new Intent(this, TemperaturaActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToConsultaDeRegistros(View view){
        Intent intent = new Intent(this, ConsultaRegistrosActitvity.class);
        startActivity(intent);
    }


}
