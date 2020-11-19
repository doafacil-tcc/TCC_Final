package com.example.tcc.ong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedCampanhaAbertaAdapter;
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

public class OngStatusCampanhaAbertaEspecifico extends AppCompatActivity implements FeedCampanhaAbertaAdapter.OnListItemClick {

    private RecyclerView feedCampanhaEmAbertoEspecifico;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    private TextView qtd_atual, qtd_limite;
    private EditText edtValor_ext;
    private String idCampanha, tituloCamapnha, qtdArrec, qtdLimi, idDoador, valor_externo;
    private Button btnAdicExt, btnEncerrarCampanha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_capanha_aberta);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedCampanhaEmAbertoEspecifico = findViewById(R.id.recycler_Campanha_Aberta);
        Toolbar toolbar = findViewById(R.id.toolbar_statusCampanhaAberta);
        qtd_atual = findViewById(R.id.textView16);
        qtd_limite = findViewById(R.id.textView17);
        edtValor_ext = findViewById(R.id.editTextNumber);
        btnAdicExt = findViewById(R.id.btcEnviarItens);
        btnEncerrarCampanha = findViewById(R.id.button3);

        btnAdicExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                valor_externo = edtValor_ext.getText().toString();
                final int adic_ext = Integer.parseInt(valor_externo);
                DocumentReference docRef = FirebaseFirestore.getInstance().collection("Campanha").document(idCampanha);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                int qtd_arrec = document.getLong("qtd_arrecadado").intValue();
                                idDoador = document.getString("id_user");

                                qtd_arrec = qtd_arrec + adic_ext;

                                FirebaseFirestore.getInstance().collection("Campanha").document(idCampanha)
                                        .update("qtd_arrecadado", qtd_arrec);

                                Intent i = new Intent(OngStatusCampanhaAbertaEspecifico.this, OngStatusCampanhaAberta.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                });
            }
        });

        btnEncerrarCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("Campanha").document(idCampanha)
                        .update("status", "Finalizada");
                Intent i = new Intent(OngStatusCampanhaAbertaEspecifico.this, OngStatusCampanhaAberta.class);
                startActivity(i);
                finish();
            }
        });


        idCampanha = getIntent().getStringExtra("id_campanha");
        tituloCamapnha = getIntent().getStringExtra("titulo_camapanha");
        qtdArrec = getIntent().getStringExtra("qtd_arrecadado");
        qtdLimi = getIntent().getStringExtra("qtd_limite");

        toolbar.setTitle(tituloCamapnha);
        qtd_atual.setText("Quantidade Atual: " + qtdArrec);
        qtd_limite.setText("Quantidade Meta: " + qtdLimi);

        feedCampanhaEmAbertoEspecifico.addItemDecoration(new DividerItemDecoration(feedCampanhaEmAbertoEspecifico.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Aguardando")
                .whereEqualTo("unica_ou_campanha", "campanha")
                .whereEqualTo("id_ong", FirebaseAuth.getInstance().getUid())
                .whereEqualTo("id", idCampanha);

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedCampanhaAbertaAdapter(options,this);

        feedCampanhaEmAbertoEspecifico.setHasFixedSize(true);
        feedCampanhaEmAbertoEspecifico.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedCampanhaEmAbertoEspecifico.setAdapter(adapter);

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
    public void onItemClickCampanhaAberta(Doacao snapshot, int position) {
        String qtd = snapshot.getQtd();
        String time = snapshot.getOrigem();
        final int qtd_adic = Integer.parseInt(qtd);

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Campanha").document(idCampanha);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        int qtd_arrec = document.getLong("qtd_arrecadado").intValue();
                        idDoador = document.getString("id_user");

                        qtd_arrec = qtd_arrec + qtd_adic;

                        FirebaseFirestore.getInstance().collection("Campanha").document(idCampanha)
                                .update("qtd_arrecadado", qtd_arrec);

                    }
                }
            }
        });
        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("Aguardando").document(idCampanha + time);
        docRef2.delete();
        FirebaseFirestore.getInstance().collection("Finalizadas").document(idCampanha + time)
                .update("status", "Finalizada");



        Intent i = new Intent(OngStatusCampanhaAbertaEspecifico.this, OngStatusCampanhaAberta.class);
        startActivity(i);
        finish();
    }
}