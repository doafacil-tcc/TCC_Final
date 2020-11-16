package com.example.tcc.ong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tcc.R;

public class OngStatusCampanha extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_status_campanha);

        btn1 = findViewById(R.id.btnStatus1);
        btn2 = findViewById(R.id.btnStatus2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OngStatusCampanha.this, OngStatusCampanhaAberta.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OngStatusCampanha.this, OngStatusDoacaoTransito.class);
                startActivity(i);
            }
        });
    }
}