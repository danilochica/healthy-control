package com.healtycontrol.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.healtycontrol.R;
import com.healtycontrol.adapter.TemperaturaAdapter;
import com.healtycontrol.entidades.Temperatura;

public class MostrarTemperaturaActivity extends AppCompatActivity {

    RecyclerView recycleTemperatura;
    TemperaturaAdapter adapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_temperatura);
        this.setTitle(R.string.consulta_temperatura);
        recycleTemperatura = findViewById(R.id.recycleTemperatura);
        recycleTemperatura.setLayoutManager(new LinearLayoutManager(this));
        firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("Temperatura").whereEqualTo("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());

        FirestoreRecyclerOptions<Temperatura> recyclerOptions = new FirestoreRecyclerOptions.Builder<Temperatura>().setQuery(query, Temperatura.class).build();
        adapter = new TemperaturaAdapter(recyclerOptions);
        adapter.notifyDataSetChanged();
        recycleTemperatura.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
