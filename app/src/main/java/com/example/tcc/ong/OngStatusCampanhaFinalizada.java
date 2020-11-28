package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tcc.Entities.FeedCampanhaAdapter;
import com.example.tcc.Entities.SolicitarCampanha;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OngStatusCampanhaFinalizada extends AppCompatActivity implements FeedCampanhaAdapter.OnListItemClick {

    private RecyclerView feedCampanhaFinalizada;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_campanha_finalizada);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedCampanhaFinalizada = findViewById(R.id.recycler_Campanha_Finalizada);

        feedCampanhaFinalizada.addItemDecoration(new DividerItemDecoration(feedCampanhaFinalizada.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Campanha").
                whereEqualTo("id_ong", FirebaseAuth.getInstance().getUid()).
                whereEqualTo("status", "Finalizada");

        FirestoreRecyclerOptions<SolicitarCampanha> options = new FirestoreRecyclerOptions.Builder<SolicitarCampanha>()
                .setQuery(query, SolicitarCampanha.class)
                .build();

        adapter = new FeedCampanhaAdapter(options, this);

        feedCampanhaFinalizada.setHasFixedSize(true);
        feedCampanhaFinalizada.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedCampanhaFinalizada.setAdapter(adapter);
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

    }
}