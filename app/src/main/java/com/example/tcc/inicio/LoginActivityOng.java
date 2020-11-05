package com.example.tcc.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc.R;
import com.example.tcc.doador.DoadorMainActivity;
import com.example.tcc.ong.OngMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivityOng extends AppCompatActivity {

    private EditText mEmail;
    private EditText mSenha;
    private Button mBtnEnviarLoginOng;
    private Button mBtnNovoOng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity_login_ong);

        mEmail = findViewById(R.id.edtLogin_ong);
        mSenha = findViewById(R.id.edtPassword_ong);
        mBtnEnviarLoginOng = findViewById(R.id.btnLogin_ong);
        mBtnNovoOng = findViewById(R.id.btnNovoOng);

        mBtnEnviarLoginOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mSenha.getText().toString();

                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    Toast.makeText(LoginActivityOng.this, "Senha e Email devem ser preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.i("Teste", task.getResult().getUser().getUid());

                                Intent intent = new Intent(LoginActivityOng.this, OngMainActivity.class);

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

        mBtnNovoOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.tcc.inicio.LoginActivityOng.this , com.example.tcc.inicio.CadastroOngActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}