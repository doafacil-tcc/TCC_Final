package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        if (!qtd_doar.getText().toString().isEmpty()) {

            final int doado = Integer.parseInt(qtd_doar.getText().toString());
            Log.i("lklk", String.valueOf(doado));

            DocumentReference docRef = FirebaseFirestore.getInstance().collection("Campanha").document(idItemCampanha);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            int qtd_arrec = document.getLong("qtd_arrecadado").intValue();

                            qtd_arrec = qtd_arrec + doado;

                            Log.i("lklk", String.valueOf(qtd_arrec));

                            FirebaseFirestore.getInstance().collection("Campanha").document(idItemCampanha)
                                    .update("qtd_arrecadado", qtd_arrec);
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