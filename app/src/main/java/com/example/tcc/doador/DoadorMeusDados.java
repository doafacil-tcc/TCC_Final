package com.example.tcc.doador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.example.tcc.inicio.EscolhaInicio;
import com.example.tcc.inicio.LoginActivityDoador;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


import java.util.Map;

public class DoadorMeusDados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_meus_dados);

        ColetaDados();

        Button btnEditarPerfil = findViewById(R.id.btnEditarPerfilDoador);
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DoadorMeusDados.this, DoadorEditarPerfil.class);
                startActivity(i);
                finish();
            }
        });

    }


    private void ColetaDados() {

        String uid = FirebaseAuth.getInstance().getUid();
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("userDoador").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i("TAG2", "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> x = document.getData();

                        String mNome = x.get("username").toString();
                        String mEndereco = x.get("endereco").toString();
                        String mFoto = x.get("profileUrl").toString();

                        TextView username = (TextView) findViewById(R.id.txtNomeMeusDadosDoador);
                        TextView endereco = (TextView) findViewById(R.id.txtEnderecoMeusDadosDoador);
                        ImageView foto = (ImageView) findViewById(R.id.imgAvataMeusDadosDoador);
                        username.setText(mNome);
                        endereco.setText(mEndereco);
                        Picasso.get().load(mFoto).into(foto);


                        Log.i("TAG2", "DocumentSnapshot data: " + mNome);

                        Log.i("TAG2", "DocumentSnapshot data: " + mEndereco);

                        Log.i("TAG2", "DocumentSnapshot data: " + mFoto);


                    }

                }
            }
        });

    }
}