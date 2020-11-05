package com.example.tcc.inicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.util.Log;

import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.example.tcc.doador.DoadorMainActivity;
import com.example.tcc.ong.OngMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

public class TelaInicio extends AppCompatActivity {

    private Object view;
    private int counter;
    String Cpf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity_telainicio);

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                CheckOng();
            }
        }.start();
        }

    private void CheckOng() {

        String uid = FirebaseAuth.getInstance().getUid();

        if(uid == null){
            Intent i = new Intent(com.example.tcc.inicio.TelaInicio.this, EscolhaInicio.class);
            startActivity(i);
            finish();
        }

        Log.i("TAG", uid + " ok");

        if(uid != null) {

            DocumentReference docRef = FirebaseFirestore.getInstance().collection("userDoador").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.i("TAG", "DocumentSnapshot data: " + document.getData());

                            Map<String, Object> x = document.getData();

                            Log.i("TAG", "DocumentSnapshot data: " + x.get("cpf"));

                            Log.i("TAG", "DocumentSnapshot data: " + x.get("cnpj"));
                            if(x.get("cpf") != null){
                                Intent i = new Intent(com.example.tcc.inicio.TelaInicio.this, DoadorMainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        } else {
                            Log.i("TAG", "No such document");
                            Intent i = new Intent(com.example.tcc.inicio.TelaInicio.this, OngMainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Log.i("TAG", "get failed with ", task.getException());
                    }
                }
            });
        }

    }

}
