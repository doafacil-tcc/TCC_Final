package com.example.tcc.doador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tcc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DoadorNewDoacoesFragment extends Fragment{

    NavController navController = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doador_fragment_new_doacoes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        ImageButton roupa = view.findViewById(R.id.imabtnRoupa);
        ImageButton livro = view.findViewById(R.id.imabtnLivros);
        ImageButton brinquedo = view.findViewById(R.id.imabtnToy);
        ImageButton movel = view.findViewById(R.id.imabtnMoveis);
        BottomNavigationView bottomNav = view.findViewById(R.id.nav_bottom);


        roupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_newDoacoesFragment_to_newRoupaFragment);
            }
        });

        livro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_newDoacoesFragment_to_newLivrosFragment);
            }
        });

        brinquedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_newDoacoesFragment_to_newBrinquedoFragment);
            }
        });

        movel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_newDoacoesFragment_to_newMoveisFragment);
            }
        });


    }

}

