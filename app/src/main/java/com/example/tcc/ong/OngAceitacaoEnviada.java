package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.tcc.R;

public class OngAceitacaoEnviada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_aceitacao_enviada);

        Toast.makeText(getApplicationContext(), "Obrigado!", Toast.LENGTH_SHORT).show();

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(OngAceitacaoEnviada.this, OngMainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();

    }
}