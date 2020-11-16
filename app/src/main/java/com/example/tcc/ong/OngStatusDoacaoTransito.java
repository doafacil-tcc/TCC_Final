package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedDoacaoUnicaAdapter;
import com.example.tcc.R;
import com.example.tcc.doador.DoadorSelecaoDoacaoUnicaRoupa;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OngStatusDoacaoTransito extends AppCompatActivity implements FeedDoacaoUnicaAdapter.OnListItemClick {

    private RecyclerView feedDoacoesEmTransito;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_doacao_transito);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesEmTransito = findViewById(R.id.recycler_Doacoes_transito);

        feedDoacoesEmTransito.addItemDecoration(new DividerItemDecoration(feedDoacoesEmTransito.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Finalizadas")
                .whereEqualTo("status", "Em_Transito")
                .whereEqualTo("id_ong", FirebaseAuth.getInstance().getUid());

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedDoacaoUnicaAdapter(options,this);

        feedDoacoesEmTransito.setHasFixedSize(true);
        feedDoacoesEmTransito.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedDoacoesEmTransito.setAdapter(adapter);
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
    public void onItemClickDoacao(Doacao snapshot, int position) {
        Intent i = new Intent(this, OngConfirmarRecebimento.class);
        i.putExtra("id_doacao", snapshot.getId());
        startActivity(i);
    }
}

