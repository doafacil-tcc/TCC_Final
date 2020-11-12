package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tcc.R;
import com.example.tcc.doador.DoadorStatusDoacao;
import com.example.tcc.doador.DoadorStatusDoacaoEsperando;
import com.example.tcc.doador.DoadorStatusDoacaoFinalizada;
import com.example.tcc.doador.DoadorStatusDoacaoTransito;

public class OngStatusDoacao extends AppCompatActivity {

    Button btn1, btn2, btn3;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_doacao);
        btn1 = findViewById(R.id.btnStatus1);
        btn2 = findViewById(R.id.btnStatus2);
        btn3 = findViewById(R.id.btnStatus3);
        toolbar = findViewById(R.id.toolbar_statusOng);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OngStatusDoacao.this, OngStatusDoacaoEsperando.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OngStatusDoacao.this, OngStatusDoacaoTransito.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OngStatusDoacao.this, OngStatusDoacaoFinalizadas.class);
                startActivity(i);
            }
        });

    }
}