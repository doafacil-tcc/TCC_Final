package com.example.tcc.ong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcc.Entities.ChatActivity;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.example.tcc.doador.DoadorClickDoar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class OngConfirmarRecebimento extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4;
    String id_doacao;
    Button btnRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_confirmar_recebimento);

        id_doacao = getIntent().getStringExtra("id_doacao");
        txt1 = findViewById(R.id.txttipo);
        txt2 = findViewById(R.id.txtquantidade);
        txt3 = findViewById(R.id.txtdescricao);
        txt4 = findViewById(R.id.txtnomedoador);
        btnRecebido = findViewById(R.id.buttonrecebido);

        Log.i("id_doaçao", id_doacao);


        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("Finalizadas").document(id_doacao);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Map<String, Object> x = document.getData();

                        String tipo = x.get("tipo").toString();
                        String qtd = x.get("qtd").toString();
                        String descricao = x.get("descricao").toString();

                        txt1.setText("Tipo: " + tipo);
                        txt2.setText("Quantidade: " + qtd );
                        txt3.setText("Descrição: " + descricao);

                        DocumentReference docRef3 = FirebaseFirestore.getInstance().collection("userDoador").document(x.get("id_user").toString());
                        docRef3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                                        Map<String, Object> x = document.getData();

                                        String nome = x.get("username").toString();

                                        txt4.setText("Nome Doador: " + nome);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });


        btnRecebido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("Finalizadas").document(id_doacao).update("status", "Finalizada");
                Toast.makeText(getApplicationContext(), "Doação Recebida com Sucesso!", Toast.LENGTH_SHORT).show();

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {
                    }
                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();

            }
        });



    }
}