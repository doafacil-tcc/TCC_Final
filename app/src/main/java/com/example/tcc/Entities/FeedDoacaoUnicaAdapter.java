package com.example.tcc.Entities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class FeedDoacaoUnicaAdapter extends FirestoreRecyclerAdapter<Doacao, FeedDoacaoUnicaAdapter.ItensViewHolderDoacao> {

    private FeedDoacaoUnicaAdapter.OnListItemClick onListItemClick;

    public FeedDoacaoUnicaAdapter(@NonNull FirestoreRecyclerOptions<Doacao> options, FeedDoacaoUnicaAdapter.OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItensViewHolderDoacao holder, int position, @NonNull Doacao model) {
        holder.tipo.setText(model.getTipo());
        holder.categoria.setText("Categoria: " + model.getCategoria());
        holder.quantidade.setText("Quantidade: " + model.getQtd());
        Picasso.get().load(model.getImgUrl1()).into(holder.imagem);
    }

    @NonNull
    @Override
    public ItensViewHolderDoacao onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ong_customfeedlayout, parent, false);
        return new FeedDoacaoUnicaAdapter.ItensViewHolderDoacao(view);
    }


    public class ItensViewHolderDoacao extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagem;
        private TextView tipo;
        private TextView categoria;
        private TextView quantidade;

        public ItensViewHolderDoacao(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.txtTipo);
            categoria = itemView.findViewById(R.id.txtCat);
            quantidade = itemView.findViewById(R.id.txtQuantidade);
            imagem = itemView.findViewById(R.id.imgDoacao);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClickDoacao(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnListItemClick {
        void onItemClickDoacao(Doacao snapshot, int position);
    }

}
