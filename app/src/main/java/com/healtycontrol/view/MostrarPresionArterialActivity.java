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
import com.healtycontrol.adapter.PresionArterialAdapter;
import com.healtycontrol.entidades.PresionArterial;


public class MostrarPresionArterialActivity extends AppCompatActivity {

    RecyclerView recyclePresionArterial;
    PresionArterialAdapter adapter;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_presion_arterial);
        this.setTitle(R.string.consulta_presion);
        recyclePresionArterial = findViewById(R.id.recyclePresionArterial);
        recyclePresionArterial.setLayoutManager(new LinearLayoutManager(this));
        firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("PresionArterial").whereEqualTo("usuario", FirebaseAuth.getInstance().getCurrentUser().getEmail());

        FirestoreRecyclerOptions<PresionArterial> recyclerOptions = new FirestoreRecyclerOptions.Builder<PresionArterial>().setQuery(query, PresionArterial.class).build();
        adapter = new PresionArterialAdapter(recyclerOptions);
        adapter.notifyDataSetChanged();
        recyclePresionArterial.setAdapter(adapter);

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
