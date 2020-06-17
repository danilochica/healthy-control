package com.healtycontrol.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.healtycontrol.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.btnCerrarSesion)
    public Button btnCerrarSesion;

    @BindView(R.id.txtUsuario)
    public TextView txtUsuario;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        obtenerNombreUsuario();
    }

    private void obtenerNombreUsuario(){
        String id = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String nombre = "Hola! , " + dataSnapshot.child("nombre").getValue().toString();

                    txtUsuario.setText(nombre);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    public void cerrarSesion(View view) {
        firebaseAuth.signOut();
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();

    }


}
