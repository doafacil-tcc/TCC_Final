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

public class FeedOngAdapter extends FirestoreRecyclerAdapter<Doacao, FeedOngAdapter.ItensViewHolderOng> {

    private FeedOngAdapter.OnListItemClick onListItemClick;

    public FeedOngAdapter(@NonNull FirestoreRecyclerOptions<Doacao> options, FeedOngAdapter.OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @NonNull
    @Override
    public ItensViewHolderOng onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ong_customfeedlayout, parent, false);
        return new FeedOngAdapter.ItensViewHolderOng(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItensViewHolderOng holder, int position, @NonNull Doacao model) {
        holder.tipo.setText(model.getTipo());
        holder.categoria.setText("Categoria: " + model.getCategoria());
        holder.quantidade.setText("Quantidade: " + model.getQtd());
        Picasso.get().load(model.getImgUrl1()).into(holder.imagem);
    }

    public class ItensViewHolderOng extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagem;
        private TextView tipo;
        private TextView categoria;
        private TextView quantidade;

        public ItensViewHolderOng(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.txtTipo);
            categoria = itemView.findViewById(R.id.txtCat);
            quantidade = itemView.findViewById(R.id.txtQuantidade);
            imagem = itemView.findViewById(R.id.imgDoacao);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnListItemClick {
        void onItemClick(Doacao snapshot, int position);
    }

}
