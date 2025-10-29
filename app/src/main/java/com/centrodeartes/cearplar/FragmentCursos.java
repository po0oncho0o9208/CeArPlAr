package com.centrodeartes.cearplar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class FragmentCursos extends Fragment implements View.OnClickListener {
    LinearLayout btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cursos, container, false);
        btn1 = view.findViewById(R.id.btncurso1);
        btn1.setOnClickListener(this);
        btn2 = view.findViewById(R.id.btncurso2);
        btn2.setOnClickListener(this);
        btn3 = view.findViewById(R.id.btncurso3);
        btn3.setOnClickListener(this);
        btn4 = view.findViewById(R.id.btncurso4);
        btn4.setOnClickListener(this);
        btn5 = view.findViewById(R.id.btncurso5);
        btn5.setOnClickListener(this);
        btn6 = view.findViewById(R.id.btncurso6);
        btn6.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.btnanim);
        int cont = 0;

        if (v.getId() == R.id.btncurso1) {
            cont = 0;
            btn1.startAnimation(anim);

        } else if (v.getId() == R.id.btncurso2) {
            cont = 1;
            btn2.startAnimation(anim);

        } else if (v.getId() == R.id.btncurso3) {
            cont = 2;
            btn3.startAnimation(anim);

        } else if (v.getId() == R.id.btncurso4) {
            cont = 3;
            btn4.startAnimation(anim);

        } else if (v.getId() == R.id.btncurso5) {
            cont = 4;
            btn5.startAnimation(anim);

        } else if (v.getId() == R.id.btncurso6) {
            cont = 5;
            btn6.startAnimation(anim);
        }

        Intent intent = new Intent(getContext(), Galeria.class);
        intent.putExtra("id", cont);
        startActivity(intent);
    }
}