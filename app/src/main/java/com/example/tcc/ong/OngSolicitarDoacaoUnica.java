package com.example.tcc.ong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.SolicitarDoacaoUnica;
import com.example.tcc.R;
import com.example.tcc.doador.DoadorDoacaoFinalizada;
import com.example.tcc.doador.DoadorMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.UUID;

public class OngSolicitarDoacaoUnica extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner mSpnCategoria, mSpnQuantidade;
    String spnCategoria, spnQuantidade, uid, tipo, descricao;
    Button btnEnviarDoacaoUnica;
    EditText mTipo, mDescricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_solicitar_unica);

        mSpnCategoria = findViewById(R.id.spnCategoriaSolicitarUnica);
        mSpnQuantidade = findViewById(R.id.spnQtdSolicitarUnica);
        mTipo = findViewById(R.id.edtTipoSolicitarUnica);
        mDescricao = findViewById(R.id.edtDescricaoSolicitarUnica);
        btnEnviarDoacaoUnica = findViewById(R.id.btnEnviarSolicitarUnica);

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this, R.array.spnCategoria, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<CharSequence> adapterQtd = ArrayAdapter.createFromResource(this, R.array.spnQuantidadeDoacao, android.R.layout.simple_spinner_item);
        adapterQtd.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpnCategoria.setAdapter(adapterCategoria);
        mSpnCategoria.setOnItemSelectedListener(this);
        mSpnQuantidade.setAdapter(adapterQtd);
        mSpnQuantidade.setOnItemSelectedListener(this);

        btnEnviarDoacaoUnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarDoacaoUnica();
            }

            private void EnviarDoacaoUnica() {
                uid = FirebaseAuth.getInstance().getUid();
                tipo = mTipo.getText().toString();
                descricao = mDescricao.getText().toString();

                if (  uid.isEmpty() || spnCategoria.equals("Escolha...")
                        || spnQuantidade.equals("Escolha...") || tipo.isEmpty() || descricao.isEmpty()) {
                    Toast.makeText(OngSolicitarDoacaoUnica.this, "Preencha tudo parça. TA LOKÃO?", Toast.LENGTH_SHORT).show();
                    Log.i("roupa", "não foi");
                    return;
                }
                saveDoacaoUnica();
            }

            private void saveDoacaoUnica() {

                final Task<DocumentSnapshot> ref_foto = FirebaseFirestore.getInstance().collection("userONG").document(uid)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {

                                        Map<String, Object> x = document.getData();
                                        final String foto_ong = x.get("profileUrl").toString();
                                        final String iddoacao = UUID.randomUUID().toString();
                                        final String status = "Aguardando";
                                        final String unica_ou_camp = "unica";
                                        final String origem = "ONG";


                                        Doacao doacao = new Doacao(iddoacao, null, uid, tipo, spnQuantidade, null, null,
                                                descricao, foto_ong, null, null, status, unica_ou_camp, spnCategoria, origem);

                                        Log.i("foto_ong", foto_ong);

                                        FirebaseFirestore.getInstance().collection("Aguardando")
                                                .document(iddoacao)
                                                .set(doacao).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Intent intent = new Intent(OngSolicitarDoacaoUnica.this, OngMainActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                            }
                                        });
                                    }
                                }
                            }
                        });

//                final String iddoacao = UUID.randomUUID().toString();
//                final String status = "Aguardando";
//                final String unica_ou_camp = "unica";
//                final String origem = "ONG";
//
//
//                Doacao doacao = new Doacao(iddoacao, null, uid, tipo, spnQuantidade, null, null,
//                        descricao, foto_ong, null, null, status, unica_ou_camp, spnCategoria, origem);

//                FirebaseFirestore.getInstance().collection("Aguardando")
//                        .document(iddoacao)
//                        .set(doacao).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Intent intent = new Intent(OngSolicitarDoacaoUnica.this, OngMainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spn = adapterView.getItemAtPosition(i).toString();
        spnCategoria = mSpnCategoria.getSelectedItem().toString();
        spnQuantidade = mSpnQuantidade.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}