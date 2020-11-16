package com.example.tcc.ong;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc.Entities.ChatActivity;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class OngSelecaoDoacaoUnicaLivros extends AppCompatActivity {

    String mUser;
    String idItem;
    String mFoto2, mFoto3;
    Button btnChat, btnAceitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_selecao_doacao_unica_livros);

        idItem = OngFeedDoacaoFragment.id_Clicked_Livros;

        btnChat = findViewById(R.id.btnChamarChatOngLivros);
        btnAceitar = findViewById(R.id.btnAceitarDoacaoLivros);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarChat();
            }
        });

        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AceitarDoacao();
            }
        });

        ColetaDadosLivros();

    }

    private void AceitarDoacao() {

        FirebaseFirestore.getInstance().collection("Aguardando").document(idItem).update("id_ong", FirebaseAuth.getInstance().getUid());
        FirebaseFirestore.getInstance().collection("Aguardando").document(idItem).update("status", "Pendente");

        Intent i = new Intent(OngSelecaoDoacaoUnicaLivros.this, OngAceitacaoEnviada.class);
        startActivity(i);
        finish();

    }

    private void IniciarChat() {

        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userDoador").document(mUser);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> x = document.getData();

                        String nome = x.get("username").toString();
                        String id = x.get("uuid").toString();
                        String tel = x.get("telefone").toString();
                        String avatar = x.get("profileUrl").toString();
                        String endereco = x.get("endereco").toString();
                        String email = x.get("email").toString();
                        String cpf = x.get("cpf").toString();
                        String cep = x.get("cep").toString();

                        User u = new User(id,nome,avatar,cpf,cep,email,tel,null,endereco,null);

                        Intent i = new Intent(OngSelecaoDoacaoUnicaLivros.this, ChatActivity.class);
                        i.putExtra("id_outro", u.getUuid());
                        i.putExtra("nome_outro", u.getUsername());
                        i.putExtra("foto_outro", u.getProfileUrl());
                        startActivity(i);

                    }

                }
            }
        });
    }

    private void ColetaDadosLivros() {

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Aguardando").document(idItem);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> x = document.getData();

                        String mCategoria = x.get("categoria").toString();
                        String mCondicao = x.get("condicao").toString();
                        String mTipo = x.get("tipo").toString();
                        String mQtd = x.get("qtd").toString();
                        String mDescricao = x.get("descricao").toString();
                        String mFoto1 = x.get("imgUrl1").toString();
                        if(x.get("imgUrl2") != null) {
                            mFoto2 = x.get("imgUrl2").toString();
                        }
                        if(x.get("imgUrl2") == null) {
                            mFoto2 = "00";
                        }
                        if(x.get("imgUrl3") != null) {
                            mFoto3 = x.get("imgUrl3").toString();
                        }
                        if(x.get("imgUrl3") == null) {
                            mFoto3 = "00";
                        }
                        mUser = x.get("id_user").toString();

                        TextView categoria = (TextView) findViewById(R.id.txtSelecaoLivrosOngCategoria);
                        TextView condicao = (TextView) findViewById(R.id.txtSelecaoLivrosOngCondição);
                        TextView tipo = (TextView) findViewById(R.id.txtSelecaoLivrosOngTipo);
                        TextView qtd = (TextView) findViewById(R.id.txtSelecaoLivrosOngQuantidade);
                        TextView descricao = (TextView) findViewById(R.id.txtSelecaoLivrosOngDescrição);
                        ImageView foto1 = (ImageView) findViewById(R.id.imgSelecaoLivrosOng);
                        ImageView foto2 = (ImageView) findViewById(R.id.imgSelecaoLivrosOng2);
                        ImageView foto3 = (ImageView) findViewById(R.id.imgSelecaoLivrosOng3);
                        categoria.setText(mCategoria);
                        condicao.setText(mCondicao);
                        tipo.setText(mTipo);
                        qtd.setText(mQtd);
                        descricao.setText(mDescricao);
                        Picasso.get().load(mFoto1).into(foto1);
                        Picasso.get().load(mFoto2).into(foto2);
                        Picasso.get().load(mFoto3).into(foto3);

                        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userDoador").document(mUser);
                        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                                        Map<String, Object> x = document.getData();

                                        String mNome = x.get("username").toString();
                                        String mEndereco = x.get("endereco").toString();

                                        TextView username = (TextView) findViewById(R.id.txtSelecaoLivrosOngDoador);
                                        TextView endereco = (TextView) findViewById(R.id.txtSelecaoLivrosOngEndereco);
                                        username.setText(mNome);
                                        endereco.setText(mEndereco);

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