package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.tcc.Entities.FeedCampanhaAdapter;
import com.example.tcc.Entities.SolicitarCampanha;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OngStatusCampanhaAberta extends AppCompatActivity implements FeedCampanhaAdapter.OnListItemClick {

    private RecyclerView feedCampanhaAberta;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_campanha_aberta);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedCampanhaAberta = findViewById(R.id.recycler_Campanha_Aberta);

        feedCampanhaAberta.addItemDecoration(new DividerItemDecoration(feedCampanhaAberta.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Campanha").whereEqualTo("id_ong", FirebaseAuth.getInstance().getUid());

        FirestoreRecyclerOptions<SolicitarCampanha> options = new FirestoreRecyclerOptions.Builder<SolicitarCampanha>()
                .setQuery(query, SolicitarCampanha.class)
                .build();

        adapter = new FeedCampanhaAdapter(options, this);

        feedCampanhaAberta.setHasFixedSize(true);
        feedCampanhaAberta.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedCampanhaAberta.setAdapter(adapter);
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

    @Override
    public void onItemClickCampanha(SolicitarCampanha snapshot, int position) {
        int arrec = snapshot.getQtd_arrecadado();
        int limi = snapshot.getQtd_limite();

        String x1 = String.valueOf(arrec);
        String x2 = String.valueOf(limi);

        Intent i = new Intent(OngStatusCampanhaAberta.this, OngStatusCampanhaAbertaEspecifico.class);
        i.putExtra("id_campanha", snapshot.getId_campanha());
        i.putExtra("titulo_camapanha", snapshot.getTitulo());
        i.putExtra("qtd_arrecadado", x1);
        i.putExtra("qtd_limite", x2);
        startActivity(i);
    }
}