package com.example.tcc.doador;

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

public class DoadorStatusDoacaoEsperando extends AppCompatActivity {

    private RecyclerView feedDoacoesEmEspera;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_status_doacao_esperando);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesEmEspera = findViewById(R.id.recycler_Doacoes_espera);

        feedDoacoesEmEspera.addItemDecoration(new DividerItemDecoration(feedDoacoesEmEspera.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Aguardando").whereEqualTo("origem", "Doador")
                .whereEqualTo("status", "Aguardando")
                .whereEqualTo("id_user", FirebaseAuth.getInstance().getUid());

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedDoacaoUnicaAdapter(options,null);

        feedDoacoesEmEspera.setHasFixedSize(true);
        feedDoacoesEmEspera.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedDoacoesEmEspera.setAdapter(adapter);

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