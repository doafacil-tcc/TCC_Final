package com.example.tcc.Entities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FeedCampanhaAbertaAdapter extends FirestoreRecyclerAdapter<Doacao, FeedCampanhaAbertaAdapter.ItensViewHolderCampanha> {

    private FeedCampanhaAbertaAdapter.OnListItemClick onListItemClick;

    public FeedCampanhaAbertaAdapter(@NonNull FirestoreRecyclerOptions<Doacao> options, FeedCampanhaAbertaAdapter.OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }


    @NonNull
    @Override
    public ItensViewHolderCampanha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ong_campanhaaberta_layout, parent, false);
        return new FeedCampanhaAbertaAdapter.ItensViewHolderCampanha(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItensViewHolderCampanha holder, int position, @NonNull Doacao model) {
        holder.nome.setText(model.getDescricao());
        holder.quantidade.setText("Qtd: " + model.getQtd());
    }

    public class ItensViewHolderCampanha extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nome;
        private TextView quantidade;

        public ItensViewHolderCampanha(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtNomePessoa);
            quantidade = itemView.findViewById(R.id.txtQtd);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClickCampanhaAberta(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }


    public interface OnListItemClick {
        void onItemClickCampanhaAberta(Doacao snapshot, int position);
    }

}
