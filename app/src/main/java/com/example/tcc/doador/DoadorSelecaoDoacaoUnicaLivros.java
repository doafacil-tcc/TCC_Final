package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcc.Entities.ChatActivity;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class DoadorSelecaoDoacaoUnicaLivros extends AppCompatActivity {

    String mUserOng;
    String idItemDoacao;
    String mFoto2, mFoto3;
    Button btnChat, btnDoar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_selecao_doacao_unica_livros);

        idItemDoacao = DoadorDoacaoUnicaFragment.id_Clicked_Livros_doacao;

        btnChat = findViewById(R.id.btnChamarChatUnicaLivros);
        btnDoar = findViewById(R.id.btnAceitarDoacaoLivros);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarChat();
            }
        });

        btnDoar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarDoacao();
            }
        });

        ColetaDadosLivrosDoador();

    }

    private void IniciarChat() {

        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mUserOng);
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
                        String site = x.get("site").toString();
                        String avatar = x.get("profileUrl").toString();
                        String endereco = x.get("endereco").toString();
                        String email = x.get("email").toString();
                        String cnpj = x.get("cnpj").toString();
                        String cep = x.get("cep").toString();

                        User u = new User(id,nome,avatar,null,cep,email,tel,cnpj,endereco,site);

                        Intent i = new Intent(DoadorSelecaoDoacaoUnicaLivros.this, ChatActivity.class);
                        i.putExtra("id_outro", u.getUuid());
                        i.putExtra("nome_outro", u.getUsername());
                        i.putExtra("foto_outro", u.getProfileUrl());
                        startActivity(i);
                        finish();
                    }

                }
            }
        });
    }

    private void IniciarDoacao() {

        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mUserOng);
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
                        String site = x.get("site").toString();
                        String avatar = x.get("profileUrl").toString();
                        String endereco = x.get("endereco").toString();
                        String email = x.get("email").toString();
                        String cnpj = x.get("cnpj").toString();
                        String cep = x.get("cep").toString();

                        User u = new User(id,nome,avatar,null,cep,email,tel,cnpj,endereco,site);

                        Intent i = new Intent(DoadorSelecaoDoacaoUnicaLivros.this, DoadorClickDoar.class);
                        i.putExtra("id_doacao", idItemDoacao);
                        i.putExtra("id_outro", u.getUuid());
                        i.putExtra("nome_outro", u.getUsername());
                        i.putExtra("foto_outro", u.getProfileUrl());
                        startActivity(i);
                        finish();
                    }

                }
            }
        });
    }

    private void ColetaDadosLivrosDoador() {

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Aguardando").document(idItemDoacao);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> x = document.getData();

                        String mCategoria = x.get("categoria").toString();
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
                        mUserOng = x.get("id_ong").toString();

                        TextView categoria = (TextView) findViewById(R.id.txtSelecaoLivrosDoadorCategoria);
                        TextView tipo = (TextView) findViewById(R.id.txtSelecaoLivrosDoadorTipo);
                        TextView qtd = (TextView) findViewById(R.id.txtSelecaoLivrosDoadorQuantidade);
                        TextView descricao = (TextView) findViewById(R.id.txtSelecaoLivrosDoadorDescrição);
                        ImageView foto1 = (ImageView) findViewById(R.id.imgSelecaoLivrosDoador);
                        categoria.setText(mCategoria);
                        tipo.setText(mTipo);
                        qtd.setText(mQtd);
                        descricao.setText(mDescricao);
                        Picasso.get().load(mFoto1).into(foto1);

                        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mUserOng);
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

                                        TextView username = (TextView) findViewById(R.id.txtSelecaoLivrosDoadorDoador);
                                        TextView endereco = (TextView) findViewById(R.id.txtSelecaoLivrosDoadorEndereco);
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