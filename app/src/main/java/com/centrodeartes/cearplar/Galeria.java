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
                , getResources().getString(R.string.vidriotit), getResources().getString(R.string.pirotit), getResources().getString(R.string.doradotit),
                getResources().getString(R.string.maderatit), getResources().getString(R.string.maderatit), getResources().getString(R.string.maderatext), getResources().getString(R.string.maderatit), getResources().getString(R.string.conchatit),
                getResources().getString(R.string.porcelanatit), getResources().getString(R.string.orfebreriatit), getResources().getString(R.string.pieltit), getResources().getString(R.string.tapiztit)};

        textos = new String[]{getResources().getString(R.string.dibujotext), getResources().getString(R.string.esculturatext), getResources().getString(R.string.marqtext)
                , getResources().getString(R.string.vidriotext), getResources().getString(R.string.pirotext), getResources().getString(R.string.doradotext),
                getResources().getString(R.string.maderatext), getResources().getString(R.string.maderatext), getResources().getString(R.string.conchatext), getResources().getString(R.string.porcelanatext),
                getResources().getString(R.string.orfebreriatext), getResources().getString(R.string.pieltext), getResources().getString(R.string.tapiztext)};

        titulo.setText(titulos[cont]);
        texto.setText(textos[cont]);


        arrayList.add(R.mipmap.dibujo7);
        arrayList.add(R.mipmap.dibujo2);
        arrayList.add(R.mipmap.dibujo3);
        arrayList.add(R.mipmap.dibujo4);
        arrayList.add(R.mipmap.dibujo5);
        arrayList.add(R.mipmap.dibujo1);
        arrayList.add(R.mipmap.dibujo6);
        arrayList.add(R.mipmap.dibujo8);

        arrayList.add(R.mipmap.escultura1);
        arrayList.add(R.mipmap.escul);
        arrayList.add(R.mipmap.escultura2);
        arrayList.add(R.mipmap.escultura3);
        arrayList.add(R.mipmap.escultura4);
        arrayList.add(R.mipmap.escultura5);


        arrayList.add(R.mipmap.marqueteria2);
        arrayList.add(R.mipmap.marqueteria1);
        arrayList.add(R.mipmap.marqueteria3);
        arrayList.add(R.mipmap.marqueteria4);
        arrayList.add(R.mipmap.marqueteria5);
        arrayList.add(R.mipmap.marqueteria6);
        arrayList.add(R.mipmap.marqueteria7);
        arrayList.add(R.mipmap.marqueteria8);
        arrayList.add(R.mipmap.marqueteria9);

        arrayList.add(R.mipmap.vidrio4);
        arrayList.add(R.mipmap.vidrio2);
        arrayList.add(R.mipmap.vidrio3);
        arrayList.add(R.mipmap.vidrio1);
        arrayList.add(R.mipmap.vidrio5);
        arrayList.add(R.mipmap.vidrio6);

        arrayList.add(R.mipmap.madera4);
        arrayList.add(R.mipmap.madera2);
        arrayList.add(R.mipmap.madera3);
        arrayList.add(R.mipmap.madera1);
        arrayList.add(R.mipmap.madera5);
        arrayList.add(R.mipmap.madera6);
        arrayList.add(R.mipmap.madera7);
        arrayList.add(R.mipmap.madera8);

        arrayList.add(R.mipmap.orfebreria6);
        arrayList.add(R.mipmap.orfebreria2);
        arrayList.add(R.mipmap.orfebreria3);
        arrayList.add(R.mipmap.orfebreria4);
        arrayList.add(R.mipmap.orfebreria5);
        arrayList.add(R.mipmap.orfebreria1);
        arrayList.add(R.mipmap.orfebreria7);
        arrayList.add(R.mipmap.orfebreria8);
        arrayList.add(R.mipmap.orfebreria9);
        arrayList.add(R.mipmap.orfebreria10);

        arrayList.add(R.mipmap.ceramica7);
        arrayList.add(R.mipmap.img1);
        arrayList.add(R.mipmap.ceramica3);
        arrayList.add(R.mipmap.ceramica4);
        arrayList.add(R.mipmap.ceramica5);
        arrayList.add(R.mipmap.ceramica6);
        arrayList.add(R.mipmap.ceramica2);
        arrayList.add(R.mipmap.ceramica8);
        arrayList.add(R.mipmap.ceramica9);


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