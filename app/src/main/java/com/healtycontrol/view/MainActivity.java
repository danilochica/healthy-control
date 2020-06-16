package com.healtycontrol.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtCorreo)
    public TextView txtCorreo;
    @BindView(R.id.txtPassword)
    public TextView txtPassword;
    @BindView(R.id.btnIngresar)
    public Button btnIngresar;
    @BindView(R.id.btnUsuarioNuevo)
    public Button btnUsuarioNuevo;

    private String correo ="";
    private String password="";

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }


    private boolean esValido(){

        correo = txtCorreo.getText().toString();
        password = txtPassword.getText().toString();

        if(correo.isEmpty() && password.isEmpty()){
            Toast.makeText(MainActivity.this, "Todos los campos deben de estar completos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length() <= 6){
            Toast.makeText(MainActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    public void loginUser(View view){
        esValido();
        firebaseAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }else{
                Toast.makeText(MainActivity.this, "No se logró iniciar sesión, por favor verifique los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();

        }

    }

    public void goToRegistrarUsuarioActivity(View view){
        Intent intent = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(intent);

    }

}
