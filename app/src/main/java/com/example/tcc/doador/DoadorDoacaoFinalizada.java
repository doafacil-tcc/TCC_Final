package com.example.tcc.doador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.tcc.R;
import com.example.tcc.inicio.EscolhaInicio;

public class DoadorDoacaoFinalizada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_doacao_finalizada);

        new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(DoadorDoacaoFinalizada.this, DoadorMainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}