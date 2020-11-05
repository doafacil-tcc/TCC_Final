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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivityDoador extends AppCompatActivity {

    private EditText mEmail;
    private EditText mSenha;
    private Button mBtnEnviarLoginDoador;
    private Button mBtnNovoDoador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity_login_doador);

        mEmail = findViewById(R.id.edtLogin_doador);
        mSenha = findViewById(R.id.edtPassword_doador);
        mBtnEnviarLoginDoador = findViewById(R.id.btnLogin_doador);
        mBtnNovoDoador = findViewById(R.id.btnNovoDoador);

        mBtnEnviarLoginDoador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mSenha.getText().toString();

                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    Toast.makeText(LoginActivityDoador.this, "Senha e Email devem ser preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                Log.i("Teste", task.getResult().getUser().getUid());
                                if(task.isSuccessful()) {

                                    Intent intent = new Intent(LoginActivityDoador.this, DoadorMainActivity.class);

                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(LoginActivityDoador.this, "LOGIN OU SENHA INCORRETOS", Toast.LENGTH_SHORT).show();
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
        });

        mBtnNovoDoador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.tcc.inicio.LoginActivityDoador.this , com.example.tcc.inicio.CadastroDoadorActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}