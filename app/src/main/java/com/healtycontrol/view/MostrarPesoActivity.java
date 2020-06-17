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
import com.healtycontrol.adapter.PesoAdapter;
import com.healtycontrol.entidades.Peso;


public class MostrarPesoActivity extends AppCompatActivity {

    RecyclerView recyclePeso;
    PesoAdapter adapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_peso);
        this.setTitle(R.string.consulta_peso);
        recyclePeso = findViewById(R.id.recyclePeso);
        recyclePeso.setLayoutManager(new LinearLayoutManager(this));
        firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("Peso").whereEqualTo("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());

        FirestoreRecyclerOptions<Peso> recyclerOptions = new FirestoreRecyclerOptions.Builder<Peso>().setQuery(query, Peso.class).build();
        adapter = new PesoAdapter(recyclerOptions);
        adapter.notifyDataSetChanged();
        recyclePeso.setAdapter(adapter);
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
