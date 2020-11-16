package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedDoacaoUnicaAdapter;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

public class DoadorDoacaoAprovadas extends AppCompatActivity implements FeedDoacaoUnicaAdapter.OnListItemClick {

    private RecyclerView feedDoacoesAprovadas;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_doacao_aprovadas);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesAprovadas = findViewById(R.id.recycler_DoacoesAprovacao);

        feedDoacoesAprovadas.addItemDecoration(new DividerItemDecoration(feedDoacoesAprovadas.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Aguardando").whereEqualTo("origem", "Doador")
                .whereEqualTo("status", "Pendente")
                .whereEqualTo("id_user", FirebaseAuth.getInstance().getUid());

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedDoacaoUnicaAdapter(options,this);

        feedDoacoesAprovadas.setHasFixedSize(true);
        feedDoacoesAprovadas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedDoacoesAprovadas.setAdapter(adapter);

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
    public void onItemClickDoacao(final Doacao snapshot, int position) {
        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(snapshot.getId_ong());
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Map<String, Object> x = document.getData();

                        String nome_ong = x.get("username").toString();
                        String id_ong = x.get("uuid").toString();
                        String foto_ong = x.get("profileUrl").toString();

                        Intent i = new Intent(DoadorDoacaoAprovadas.this, DoadorClickDoar.class);
                        i.putExtra("id_doacao", snapshot.getId());
                        i.putExtra("id_outro", nome_ong);
                        i.putExtra("nome_outro", id_ong);
                        i.putExtra("foto_outro", foto_ong);
                        startActivity(i);
                    }
                }
            }
        });
    }
}