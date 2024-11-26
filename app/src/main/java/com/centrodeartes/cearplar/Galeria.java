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
    TextView texto1, texto2;
    String[] titulos, textos1, textos2;
    LinearLayout layout;
    Button btnpresent, btntem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_galeria);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        texto1 = findViewById(R.id.txvtxt1);
        texto2 = findViewById(R.id.txvtxt2);
        btnpresent = findViewById(R.id.btnpresentacion);
        btntem = findViewById(R.id.btntemario);
        Intent intent = getIntent();
        cont = intent.getIntExtra("id", 0);
        layout = findViewById(R.id.layoutgaleria);
        layout.getBackground().setAlpha(215);
        titulos = new String[]{getResources().getString(R.string.dibujotit), getResources().getString(R.string.esculturatit), getResources().getString(R.string.marqtit)
                , getResources().getString(R.string.vidriotit), getResources().getString(R.string.pirotit), getResources().getString(R.string.doradotit),
                getResources().getString(R.string.maderatit), getResources().getString(R.string.maderatit), getResources().getString(R.string.maderatit), getResources().getString(R.string.maderatit), getResources().getString(R.string.conchatit),
                getResources().getString(R.string.porcelanatit), getResources().getString(R.string.orfebreriatit), getResources().getString(R.string.pieltit), getResources().getString(R.string.tapiztit)};

        textos1 = new String[]{getResources().getString(R.string.dibujotext1), getResources().getString(R.string.esculturatext1), getResources().getString(R.string.marqtext1)
                , getResources().getString(R.string.vidriotext1), getResources().getString(R.string.pirotext1), getResources().getString(R.string.doradotext1),
                getResources().getString(R.string.maderatext1), getResources().getString(R.string.maderatext1), getResources().getString(R.string.conchatext1), getResources().getString(R.string.porcelanatext1),
                getResources().getString(R.string.orfebreriatext1), getResources().getString(R.string.pieltext1), getResources().getString(R.string.tapiztext1)};

        textos2 = new String[]{getResources().getString(R.string.dibujotext2), getResources().getString(R.string.esculturatext2), getResources().getString(R.string.marqtext2)
                , getResources().getString(R.string.vidriotext2), getResources().getString(R.string.pirotext2), getResources().getString(R.string.doradotext2),
                getResources().getString(R.string.maderatext2), getResources().getString(R.string.maderatext2), getResources().getString(R.string.conchatext2), getResources().getString(R.string.porcelanatext2),
                getResources().getString(R.string.orfebreriatext2), getResources().getString(R.string.pieltext2), getResources().getString(R.string.tapiztext2)};
        setTitle(titulos[cont]);
        texto1.setText(textos1[cont]);
        texto2.setText(textos2[cont]);


        switch (cont) {
            case 0:
                arrayList.add(R.mipmap.dibujo7);
                arrayList.add(R.mipmap.dibujo2);
                arrayList.add(R.mipmap.dibujo3);
                arrayList.add(R.mipmap.dibujo4);
                arrayList.add(R.mipmap.dibujo5);
                arrayList.add(R.mipmap.dibujo1);
                arrayList.add(R.mipmap.dibujo6);
                arrayList.add(R.mipmap.dibujo8);
                break;
            case 1:
                arrayList.add(R.mipmap.escultura1);
                arrayList.add(R.mipmap.escul);
                arrayList.add(R.mipmap.escultura2);
                arrayList.add(R.mipmap.escultura3);
                arrayList.add(R.mipmap.escultura4);
                arrayList.add(R.mipmap.escultura5);
                break;
            case 2:
                arrayList.add(R.mipmap.marqueteria2);
                arrayList.add(R.mipmap.marqueteria1);
                arrayList.add(R.mipmap.marqueteria3);
                arrayList.add(R.mipmap.marqueteria4);
                arrayList.add(R.mipmap.marqueteria5);
                arrayList.add(R.mipmap.marqueteria6);
                arrayList.add(R.mipmap.marqueteria7);
                arrayList.add(R.mipmap.marqueteria8);
                arrayList.add(R.mipmap.marqueteria9);
                break;
            case 3:
                arrayList.add(R.mipmap.vidrio4);
                arrayList.add(R.mipmap.vidrio2);
                arrayList.add(R.mipmap.vidrio3);
                arrayList.add(R.mipmap.vidrio1);
                arrayList.add(R.mipmap.vidrio5);
                arrayList.add(R.mipmap.vidrio6);
                break;

            //pirograbado
            case 4:
                arrayList.add(R.mipmap.piro1);
                arrayList.add(R.mipmap.piro2);
                arrayList.add(R.mipmap.piro3);
                arrayList.add(R.mipmap.piro4);
                arrayList.add(R.mipmap.piro5);
                arrayList.add(R.mipmap.piro6);
                arrayList.add(R.mipmap.piro7);

                //dorado y estofado
            case 5:
                arrayList.add(R.mipmap.dorado1);
                arrayList.add(R.mipmap.dorado2);
                arrayList.add(R.mipmap.dorado3);
                arrayList.add(R.mipmap.dorado4);
                arrayList.add(R.mipmap.dorado5);
                arrayList.add(R.mipmap.dorado6);
                arrayList.add(R.mipmap.dorado7);

                break;

            case 6:
                arrayList.add(R.mipmap.madera4);
                arrayList.add(R.mipmap.madera2);
                arrayList.add(R.mipmap.madera3);
                arrayList.add(R.mipmap.madera1);
                arrayList.add(R.mipmap.madera5);
                arrayList.add(R.mipmap.madera6);
                arrayList.add(R.mipmap.madera7);
                arrayList.add(R.mipmap.madera8);
                break;
            //concha
            case 7:
                arrayList.add(R.mipmap.madera4);
                break;
            case 8:
                arrayList.add(R.mipmap.ceramica7);
                arrayList.add(R.mipmap.img1);
                arrayList.add(R.mipmap.ceramica3);
                arrayList.add(R.mipmap.ceramica4);
                arrayList.add(R.mipmap.ceramica5);
                arrayList.add(R.mipmap.ceramica6);
                arrayList.add(R.mipmap.ceramica2);
                arrayList.add(R.mipmap.ceramica8);
                arrayList.add(R.mipmap.ceramica9);
                break;
            case 9:
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
                break;
            //piel
            case 10:
                arrayList.add(R.mipmap.piel1);
                arrayList.add(R.mipmap.piel2);
                arrayList.add(R.mipmap.piel3);
                arrayList.add(R.mipmap.piel4);
                arrayList.add(R.mipmap.piel5);
                arrayList.add(R.mipmap.piel6);
                arrayList.add(R.mipmap.piel7);

                break;
            //tapiz
            case 11:
                arrayList.add(R.mipmap.tapiz1);
                arrayList.add(R.mipmap.tapiz2);
                arrayList.add(R.mipmap.tapiz3);
                arrayList.add(R.mipmap.tapiz4);
                arrayList.add(R.mipmap.tapiz5);
                arrayList.add(R.mipmap.tapiz6);
                arrayList.add(R.mipmap.tapiz7);
                arrayList.add(R.mipmap.tapiz8);
                break;

        }

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
        btnpresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Galeria.this, "presentacion", Toast.LENGTH_LONG).show();
            }
        });
        btntem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Galeria.this, "temario", Toast.LENGTH_LONG).show();
            }
        });

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