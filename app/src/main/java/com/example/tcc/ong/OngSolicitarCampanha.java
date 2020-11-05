package com.example.tcc.ong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tcc.Entities.SolicitarCampanha;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class OngSolicitarCampanha extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner mSpnCategoria;
    String spnCategoria, uid, titulo, descricao, breve_descricao, imagem1;
    EditText nQtndLimite, mTitulo, mDescricao, mBreveDescricao;
    Integer limite;
    Button btnEnviarCampanha, btnFotoCampanha;
    ImageView imgCampanha;
    Uri mSelectedUri1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_solicitar_campanha);

        mSpnCategoria = findViewById(R.id.spnCategoriaSolicitarCampanha);
        btnEnviarCampanha = findViewById(R.id.btnEnviarSolicitarCampanha);
        nQtndLimite = findViewById(R.id.edtQtndLimiteCampanha);
        mTitulo = findViewById(R.id.edtTituloSolicitarCampanha);
        mDescricao = findViewById(R.id.edtDescricaoSolicitarCampanha);
        mBreveDescricao = findViewById(R.id.edtBreveDescricaoCamapanha);
        btnFotoCampanha = findViewById(R.id.btnImagemCampanha);
        imgCampanha = findViewById(R.id.imgImagemCampanha);

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this, R.array.spnCategoria, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpnCategoria.setAdapter(adapterCategoria);
        mSpnCategoria.setOnItemSelectedListener(this);

        btnFotoCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto1();
            }
        });

        btnEnviarCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarCampanha();
            }

            private void EnviarCampanha() {

                uid = FirebaseAuth.getInstance().getUid();
                titulo = mTitulo.getText().toString();
                descricao = mDescricao.getText().toString();
                breve_descricao = mBreveDescricao.getText().toString();
//                limite = nQtndLimite.getText().length();

                if (uid.isEmpty() || spnCategoria.equals("Escolha...")
                        || titulo.isEmpty() || descricao.isEmpty() || breve_descricao.isEmpty() || (mSelectedUri1 == null)) {
                    Toast.makeText(OngSolicitarCampanha.this, "Preencha tudo parça. TA LOKÃO?", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveCampanha();
            }

            private void saveCampanha() {

                String foto1 = UUID.randomUUID().toString();
                final StorageReference ref1 = FirebaseStorage.getInstance().getReference("/FotosCampanha/" + uid + foto1);
                ref1.putFile(mSelectedUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imagem1 = uri.toString();
                                Log.i("sucesso", imagem1);
                            }
                        });
                    }
                });

                final String idcampanha = UUID.randomUUID().toString();
                int qtd_arrecadado = 0;
                final String status = "Em Aberto";
                final String unica_ou_camp = "campanha";

                Intent intent = new Intent(OngSolicitarCampanha.this, OngMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                new CountDownTimer(20000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {

                        SolicitarCampanha campanha = new SolicitarCampanha(idcampanha, null, uid, titulo, 0, 0, descricao,
                                breve_descricao, imagem1, status, unica_ou_camp, spnCategoria);

                        FirebaseFirestore.getInstance().collection("Campanha")
                                .document(idcampanha)
                                .set(campanha).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
                    }
                }.start();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 15) {
            mSelectedUri1 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mSelectedUri1);
                imgCampanha.setImageDrawable(new BitmapDrawable(bitmap));
                btnFotoCampanha.setAlpha(0);
            } catch (IOException e) { }
        }

    }

    private void selectPhoto1() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 15);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spn = adapterView.getItemAtPosition(i).toString();
        spnCategoria = mSpnCategoria.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}