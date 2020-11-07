package com.example.tcc.ong;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tcc.Entities.FeedOngAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class OngFeedDoacaoFragment extends Fragment implements FeedOngAdapter.OnListItemClick {

    private RecyclerView feedDoacoesOng;
    private FirebaseFirestore mFirebaseFirestore;
    private FeedOngAdapter adapter;
    public static String id_Clicked_roupa;
    public static String id_Clicked_Brinquedo;
    public static String id_Clicked_Livros;
    public static String id_Clicked_Moveis;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ong_fragment_feed_doacao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesOng = view.findViewById(R.id.feedOng);

        feedDoacoesOng.addItemDecoration(new DividerItemDecoration(feedDoacoesOng.getContext(), DividerItemDecoration.VERTICAL));

        Query query = mFirebaseFirestore.collection("Aguardando").whereEqualTo("origem", "Doador");

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedOngAdapter(options,this);

        Log.i("xx", view.getContext().toString());
        feedDoacoesOng.setHasFixedSize(true);
        feedDoacoesOng.setLayoutManager(new LinearLayoutManager(view.getContext()));
        feedDoacoesOng.setAdapter(adapter);

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
    public void onItemClick(Doacao snapshot, int p) {

        if(snapshot.getCategoria().equals("Roupa")) {

                Log.i("Item_Clicked", snapshot.getCategoria());
                id_Clicked_roupa = snapshot.getId();
            Intent i = new Intent(getContext(), OngSelecaoDoacaoUnicaRoupa.class);
            startActivity(i);
        }
// vish maria
        if(snapshot.getCategoria().equals("Brinquedo")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_Brinquedo = snapshot.getId();
            Intent i = new Intent(getContext(), OngSelecaoDoacaoUnicaBrinquedo.class);
            startActivity(i);
        }

        if(snapshot.getCategoria().equals("Moveis")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_Moveis = snapshot.getId();
            Intent i = new Intent(getContext(), OngSelecaoDoacaoUnicaMoveis.class);
            startActivity(i);
        }

        if(snapshot.getCategoria().equals("Livro")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_Livros = snapshot.getId();
            Intent i = new Intent(getContext(), OngSelecaoDoacaoUnicaLivros.class);
            startActivity(i);
        }

    }
}
