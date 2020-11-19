package com.example.tcc.doador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedCampanhaAdapter;
import com.example.tcc.Entities.SolicitarCampanha;
import com.example.tcc.R;
import com.example.tcc.ong.OngSelecaoDoacaoUnicaRoupa;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class DoadorDoacoesCampanhaFragment extends Fragment implements FeedCampanhaAdapter.OnListItemClick{

    private RecyclerView feedCampanha;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    public static String id_Clicked_campanha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doador_fragment_doacoes_campanha, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedCampanha = view.findViewById(R.id.feedCampanhas);

        feedCampanha.addItemDecoration(new DividerItemDecoration(feedCampanha.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Campanha");

        FirestoreRecyclerOptions<SolicitarCampanha> options = new FirestoreRecyclerOptions.Builder<SolicitarCampanha>()
                .setQuery(query, SolicitarCampanha.class)
                .build();

        adapter = new FeedCampanhaAdapter(options, this);

        feedCampanha.setHasFixedSize(true);
        feedCampanha.setLayoutManager(new LinearLayoutManager(view.getContext()));
        feedCampanha.setAdapter(adapter);
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
    public void onItemClickCampanha(SolicitarCampanha snapshot, int position) {

            Intent i = new Intent(getContext(), DoadorSelecaoCampanha.class);
            i.putExtra("id_campanha", snapshot.getId_campanha());
            startActivity(i);

    }
}

