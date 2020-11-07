package com.example.tcc.doador;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedDoacaoUnicaAdapter;
import com.example.tcc.Entities.FeedMenuOngsAdapter;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DoadorMenuOngs extends AppCompatActivity implements FeedMenuOngsAdapter.OnListItemClick{

    private RecyclerView feedMenuOngs;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_menu_ongs);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedMenuOngs = findViewById(R.id.menuFeedOngs);

        feedMenuOngs.addItemDecoration(new DividerItemDecoration(feedMenuOngs.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("userONG");

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new FeedMenuOngsAdapter(options,this);

        feedMenuOngs.setHasFixedSize(true);
        feedMenuOngs.setLayoutManager(new LinearLayoutManager(this));
        feedMenuOngs.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onItemClick(User snapshot, int position) {
        Log.i("Menu_ONG", "Item Clicked");
    }
}

