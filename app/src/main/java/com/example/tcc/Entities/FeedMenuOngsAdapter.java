package com.example.tcc.Entities;

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
import com.squareup.picasso.Picasso;

public class FeedMenuOngsAdapter extends FirestoreRecyclerAdapter<User, FeedMenuOngsAdapter.ItensViewHolderMenuOng> {

    private FeedMenuOngsAdapter.OnListItemClick onListItemClick;

    public FeedMenuOngsAdapter(@NonNull FirestoreRecyclerOptions<User> options, FeedMenuOngsAdapter.OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @NonNull
    @Override
    public ItensViewHolderMenuOng onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doador_customongs, parent,false);
        return new ItensViewHolderMenuOng(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItensViewHolderMenuOng holder, int position, @NonNull User model) {
        holder.nome.setText(model.getUsername());
        holder.site.setText(model.getSite());
        Picasso.get().load(model.getProfileUrl()).into(holder.imagem);
    }

    public class ItensViewHolderMenuOng extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagem;
        private TextView nome;
        private TextView site;


        public ItensViewHolderMenuOng(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.txtNomeOng);
            site = itemView.findViewById(R.id.txtSiteOng);
            imagem = itemView.findViewById(R.id.imgOngMenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnListItemClick {
        void onItemClick(User snapshot, int position);
    }
}
