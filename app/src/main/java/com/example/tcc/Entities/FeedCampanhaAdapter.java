package com.example.tcc.Entities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.R;
import com.example.tcc.doador.DoadorDoacoesCampanhaFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class FeedCampanhaAdapter extends FirestoreRecyclerAdapter<SolicitarCampanha, FeedCampanhaAdapter.ItensViewHolderCampanha> {

    private FeedCampanhaAdapter.OnListItemClick onListItemClick;

    public FeedCampanhaAdapter(@NonNull FirestoreRecyclerOptions<SolicitarCampanha> options, FeedCampanhaAdapter.OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItensViewHolderCampanha holder, int position, @NonNull SolicitarCampanha model) {
        holder.titulox.setText(model.getTitulo());
        holder.categoriax.setText("Categoria: " + model.getCategoria());
        holder.breve_descx.setText(model.getDescricao_breve());
        Picasso.get().load(model.getImgUrl1_campanha()).into(holder.imagemx);
    }

    @NonNull
    @Override
    public ItensViewHolderCampanha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doador_customfeedlayout, parent, false);
        return new FeedCampanhaAdapter.ItensViewHolderCampanha(view);
    }

    public class ItensViewHolderCampanha extends RecyclerView.ViewHolder implements View.OnClickListener{
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClickCampanha(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnListItemClick {
        void onItemClickCampanha(SolicitarCampanha snapshot, int position);
    }
}
