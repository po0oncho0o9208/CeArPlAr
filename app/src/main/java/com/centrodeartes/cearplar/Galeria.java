package com.centrodeartes.cearplar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Objects;

public class Galeria extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Integer> arrayList = new ArrayList<>();
    Button btn;
    ViewPager viewpager, viewpagerfull;
    SliderAdapter adapter;
    private AdView mAdView;
    int cont;
    TextView titulo, texto;
    String[] titulos, textos;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        titulo = findViewById(R.id.txvtit);
        texto = findViewById(R.id.txvtxt);
        Intent intent = getIntent();
        cont = intent.getIntExtra("id", 0);
        layout = findViewById(R.id.layoutgaleria);
        layout.getBackground().setAlpha(215);

        titulos = new String[]{getResources().getString(R.string.dibujotit), getResources().getString(R.string.esculturatit), getResources().getString(R.string.marqtit)
                , getResources().getString(R.string.vidriotit), getResources().getString(R.string.pirotit), getResources().getString(R.string.doradotit), getResources().getString(R.string.maderatit)};
        textos = new String[]{getResources().getString(R.string.dibujotext), getResources().getString(R.string.esculturatext), getResources().getString(R.string.marqtext)
                , getResources().getString(R.string.vidriotext), getResources().getString(R.string.pirotext), getResources().getString(R.string.doradotext), getResources().getString(R.string.maderatext)};

        titulo.setText(titulos[cont]);
        texto.setText(textos[cont]);

        arrayList.add(R.mipmap.img1);
        arrayList.add(R.mipmap.img2);
        arrayList.add(R.mipmap.img3);
        arrayList.add(R.mipmap.img4);
        arrayList.add(R.mipmap.img5);
        btn = findViewById(R.id.btncloseimage);
        btn.setOnClickListener(this);
        viewpager = findViewById(R.id.viewpager);
        viewpagerfull = findViewById(R.id.viewpagerfull);
        adapter = new SliderAdapter(this, arrayList, btn, viewpagerfull, layout);
        viewpager.setAdapter(adapter);
        viewpagerfull.setAdapter(adapter);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onClick(View v) {
        layout.setVisibility(View.GONE);
        viewpagerfull.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        viewpager.setCurrentItem(viewpagerfull.getCurrentItem());
        adapter.isfull = false;
    }

    //habilitar boton hacia atras
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}