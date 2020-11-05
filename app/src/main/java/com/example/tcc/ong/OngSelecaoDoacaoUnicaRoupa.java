package com.example.tcc.ong;

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

public class OngSelecaoDoacaoUnicaRoupa extends AppCompatActivity {

    String mUser;
    String idItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_selecao_doacao_unica_roupa);

        idItem = OngFeedDoacaoFragment.id_Clicked_roupa;
        Log.i("chama", idItem);

        ColetaDadosRoupa();

    }

    private void ColetaDadosRoupa() {

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
                        String mTamanho = x.get("tamanho").toString();
                        String mDescricao = x.get("descricao").toString();
                        String mFoto1 = x.get("imgUrl1").toString();
                        String mFoto2 = x.get("imgUrl2").toString();
                        String mFoto3 = x.get("imgUrl3").toString();
                        mUser = x.get("id_user").toString();

                        TextView categoria = (TextView) findViewById(R.id.txtSelecaoRoupaOngCategoria);
                        TextView condicao = (TextView) findViewById(R.id.txtSelecaoRoupaOngCondição);
                        TextView tipo = (TextView) findViewById(R.id.txtSelecaoRoupaOngTipo);
                        TextView qtd = (TextView) findViewById(R.id.txtSelecaoRoupaOngQuantidade);
                        TextView tamanho = (TextView) findViewById(R.id.txtSelecaoRoupaOngTamanho);
                        TextView descricao = (TextView) findViewById(R.id.txtSelecaoRoupaOngDescrição);
                        ImageView foto1 = (ImageView) findViewById(R.id.imgSelecaoRoupaOng);
                        ImageView foto2 = (ImageView) findViewById(R.id.imgSelecaoRoupaOng2);
                        ImageView foto3 = (ImageView) findViewById(R.id.imgSelecaoRoupaOng3);
                        categoria.setText(mCategoria);
                        condicao.setText(mCondicao);
                        tipo.setText(mTipo);
                        qtd.setText(mQtd);
                        tamanho.setText(mTamanho);
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

                                        TextView username = (TextView) findViewById(R.id.txtSelecaoRoupaOngDoador);
                                        TextView endereco = (TextView) findViewById(R.id.txtSelecaoRoupaOngEndereco);
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