package com.example.tcc.ong;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tcc.R;
import com.example.tcc.Entities.MensagensActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class OngMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ong_activity_main);

        toolbar = findViewById(R.id.toolbarOng);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigationViewOng);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.feedDoacaoOng, new OngFeedDoacaoFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.sair) {

            FirebaseAuth.getInstance().signOut();

            Intent i = new Intent(com.example.tcc.ong.OngMainActivity.this, com.example.tcc.inicio.EscolhaInicio.class);
            startActivity(i);
            finish();
        }

        if(item.getItemId() == R.id.meus_dados_ong) {
            Intent i = new Intent(com.example.tcc.ong.OngMainActivity.this, com.example.tcc.ong.OngMeusDados.class);
            startActivity(i);
        }

        if (item.getItemId() == R.id.solicitar_doacao){

            Intent i = new Intent(com.example.tcc.ong.OngMainActivity.this, com.example.tcc.ong.OngEscolhaTipoDoacao.class);
            startActivity(i);

        }
        if (item.getItemId() == R.id.mensagens){

            Intent i = new Intent(com.example.tcc.ong.OngMainActivity.this, MensagensActivity.class);
//            i.putExtra("id_outro", "SJ972B8IlKOPIUzTei2ANI0xxsu2");
//            i.putExtra("nome_ong", "Teste Doador");
//            i.putExtra("foto_avatar", "https://firebasestorage.googleapis.com/v0/b/tcc-doafacil.appspot.com/o/AvatarDoador%2F5907028e-85dc-464e-bc90-e4b97fba7425?alt=media&token=f2cf055d-7407-4306-9623-27c82d646cf9");
            startActivity(i);

        }
        return true;
    }
}
