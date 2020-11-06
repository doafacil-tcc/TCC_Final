package com.example.tcc.doador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedDoacaoUnicaAdapter;
import com.example.tcc.Entities.FeedOngAdapter;
import com.example.tcc.R;
import com.example.tcc.ong.OngSelecaoDoacaoUnicaBrinquedo;
import com.example.tcc.ong.OngSelecaoDoacaoUnicaLivros;
import com.example.tcc.ong.OngSelecaoDoacaoUnicaMoveis;
import com.example.tcc.ong.OngSelecaoDoacaoUnicaRoupa;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class DoadorDoacaoUnicaFragment extends Fragment implements FeedDoacaoUnicaAdapter.OnListItemClick {

    private RecyclerView feedDoacoesDoarUnica;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    public static String id_Clicked_roupa_doacao;
    public static String id_Clicked_Brinquedo_doacao;
    public static String id_Clicked_Livros_doacao;
    public static String id_Clicked_Moveis_doacao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doador_fragment_doacao_unica, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesDoarUnica = view.findViewById(R.id.feedDoarUnica);

        Query query = mFirebaseFirestore.collection("Aguardando").whereEqualTo("origem", "ONG");

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FeedDoacaoUnicaAdapter(options,this);

        feedDoacoesDoarUnica.setHasFixedSize(true);
        feedDoacoesDoarUnica.setLayoutManager(new LinearLayoutManager(view.getContext()));
        feedDoacoesDoarUnica.setAdapter(adapter);
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
    public void onItemClickDoacao(Doacao snapshot, int position) {
        if(snapshot.getCategoria().equals("Roupas")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_roupa_doacao = snapshot.getId();
            Intent i = new Intent(getContext(), DoadorSelecaoDoacaoUnicaRoupa.class);
            startActivity(i);
        }

        if(snapshot.getCategoria().equals("Brinquedos")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_Brinquedo_doacao = snapshot.getId();
            Intent i = new Intent(getContext(), DoadorSelecaoDoacaoUnicaBrinquedo.class);
            startActivity(i);
        }

        if(snapshot.getCategoria().equals("Móveis")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_Moveis_doacao = snapshot.getId();
            Intent i = new Intent(getContext(), DoadorSelecaoDoacaoUnicaMoveis.class);
            startActivity(i);
        }

        if(snapshot.getCategoria().equals("Livros")) {

            Log.i("Item_Clicked", snapshot.getCategoria());
            id_Clicked_Livros_doacao = snapshot.getId();
            Intent i = new Intent(getContext(), DoadorSelecaoDoacaoUnicaLivros.class);
            startActivity(i);
        }
    }
}