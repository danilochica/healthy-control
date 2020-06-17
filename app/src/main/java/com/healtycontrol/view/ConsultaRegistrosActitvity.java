package com.healtycontrol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.healtycontrol.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultaRegistrosActitvity extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener onDateSetListenerInicial;
    DatePickerDialog.OnDateSetListener onDateSetListenerFinal;

    String[] strMenu;
    List<String> listaMenu;
    ArrayAdapter<String> comboAdapter;
    String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_consulta_registros);
        ButterKnife.bind(this);
    }

    private void createCalendar(DatePickerDialog.OnDateSetListener listener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                ConsultaRegistrosActitvity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                listener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public  void goToMostrarGlucometrias(View view) {
        Intent intent = new Intent(this, MostrarGlucometriasActivity.class);
        startActivity(intent);
        finish();

    }



    private void goToHome (View view){
    }


}
