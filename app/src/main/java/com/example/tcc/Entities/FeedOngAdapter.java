package com.example.tcc.Entities;

import android.net.Uri;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

public class FeedOngAdapter extends FirestoreRecyclerAdapter<Doacao, FeedOngAdapter.ItensViewHolder> {

    private OnListItemClick onListItemClick;

    public FeedOngAdapter(@NonNull FirestoreRecyclerOptions<Doacao> options, OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItensViewHolder holder, int position, @NonNull Doacao model) {
        holder.tipo.setText(model.getTipo());
        holder.categoria.setText("Categoria: " + model.getCategoria());
        holder.quantidade.setText("Quantidade: " + model.getQtd());
        Picasso.get().load(model.getImgUrl1()).into(holder.imagem);
        Log.i("parsa", Uri.parse(model.getImgUrl1()).toString());
    }

    @NonNull
    @Override
    public ItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ong_customfeedlayout, parent, false);
        return new ItensViewHolder(view);
    }

    public class ItensViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagem;
        private TextView tipo;
        private TextView categoria;
        private TextView quantidade;

        public ItensViewHolder(@NonNull View itemView) {
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
