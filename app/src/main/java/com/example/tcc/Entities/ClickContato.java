package com.example.tcc.Entities;

import android.view.View;

public class ClickContato {

    private ClickContato.OnListItemClick onListItemClick;

    public interface OnListItemClick {
        void onItemClick(User user);
    }
}
