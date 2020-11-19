package com.example.tcc.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcc.Entities.Contact;
import com.example.tcc.Entities.Message;
import com.example.tcc.Entities.User;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private String mAvatar, mIdOutro, mNomeOutro;
    private EditText editText;
    private GroupAdapter adapter;
    private User me;
    private LinearLayoutManager linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_chat);

        RecyclerView rv = findViewById(R.id.recycler_chat_doador);
        Toolbar toolbar = findViewById(R.id.toolbar_chat_doador);
        editText = findViewById(R.id.edit_chat_doador);
        Button btnChatDoador = findViewById(R.id.btn_chat_doador);

        btnChatDoador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        mIdOutro =  getIntent().getStringExtra("id_outro");
        mNomeOutro =  getIntent().getStringExtra("nome_outro");
        mAvatar =  getIntent().getStringExtra("foto_outro");

        toolbar.setTitle(mNomeOutro);

        adapter = new GroupAdapter();
        linearLayout = new  LinearLayoutManager(this);

        rv.setLayoutManager(linearLayout);

        rv.setAdapter(adapter);

        String uid = FirebaseAuth.getInstance().getUid();
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("userDoador").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Map<String, Object> x = document.getData();

                        String  cpf = x.get("cpf").toString();

                        Log.i("xxxxxcpf", cpf);

                        if(!cpf.isEmpty()){
                            FirebaseFirestore.getInstance().collection("/userDoador")
                                    .document(FirebaseAuth.getInstance().getUid())
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot snapshot) {
                                            me = snapshot.toObject(User.class);
                                            fetchMessage();
                                            Log.i("xxxxxdoador", "oi");

                                        }});
                        }
                    }
                }
            }
        });

        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection("userONG").document(uid);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Map<String, Object> x = document.getData();

                        String  cnpj = x.get("cnpj").toString();


                        if(!cnpj.isEmpty()){
                            FirebaseFirestore.getInstance().collection("/userONG")
                                    .document(FirebaseAuth.getInstance().getUid())
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot snapshot) {
                                            me = snapshot.toObject(User.class);
                                            fetchMessage();
                                            Log.i("xxxxxdoador", "oi");

                                        }});
                        }
                    }
                }
            }
        });



    }

    private void fetchMessage() {
        if (me != null){

            String fromId = me.getUuid();
            String toId = mIdOutro;

            FirebaseFirestore.getInstance().collection("/conversations")
                    .document(fromId)
                    .collection(toId)
                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            List<DocumentChange> documentChanges = value.getDocumentChanges();

                            if (documentChanges != null ){
                                for (DocumentChange doc : documentChanges) {
                                    if (doc.getType() == DocumentChange.Type.ADDED){
                                        Message message = doc.getDocument().toObject(Message.class);
                                        adapter.add(new MessageItem(message));
                                    }
                                }
                            }
                        }
                    });
        }
    }


    private class MessageItem extends Item<GroupieViewHolder> {

        private final Message message;

        private MessageItem(Message message) {
            this.message = message;
        }


        @Override
        public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
            TextView txtMsg = viewHolder.itemView.findViewById(R.id.txt_message);
            ImageView imgMessage = viewHolder.itemView.findViewById(R.id.img_message_user_origem);

            txtMsg.setText(message.getText());
            Picasso.get().load(message.getFromId().equals(FirebaseAuth.getInstance().getUid())
                    ? me.getProfileUrl()
                    : mAvatar)
                    .into(imgMessage);

        }

        @Override
        public int getLayout() {
            return message.getFromId().equals(FirebaseAuth.getInstance().getUid())
                    ? R.layout.mensagem_origem_layout
                    : R.layout.mensagem_destino_layout;
        }
    }

    private void sendMessage() {
            String text = editText.getText().toString();

            editText.setText(null);

            final String fromId = FirebaseAuth.getInstance().getUid();
            final String toId = mIdOutro;
            final String nome = mNomeOutro;
            long timestamp = System.currentTimeMillis();

        final Message message = new Message();
        message.setFromId(fromId);
        message.setToId(toId);
        message.setText(text);
        message.setTimestamp(timestamp);

        if (!message.getText().isEmpty()){
            FirebaseFirestore.getInstance().collection("/conversations")
                    .document(fromId)
                    .collection(toId)
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.i("chatvaivai", documentReference.getId());

                            Contact contact = new Contact();
                            contact.setUuid(toId);
                            contact.setUsername(mNomeOutro);
                            contact.setPhotoUrl(mAvatar);
                            contact.setTimestamp(message.getTimestamp());
                            contact.setLastMessage(message.getText());

                            FirebaseFirestore.getInstance().collection("/last-messages")
                                    .document(fromId)
                                    .collection("contatos")
                                    .document(toId)
                                    .set(contact);
                        }
                    });

            FirebaseFirestore.getInstance().collection("/conversations")
                    .document(toId)
                    .collection(fromId)
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.i("chatvaivai", documentReference.getId());

                            Contact contact = new Contact();
                            contact.setUuid(fromId);
                            contact.setUsername(me.getUsername());
                            contact.setPhotoUrl(me.getProfileUrl());
                            contact.setTimestamp(message.getTimestamp());
                            contact.setLastMessage(message.getText());

                            FirebaseFirestore.getInstance().collection("/last-messages")
                                    .document(toId)
                                    .collection("contatos")
                                    .document(fromId)
                                    .set(contact);
                        }
                    });
        }

    }
}