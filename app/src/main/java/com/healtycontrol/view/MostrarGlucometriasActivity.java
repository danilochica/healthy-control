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
import com.healtycontrol.adapter.GlucometriaAdapter;
import com.healtycontrol.entidades.Glucometria;


public class MostrarGlucometriasActivity extends AppCompatActivity {

    RecyclerView recycleGlucometrias;
    GlucometriaAdapter adapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_glucometrias);
        this.setTitle(R.string.consulta_glucometrias);
        recycleGlucometrias = findViewById(R.id.recycleGlucometrias);
        recycleGlucometrias.setLayoutManager(new LinearLayoutManager(this));
        firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("Glucometrias").whereEqualTo("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());

        FirestoreRecyclerOptions<Glucometria> recyclerOptions = new FirestoreRecyclerOptions.Builder<Glucometria>().setQuery(query, Glucometria.class).build();
        adapter = new GlucometriaAdapter(recyclerOptions);
        adapter.notifyDataSetChanged();
        recycleGlucometrias.setAdapter(adapter);
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
