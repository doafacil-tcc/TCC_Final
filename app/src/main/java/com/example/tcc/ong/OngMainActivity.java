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
        return true;
    }
}
