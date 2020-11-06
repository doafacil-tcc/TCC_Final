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

public class DoadorSelecaoCampanha extends AppCompatActivity {

    String mOng;
    String idItemCampanha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_selecao_campanha);

        idItemCampanha = DoadorDoacoesCampanhaFragment.id_Clicked_campanha;

        ColetaDadosRoupaCampanha();
    }

    private void ColetaDadosRoupaCampanha() {

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Campanha").document(idItemCampanha);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> x = document.getData();

                        String mCategoria = x.get("categoria").toString();
                        String mtitulo = x.get("titulo").toString();
                        String mQtd = x.get("qtd_arrecadado").toString();
                        String mObjetivo = x.get("objetivo_campanha").toString();
                        String mFoto1 = x.get("imgUrl1_campanha").toString();
                        mOng = x.get("id_ong").toString();

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

                        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(mOng);
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
                                        String mSite = x.get("site").toString();

                                        TextView username = (TextView) findViewById(R.id.txtSelecaoCampanhaDoador);
                                        TextView endereco = (TextView) findViewById(R.id.txtSelecaoCampanhaEndereco);
                                        TextView site = (TextView) findViewById(R.id.txtSelecaoCampanhaSite);
                                        username.setText(mNome);
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