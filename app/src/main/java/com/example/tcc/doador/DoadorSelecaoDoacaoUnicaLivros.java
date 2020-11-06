package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class DoadorSelecaoDoacaoUnicaLivros extends AppCompatActivity {

    String mUser;
    String idItemDoacao;
    String mFoto2, mFoto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_selecao_doacao_unica_livros);

        idItemDoacao = DoadorDoacaoUnicaFragment.id_Clicked_Livros_doacao;

        ColetaDadosLivrosDoador();

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
                        mUser = x.get("id_ong").toString();

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

                        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mUser);
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