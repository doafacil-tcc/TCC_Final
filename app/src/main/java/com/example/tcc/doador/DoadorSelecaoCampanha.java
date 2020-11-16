package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.R;
import com.example.tcc.ong.OngStatusCampanhaAberta;
import com.example.tcc.ong.OngStatusCampanhaAbertaEspecifico;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class DoadorSelecaoCampanha extends AppCompatActivity {

    String mIdOng, mNomeOng, fotoOng, idItemCampanha, mCategoria, mFoto1;
    Button btnParticipar;
    EditText qtd_doar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_selecao_campanha);

        idItemCampanha = DoadorDoacoesCampanhaFragment.id_Clicked_campanha;
        qtd_doar = findViewById(R.id.edtQuantidade);

        btnParticipar = findViewById(R.id.btnAceitarCampanha);

        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncrementarNaCampanha();
            }
        });

        ColetaDadosRoupaCampanha();
    }

    private void IncrementarNaCampanha() {
        Long t = System.currentTimeMillis();
        final String temp = t.toString();
        String uid = FirebaseAuth.getInstance().getUid();

        if (!qtd_doar.getText().toString().isEmpty()) {

            DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userDoador").document(uid);
            docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map<String, Object> x = document.getData();

                            String nomeUser = x.get("username").toString();

                            Doacao increment_campanha = new Doacao(idItemCampanha, FirebaseAuth.getInstance().getUid(), mIdOng, "null",
                                    qtd_doar.getText().toString(), "null", "null", nomeUser, mFoto1, "null", "null", "null", "campanha", mCategoria, temp);
                            FirebaseFirestore.getInstance().collection("Aguardando")
                                    .document(idItemCampanha + temp)
                                    .set(increment_campanha)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent i = new Intent(DoadorSelecaoCampanha.this, DoadorClickDoar.class);
                                            i.putExtra("id_doacao", idItemCampanha+temp);
                                            i.putExtra("id_outro", mIdOng);
                                            i.putExtra("nome_outro", mNomeOng);
                                            i.putExtra("foto_outro", fotoOng);
                                            startActivity(i);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                        }
                                    });

                        }
                    }
                }
            });

        }
        else{
            Toast.makeText(getApplicationContext(), "Coloque o número de itens", Toast.LENGTH_SHORT).show();
        }
    }

    private void ColetaDadosRoupaCampanha() {

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Campanha").document(idItemCampanha);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Map<String, Object> x = document.getData();

                        mCategoria = x.get("categoria").toString();
                        String mtitulo = x.get("titulo").toString();
                        String mQtd = x.get("qtd_arrecadado").toString();
                        String mObjetivo = x.get("objetivo_campanha").toString();
                        mFoto1 = x.get("imgUrl1_campanha").toString();
                        mIdOng = x.get("id_ong").toString();

                        TextView categoria = (TextView) findViewById(R.id.txtSelecaoCampanhaCategoria);
                        TextView titulo = (TextView) findViewById(R.id.txtSelecaoCampanhaTitulo);
                        TextView qtd = (TextView) findViewById(R.id.txtSelecaoCampanhaQuantidade);
                        TextView obejtivo = (TextView) findViewById(R.id.txtSelecaoCampanhaDescrição);
                        ImageView foto1 = (ImageView) findViewById(R.id.imgSelecaoCampanha);
                        categoria.setText(mCategoria);
                        titulo.setText(mtitulo);
                        qtd.setText(mQtd);
                        obejtivo.setText(mObjetivo);
                        Picasso.get().load(mFoto1).into(foto1);

                        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mIdOng);
                        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                                        Map<String, Object> x = document.getData();

                                        mNomeOng = x.get("username").toString();
                                        fotoOng = x.get("profileUrl").toString();
                                        String mEndereco = x.get("endereco").toString();
                                        String mSite = x.get("site").toString();

                                        TextView username = (TextView) findViewById(R.id.txtSelecaoCampanhaDoador);
                                        TextView endereco = (TextView) findViewById(R.id.txtSelecaoCampanhaEndereco);
                                        TextView site = (TextView) findViewById(R.id.txtSelecaoCampanhaSite);
                                        username.setText(mNomeOng);
                                        endereco.setText(mEndereco);
                                        site.setText(mSite);

                                    }
                                }
                            }
                        });

                    }
                }
            }
        });

    }
}