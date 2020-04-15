package com.healthycontrol.view;

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

    @BindView(R.id.spMenu)
    public Spinner spMenu;

    @BindView(R.id.txtFechaInicial)
    public TextView txtFechaInicial;

    @BindView(R.id.txtFechaFinal)
    public TextView txtFechaFinal;

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
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        cargarDatosSpinner();
        cargarDatePickerFechaInicial();
        cargarDatePickerFechaFinal();

    }

    private void cargarDatePickerFechaInicial() {

        txtFechaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCalendar(onDateSetListenerInicial);
            }
        });

        onDateSetListenerInicial = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "-"  +  month  + "-" + year;
                txtFechaInicial.setText(date);
            }
        };
    }

    private void cargarDatePickerFechaFinal() {
        txtFechaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCalendar(onDateSetListenerFinal);
            }
        });

        onDateSetListenerFinal = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "-"  +  month  + "-" + year;
                txtFechaFinal.setText(date);
            }
        };

    }

    private void cargarDatosSpinner() {
        listaMenu = new ArrayList<>();
        strMenu = new String[] {"Seleccione una opción","Glucometrías", "Presión Arterial", "Peso", "Temperatura"};
        Collections.addAll(listaMenu, strMenu);
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaMenu);
        spMenu.setAdapter(comboAdapter);
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

    private void goToHome (View view){
    }


}
