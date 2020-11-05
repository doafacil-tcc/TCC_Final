package com.example.tcc.doador;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class DoadorEditarPerfil extends AppCompatActivity {

    EditText mNome, mEndereco, mCEP, mTelefone, mSenha;
    Button btnEnviarDados;
    Uri mSelectedUri;
    ImageView imgAvatar;
    Button mBtnSelectPhoto;
    String avatarUrl;
    Object avatar_antigo;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_editar_perfil);

        mNome = findViewById(R.id.edtNomeEditarPerfilDoador);
        mEndereco = findViewById(R.id.edtEnderecoEditarPerfilDoador);
        mCEP = findViewById(R.id.edtCEPEditarPerfilDoador);
        mTelefone = findViewById(R.id.edtTelefoneEditarPerfilDoador);
        mSenha = findViewById(R.id.edtPasswordEditarPerfilDoador);
        btnEnviarDados = findViewById(R.id.btnEnviarEditarPerfilDoador);
        mBtnSelectPhoto = findViewById(R.id.btnAvatarEditarPerfilDoador);
        imgAvatar = findViewById(R.id.ImgAvatarEditarPerfilDoador);

        btnEnviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();

                if (mSelectedUri != null) {
                    new CountDownTimer(8000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            Intent i = new Intent(DoadorEditarPerfil.this, DoadorMeusDados.class);
                            startActivity(i);
                            finish();
                        }
                    }.start();
                }else {
                    Intent i = new Intent(DoadorEditarPerfil.this, DoadorMeusDados.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        mBtnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            mSelectedUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                imgAvatar.setImageDrawable(new BitmapDrawable(bitmap));
                mBtnSelectPhoto.setAlpha(0);
            } catch (IOException e) {

            }
        }
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void updateUser() {

        final String uid = FirebaseAuth.getInstance().getUid();
        String nome = mNome.getText().toString();
        String telefone = mTelefone.getText().toString();
        String CEP = mCEP.getText().toString();
        String endereco = mEndereco.getText().toString();
        String senha = mSenha.getText().toString();

        //Pegar o avatar antigo
        if(uid != null) {
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("userDoador").document(uid);
            Log.i("kkk", uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        Log.i("kkk", document.toString());
                        assert document != null;
                        if (document.exists()) {
                            Map<String, Object> x = document.getData();
                            Log.i("kkk", document.toString());
                            avatar_antigo = x.get("profileUrl");
                            Log.i("kkkk", avatar_antigo.toString());
                            ref = FirebaseStorage.getInstance().getReferenceFromUrl(avatar_antigo.toString());
                            Log.i("kkkkk", ref.toString());
                        }
                    }
                }
            });
        }

        //Atualizar com avatar novo e apagar antigo
        if(mSelectedUri != null) {
            String filename = UUID.randomUUID().toString();
            final StorageReference avatar_novo = FirebaseStorage.getInstance().getReference("AvatarDoador/" + filename);
            avatar_novo.putFile(mSelectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    avatar_novo.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.i("Testekkkk", uri.toString());
                            avatarUrl = uri.toString();
                            Log.i("foto", avatarUrl);
                            FirebaseFirestore.getInstance().collection("userDoador").document(uid).update("profileUrl", avatarUrl);
                            ref.delete();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("foto", e.getMessage());
                        }
                    });
                }
            });
        }

        //Atualiza campos
        if (!nome.isEmpty()) {
            FirebaseFirestore.getInstance().collection("userDoador").document(uid).update("username", nome);
        }
        if (!telefone.isEmpty()) {
            FirebaseFirestore.getInstance().collection("userDoador").document(uid).update("telefone", telefone);
        }
        if (!CEP.isEmpty()) {
            FirebaseFirestore.getInstance().collection("userDoador").document(uid).update("cep", CEP);
        }
        if (!endereco.isEmpty()) {
            FirebaseFirestore.getInstance().collection("userDoador").document(uid).update("endereco", endereco);
        }
        if(!senha.isEmpty()){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            user.updatePassword(senha)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("senha", "User password updated.");
                            }
                        }
                    });
        }
    }
}