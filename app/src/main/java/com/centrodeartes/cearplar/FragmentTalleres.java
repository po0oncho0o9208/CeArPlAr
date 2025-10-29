package com.centrodeartes.cearplar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class FragmentTalleres extends Fragment implements View.OnClickListener {
    LinearLayout btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_talleres, container, false);
        btn1 = view.findViewById(R.id.btntaller1);
        btn1.setOnClickListener(this);
        btn2 = view.findViewById(R.id.btntaller2);
        btn2.setOnClickListener(this);
        btn3 = view.findViewById(R.id.btntaller3);
        btn3.setOnClickListener(this);
        btn4 = view.findViewById(R.id.btntaller4);
        btn4.setOnClickListener(this);
        btn5 = view.findViewById(R.id.btntaller5);
        btn5.setOnClickListener(this);
        btn6 = view.findViewById(R.id.btntaller6);
        btn6.setOnClickListener(this);
        btn7 = view.findViewById(R.id.btntaller7);
        btn7.setOnClickListener(this);
        btn8 = view.findViewById(R.id.btntaller8);
        btn8.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        int cont = 0;

        if (v.getId() == R.id.btntaller1) {
            cont = 6;
        } else if (v.getId() == R.id.btntaller2) {
            cont = 7;
        } else if (v.getId() == R.id.btntaller3) {
            cont = 8;
        } else if (v.getId() == R.id.btntaller4) {
            cont = 9;
        } else if (v.getId() == R.id.btntaller5) {
            cont = 10;
        } else if (v.getId() == R.id.btntaller6) {
            cont = 11;
        } else if (v.getId() == R.id.btntaller7) {
            cont = 12;
        } else if (v.getId() == R.id.btntaller8) {
            cont = 13;
        }

        Intent intent = new Intent(getContext(), Galeria.class);
        intent.putExtra("id", cont);
        startActivity(intent);
    }
}