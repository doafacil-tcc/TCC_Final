package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tcc.R;
import com.example.tcc.doador.DoadorDoacaoFinalizada;

public class OngEscolhaTipoDoacao extends AppCompatActivity {

    Button btnCamapnha, btnUnica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_escolha_tipo_doacao);

        btnCamapnha = findViewById(R.id.btnCampanhaArecadacaoOng);
        btnUnica = findViewById(R.id.btnDoacaoEspecificaOng);

        btnCamapnha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OngEscolhaTipoDoacao.this, OngSolicitarCampanha.class);
                startActivity(intent);
            }
        });

        btnUnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OngEscolhaTipoDoacao.this, OngSolicitarDoacaoUnica.class);
                startActivity(intent);
            }
        });
    }
}