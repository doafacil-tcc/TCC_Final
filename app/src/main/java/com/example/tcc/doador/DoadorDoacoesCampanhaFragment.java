package com.example.tcc.doador;

import android.os.Bundle;
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
import com.example.tcc.Entities.SolicitarCampanha;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


public class DoadorDoacoesCampanhaFragment extends Fragment {

    private RecyclerView feedCampanha;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

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

        Query query = mFirebaseFirestore.collection("Campanha");

        FirestoreRecyclerOptions<SolicitarCampanha> options = new FirestoreRecyclerOptions.Builder<SolicitarCampanha>()
                .setQuery(query, SolicitarCampanha.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<SolicitarCampanha, ItensViewHolderCampanha>(options) {

            @NonNull
            @Override
            public ItensViewHolderCampanha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doador_customfeedlayout, parent, false);
                return new DoadorDoacoesCampanhaFragment.ItensViewHolderCampanha(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItensViewHolderCampanha holder, int position, @NonNull SolicitarCampanha model) {
                holder.titulox.setText(model.getTitulo());
                holder.categoriax.setText("Categoria: " + model.getCategoria());
                holder.breve_descx.setText(model.getDescricao_breve());
                Picasso.get().load(model.getImgUrl1_campanha()).into(holder.imagemx);
            }
        };

        feedCampanha.setHasFixedSize(true);
        feedCampanha.setLayoutManager(new LinearLayoutManager(view.getContext()));
        feedCampanha.setAdapter(adapter);
    }

    private class ItensViewHolderCampanha extends RecyclerView.ViewHolder {
        private ImageView imagemx;
        private TextView titulox;
        private TextView categoriax;
        private TextView breve_descx;

        public ItensViewHolderCampanha(@NonNull View itemView) {
            super(itemView);
            titulox = itemView.findViewById(R.id.txtTipoDoacao);
            categoriax = itemView.findViewById(R.id.txtCategoriaDoacao);
            breve_descx = itemView.findViewById(R.id.txtQuantidadeDoacao);
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

