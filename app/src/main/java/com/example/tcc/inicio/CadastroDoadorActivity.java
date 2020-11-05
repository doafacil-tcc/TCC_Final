package com.example.tcc.inicio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.example.tcc.doador.DoadorMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class CadastroDoadorActivity extends AppCompatActivity {

    EditText mNome, mCPF, mEndereço, mCEP, mEmail, mTelefone, mSenha;
    Button btnEnviarCadastroDoador;
    Uri mSelectedUri;
    ImageView imgAvatarDoador;
    Button mBtnSelectPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity_cadastro_doador);

        mNome = findViewById(R.id.edtNomeCadastroDoador);
        mCPF = findViewById(R.id.edtCPFCadastroDoador);
        mEndereço = findViewById(R.id.edtEnderecoCadastroDoador);
        mCEP = findViewById(R.id.edtCEPCadastroDoador);
        mEmail = findViewById(R.id.edtEmailCadastroDoador);
        mTelefone = findViewById(R.id.edtTelefoneCadastroDoador);
        mSenha = findViewById(R.id.edtPasswordCadastroDoador);
        btnEnviarCadastroDoador = findViewById(R.id.btnEnviarCadastroDoador);
        mBtnSelectPhoto = findViewById(R.id.btnAvatarDoador);
        imgAvatarDoador = findViewById(R.id.ImgAvatarCadastroDoador);

        mBtnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

        btnEnviarCadastroDoador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            mSelectedUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                imgAvatarDoador.setImageDrawable(new BitmapDrawable(bitmap));
                mBtnSelectPhoto.setAlpha(0);
            } catch (IOException e) {


            }

        }
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void createUser() {

        String nome = mNome.getText().toString();
        String email = mEmail.getText().toString();
        String senha = mSenha.getText().toString();
        String CPF = mCPF.getText().toString();
        String telefone = mTelefone.getText().toString();
        String CEP = mCEP.getText().toString();
        String endereco = mEndereço.getText().toString();

        if (nome == null || nome.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty() || CPF == null || CPF.isEmpty()
                || telefone == null || telefone.isEmpty() || CEP == null || CEP.isEmpty() || endereco == null || endereco.isEmpty() || mSelectedUri == null) {

            Toast.makeText(this, "Preencha todos os campos, parça! NÃO ESQUECE DA FOTO ARROMBADO", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.i("Teste", task.getResult().getUser().getUid());
                            saveUserInFirebase();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Teste", e.getMessage());
                    }
                });

    }


    private void saveUserInFirebase() {

        if (mSelectedUri != null ) {

            String filename = UUID.randomUUID().toString();
            final StorageReference ref = FirebaseStorage.getInstance().getReference("/AvatarDoador/" + filename);
            ref.putFile(mSelectedUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.i("Teste", uri.toString());

                                    String uid = FirebaseAuth.getInstance().getUid();
                                    String username = mNome.getText().toString();
                                    String endereco = mEndereço.getText().toString();
                                    String email = mEmail.getText().toString();
                                    String cep = mCEP.getText().toString();
                                    String telefone = mTelefone.getText().toString();
                                    String cpf = mCPF.getText().toString();
                                    String avatarUrl = uri.toString();
                                    //                               mNome.getText().toString();

                                    User userDoador = new User(uid, username, avatarUrl, cpf, cep, email, telefone, null, endereco, null);

                                    FirebaseFirestore.getInstance().collection("userDoador")
                                            .document(uid)
                                            .set(userDoador)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(CadastroDoadorActivity.this, DoadorMainActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(intent);
                                                }
                                            })

                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.i("Teste", e.getMessage());
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("teste", e.getMessage(), e);
                        }
                    });
        }
    }
}