package com.example.tcc.doador;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc.R;

public class DoadorMenuOngs extends AppCompatActivity {

    ListView mListView;

    int [] images = {R.drawable.ic_baseline_home_24,
                     R.drawable.ic_baseline_chat_24,
                     R.drawable.ic_baseline_info_24,
                     R.drawable.ic_baseline_history_24,
                     R.drawable.ic_launcher_foreground};

    String [] Names = {"ONG1", "ONG2", "ONG3", "ONG4", "ONG5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doador_activity_menu_ongs);

        mListView = (ListView) findViewById(R.id.feedMenuOngs);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
    }

    class CustomAdaptor extends BaseAdapter{


        @Override
        public int getCount() {

            return images.length;
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.doador_custommenuongslayout, null);

            ImageView mImageView = (ImageView) view.findViewById(R.id.imgAvataMeusDadosDoador);
            TextView mTextView = (TextView) view.findViewById(R.id.textView);

            mImageView.setImageResource(images[position]);

            mTextView.setText(Names[position]);

            return view;
        }
    }
}

