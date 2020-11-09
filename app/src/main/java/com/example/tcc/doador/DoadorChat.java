package com.example.tcc.doador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tcc.R;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class DoadorChat extends AppCompatActivity {

    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_chat);

        RecyclerView rv = findViewById(R.id.recycler_chat_doador);

        adapter = new GroupAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.add(new MessageItem((true)));
        adapter.add(new MessageItem((false)));
        adapter.add(new MessageItem((true)));
        adapter.add(new MessageItem((true)));
        adapter.add(new MessageItem((false)));
        adapter.add(new MessageItem((true)));
    }

    private class MessageItem extends Item<GroupieViewHolder> {

        private final boolean isLeft;

        private MessageItem(boolean isLeft) {
            this.isLeft = isLeft;
        }

        @Override
        public void bind(@NonNull GroupieViewHolder viewHolder, int position) {

        }

        @Override
        public int getLayout() {
            return isLeft
                    ? R.layout.mensagem_origem_layout
                    : R.layout.mensagem_destino_layout;
        }
    }
}