package com.healtycontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healtycontrol.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.txtNombre)
    public TextView txtNombre;
    @BindView(R.id.txtCorreo)
    public TextView txtCorreo;
    @BindView(R.id.txtPassword)
    public TextView txtPassword;
    @BindView(R.id.btnIngresar)
    public Button btnIngresar;

    private String nombre ="";
    private String correo ="";
    private String password="";

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registrar_usuario);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private boolean esValido(){

        nombre = txtNombre.getText().toString();
        correo = txtCorreo.getText().toString();
        password = txtPassword.getText().toString();

        if(correo.isEmpty() && password.isEmpty() && nombre.isEmpty()){
            Toast.makeText(RegistrarUsuarioActivity.this, "Todos los campos deben de estar completos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length() <= 6){
            Toast.makeText(RegistrarUsuarioActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    public void registrarUsuario(View view){

        if (esValido()){
            firebaseAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Map<String, Object> map = new HashMap<>();
                        map.put("nombre", nombre);
                        map.put("correo", correo);
                        map.put("password", password);

                        String id = firebaseAuth.getCurrentUser().getUid();
                        databaseReference.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if (task2.isSuccessful()){
                                    startActivity(new Intent(RegistrarUsuarioActivity.this, HomeActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(RegistrarUsuarioActivity.this, "No se logró crear el usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(RegistrarUsuarioActivity.this, "No se logró registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
}
