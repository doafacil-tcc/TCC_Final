package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedDoacaoUnicaAdapter;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OngStatusDoacaoFinalizadas extends AppCompatActivity {

    private RecyclerView feedDoacoesFinalizadas;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_doacao_finalizadas);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesFinalizadas = findViewById(R.id.recycler_Doacoes_finalizadas);

        feedDoacoesFinalizadas.addItemDecoration(new DividerItemDecoration(feedDoacoesFinalizadas.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Finalizadas").whereEqualTo("origem", "ONG")
                .whereEqualTo("status", "Finalizadas")
                .whereEqualTo("id_user", FirebaseAuth.getInstance().getUid());

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedDoacaoUnicaAdapter(options,null);

        feedDoacoesFinalizadas.setHasFixedSize(true);
        feedDoacoesFinalizadas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedDoacoesFinalizadas.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

}