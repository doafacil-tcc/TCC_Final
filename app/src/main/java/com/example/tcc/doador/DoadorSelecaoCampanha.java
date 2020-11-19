package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.DoacaoComMatch;
import com.example.tcc.R;
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

    private RadioGroup radioGroup ;
    private RadioButton rbt1, rbt2;
    private EditText data_pessoalmente, data_motorista, hora_hotorista, editText;;
    private String mTitulo, mQtd;
    private String mData;
    private String mHora;
    private String mEndereco;
    private String mTipoEntrega;
    private String mIdOng, mNomeOng, idItemCampanha, mCategoria, fotoCampanha, mDescricao, tempo, mFoto2, mFoto3, mAvatar, text;
    private Button btnParticipar;
    private EditText qtd_doar;
    private Long t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_selecao_campanha);

        idItemCampanha = getIntent().getStringExtra("id_campanha");
        qtd_doar = findViewById(R.id.edtQuantidade);
        radioGroup = findViewById(R.id.radioGroup);
        rbt1 = findViewById(R.id.radioButton1);
        rbt2 = findViewById(R.id.radioButton2);
        data_pessoalmente = findViewById(R.id.edtPessoalmente);
        data_motorista = findViewById(R.id.edtDataMotorista);
        hora_hotorista = findViewById(R.id.edtHoraMotorista);
        btnParticipar = findViewById(R.id.btnAceitarCampanha);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(!rbt1.isChecked()){
                    data_pessoalmente.setEnabled(false);
                }
                else{
                    data_pessoalmente.setEnabled(true);
                }
                if(!rbt2.isChecked()){
                    data_motorista.setEnabled(false);
                    hora_hotorista.setEnabled(false);
                }
                else{
                    data_motorista.setEnabled(true);
                    hora_hotorista.setEnabled(true);
                }
            }
        });

        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncrementarNaCampanha();
            }
        });
        ColetaDadosRoupaCampanha();
    }

    private void IncrementarNaCampanha() {
        t = System.currentTimeMillis();
        final String temp = t.toString();
        tempo = temp;
        String uid = FirebaseAuth.getInstance().getUid();
        mQtd = qtd_doar.getText().toString();

        if (!mQtd.isEmpty()) {

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
                                    mQtd, "null", "null", nomeUser, fotoCampanha, "null", "null",
                                    "null", "campanha", mCategoria, temp);
                            FirebaseFirestore.getInstance().collection("Aguardando")
                                    .document(idItemCampanha + temp)
                                    .set(increment_campanha)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            definirEntrega();

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
            Toast.makeText(getApplicationContext(), "Preencha tudo corretamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void definirEntrega() {

        if (rbt1.isChecked()  && !data_pessoalmente.getText().toString().isEmpty()){

            salvarDBaguardando();

            new CountDownTimer(6000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(DoadorSelecaoCampanha.this, DoadorMainActivity.class);
                    startActivity(i);
                    finish();
                }
            }.start();
        }
        else if(rbt1.isChecked()  && data_pessoalmente.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha todos os Campos Necessários", Toast.LENGTH_SHORT).show();
        }

        if (rbt2.isChecked()  && !data_motorista.getText().toString().isEmpty() && !hora_hotorista.getText().toString().isEmpty()){

            salvarDBaguardando();

            new CountDownTimer(6000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(DoadorSelecaoCampanha.this, DoadorMainActivity.class);
                    startActivity(i);
                    finish();
                }
            }.start();
        }
        else if(rbt2.isChecked()  && (data_motorista.getText().toString().isEmpty() || hora_hotorista.getText().toString().isEmpty())){
            Toast.makeText(getApplicationContext(), "Preencha todos os Campos Necessários", Toast.LENGTH_SHORT).show();
        }

    }

    private void salvarDBaguardando() {


                        if (rbt1.isChecked()){
                            mData = data_pessoalmente.getText().toString();
                            mHora = "Horario Comercial";
                            mTipoEntrega = "Pessoalmente";
                            DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mIdOng);
                            docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            Map<String, Object> x = document.getData();
                                            mEndereco = x.get("endereco").toString();
                                        }
                                    }
                                }
                            });
                        }
                        if (rbt2.isChecked()){
                            mData = data_motorista.getText().toString();
                            mHora = hora_hotorista.getText().toString();
                            mTipoEntrega = "Retirado";
                            DocumentReference docRef3 = FirebaseFirestore.getInstance().collection("userDoador").document(FirebaseAuth.getInstance().getUid());
                            docRef3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            Map<String, Object> x = document.getData();
                                            mEndereco = x.get("endereco").toString();
                                        }
                                    }
                                }
                            });
                        }


                        DoacaoComMatch doacao = new DoacaoComMatch(idItemCampanha, FirebaseAuth.getInstance().getUid(), mIdOng, mTitulo, mQtd,
                                null, null, mDescricao, fotoCampanha, mFoto2, mFoto3, "Em_Transito", "Campanha",
                                mCategoria, null, mData, mHora, mEndereco, mTipoEntrega);

                        FirebaseFirestore.getInstance().collection("Finalizadas")
                                .document(idItemCampanha+tempo)
                                .set(doacao)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Doação Registrada com Sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                })

                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("Teste", e.getMessage());
                                    }
                                });

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
                        mTitulo = x.get("titulo").toString();
                        mDescricao = x.get("objetivo_campanha").toString();
                        String mQtd = x.get("qtd_arrecadado").toString();
                        String mObjetivo = x.get("objetivo_campanha").toString();
                        fotoCampanha = x.get("imgUrl1_campanha").toString();
                        mIdOng = x.get("id_ong").toString();

                        TextView categoria = (TextView) findViewById(R.id.txtSelecaoCampanhaCategoria);
                        TextView titulo = (TextView) findViewById(R.id.txtSelecaoCampanhaTitulo);
                        TextView qtd = (TextView) findViewById(R.id.txtSelecaoCampanhaQuantidade);
                        TextView obejtivo = (TextView) findViewById(R.id.txtSelecaoCampanhaDescrição);
                        ImageView foto1 = (ImageView) findViewById(R.id.imgSelecaoCampanha);
                        categoria.setText(mCategoria);
                        titulo.setText(mTitulo);
                        qtd.setText(mQtd);
                        obejtivo.setText(mObjetivo);
                        Picasso.get().load(fotoCampanha).into(foto1);

                        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mIdOng);
                        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {

                                        Map<String, Object> x = document.getData();

                                        mNomeOng = x.get("username").toString();
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