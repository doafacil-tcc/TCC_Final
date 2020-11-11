package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcc.Entities.ChatActivity;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class DoadorClickDoar extends AppCompatActivity {

    private RadioGroup radioGroup ;
    private RadioButton rbt1, rbt2, rbt3;
    private EditText data_pessoalmente, data_motorista, hora_hotorista;
    private Button btnEnviar;
    private String idDoacao, idOng, nomeOng, fotoOng, mFoto2, mFoto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_click_doar);

        radioGroup = findViewById(R.id.radioGroup);
        rbt1 = findViewById(R.id.radioButton1);
        rbt2 = findViewById(R.id.radioButton2);
        rbt3 = findViewById(R.id.radioButton3);
        data_pessoalmente = findViewById(R.id.edtPessoalmente);
        data_motorista = findViewById(R.id.edtDataMotorista);
        hora_hotorista = findViewById(R.id.edtHoraMotorista);
        btnEnviar = findViewById(R.id.btn_concluido);

        idDoacao = getIntent().getStringExtra("id_doacao");
        idOng = getIntent().getStringExtra("id_outro");
        nomeOng = getIntent().getStringExtra("nome_outro");
        fotoOng = getIntent().getStringExtra("foto_outro");

        Log.i("yyy", idDoacao);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbt1.isChecked()  && !data_pessoalmente.getText().toString().isEmpty()){
                        alterarDB();
                        Toast.makeText(getApplicationContext(), "Doação Registrada com Sucesso!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DoadorClickDoar.this, DoadorMainActivity.class);
                        startActivity(i);
                }
                else if(rbt1.isChecked()  && data_pessoalmente.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os Campos Necessários", Toast.LENGTH_SHORT).show();
                }

                if (rbt2.isChecked()  && !data_motorista.getText().toString().isEmpty() && !hora_hotorista.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Doação Registrada com Sucesso!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DoadorClickDoar.this, DoadorMainActivity.class);
                        startActivity(i);
                }
                else if(rbt2.isChecked()  && (data_motorista.getText().toString().isEmpty()) || hora_hotorista.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Preencha todos os Campos Necessários", Toast.LENGTH_SHORT).show();
                }

                if (rbt3.isChecked()){
                    Intent i = new Intent(DoadorClickDoar.this, ChatActivity.class);
                    i.putExtra("id_outro", idOng);
                    i.putExtra("nome_outro", nomeOng);
                    i.putExtra("foto_outro", fotoOng);
                    startActivity(i);
                }
            }
        });

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



    }

    private void alterarDB() {

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Aguardando").document(idDoacao);
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
                        String mUserOng = x.get("id_ong").toString();
                        String mCondicao = x.get("condicao").toString();
                        String mOrigem = x.get("origem").toString();
                        String mStatus = x.get("status").toString();
                        String mTamanho = x.get("tamanho").toString();
                        String mUnic_ouCamp = x.get("unica_ou_campanha").toString();



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

                                        TextView username = (TextView) findViewById(R.id.txtSelecaoRoupaDoadorDoador);
                                        TextView endereco = (TextView) findViewById(R.id.txtSelecaoRoupaDoadorEndereco);
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