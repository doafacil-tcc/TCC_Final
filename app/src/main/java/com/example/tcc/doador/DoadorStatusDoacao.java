package com.example.tcc.doador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tcc.Entities.ChatActivity;
import com.example.tcc.R;

public class DoadorStatusDoacao extends AppCompatActivity {

    Button btn1, btn2, btn3;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_status_doacao);

        btn1 = findViewById(R.id.btnStatus1);
        btn2 = findViewById(R.id.btnStatus2);
        btn3 = findViewById(R.id.btnStatus3);
        toolbar = findViewById(R.id.toolbar_statusDoacao);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoadorStatusDoacao.this, DoadorStatusDoacaoEsperando.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoadorStatusDoacao.this, DoadorStatusDoacaoTransito.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoadorStatusDoacao.this, DoadorStatusDoacaoFinalizada.class);
                startActivity(i);
            }
        });

    }
}