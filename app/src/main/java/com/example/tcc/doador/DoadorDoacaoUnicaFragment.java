package com.example.tcc.doador;

import android.net.Uri;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.R;
import com.example.tcc.ong.OngFeedDoacaoFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


public class DoadorDoacaoUnicaFragment extends Fragment {

    private RecyclerView feedDoacoesDoarUnica;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

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

        adapter = new FirestoreRecyclerAdapter< Doacao, ItensViewHolderDoar>(options) {

            @NonNull
            @Override
            public ItensViewHolderDoar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doador_customfeedlayout, parent, false);
                return new DoadorDoacaoUnicaFragment.ItensViewHolderDoar(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItensViewHolderDoar holder, int position, @NonNull Doacao model) {
                holder.tipox.setText(model.getTipo());
                holder.categoriax.setText("Categoria: " + model.getCategoria());
                holder.quantidadex.setText("Quantidade: " + model.getQtd());
                Picasso.get().load(model.getImgUrl1()).into(holder.imagemx);
            }
        };

        feedDoacoesDoarUnica.setHasFixedSize(true);
        feedDoacoesDoarUnica.setLayoutManager(new LinearLayoutManager(view.getContext()));
        feedDoacoesDoarUnica.setAdapter(adapter);
    }

    private class ItensViewHolderDoar extends RecyclerView.ViewHolder {
        private ImageView imagemx;
        private TextView tipox;
        private TextView categoriax;
        private TextView quantidadex;

        public ItensViewHolderDoar(@NonNull View itemView) {
            super(itemView);

            tipox = itemView.findViewById(R.id.txtTipoDoacao);
            categoriax = itemView.findViewById(R.id.txtCategoriaDoacao);
            quantidadex = itemView.findViewById(R.id.txtQuantidadeDoacao);
            imagemx = itemView.findViewById(R.id.imgDoacao);
        }
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


}