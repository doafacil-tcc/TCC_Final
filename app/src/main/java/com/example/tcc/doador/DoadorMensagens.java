package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcc.Entities.ClickContato;
import com.example.tcc.Entities.Contact;
import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.FeedOngAdapter;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;

import java.util.List;

public class DoadorMensagens extends AppCompatActivity {

    GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_mensagens);

        Toolbar toolbar = findViewById(R.id.toolbar_mensagens_Doador);
        toolbar.setTitle("Mensagens");

        final RecyclerView rv = findViewById(R.id.recycler_contact);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GroupAdapter();
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {

            }
        });

        fetchLastMessage();


    }

    private void fetchLastMessage() {
        String uid = FirebaseAuth.getInstance().getUid();

        FirebaseFirestore.getInstance().collection("/last-messages")
                .document(uid)
                .collection("contatos")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<DocumentChange> documentChanges = value.getDocumentChanges();

                        if (documentChanges != null){
                            for (DocumentChange doc : documentChanges) {
                                if (doc.getType() == DocumentChange.Type.ADDED){
                                    Contact contact = doc.getDocument().toObject(Contact.class);

                                    adapter.add(new ContactItem(contact));

                                }
                            }
                        }
                    }
                });
    }



    private class ContactItem extends Item<GroupieViewHolder> {

        private final Contact contact;

        private ContactItem(Contact contact) {
            this.contact = contact;
        }

        @Override
        public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
            TextView username = viewHolder.itemView.findViewById(R.id.nome_last_one);
            TextView message = viewHolder.itemView.findViewById(R.id.msg_last_one);
            ImageView imgPhoto = viewHolder.itemView.findViewById(R.id.img_last_Msg);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(DoadorMensagens.this, DoadorChat.class);
                    i.putExtra("id_outro", contact.getUuid());
                    i.putExtra("nome_outro", contact.getUsername());
                    i.putExtra("foto_outro", contact.getPhotoUrl());

                    startActivity(i);
                }
            });

            username.setText(contact.getUsername());
            message.setText(contact.getLastMessage());
            Picasso.get().load(contact.getPhotoUrl()).into(imgPhoto);

        }

        @Override
        public int getLayout() {
            return R.layout.mensagem_last_one_layout;
        }

    }


}