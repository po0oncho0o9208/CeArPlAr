package com.centrodeartes.cearplar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    ViewPager viewpager, viewpagerfull;
    SliderAdapter adapter;
    private AdView mAdView;
    int cont;
    TextView texto1, texto2;
    String[] titulos, textos1, textos2, pdfsc, pdfss;
    LinearLayout layout;
    Button btnclose, btntem, btnweb, btnwhats, btnface;
    RelativeLayout btnpresent;
    int[] fondos, semblanzas,temarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_galeria);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        texto1 = findViewById(R.id.txvtxt1);
        texto2 = findViewById(R.id.txvtxt2);
        btnpresent = findViewById(R.id.btnpresentacion);
        btntem = findViewById(R.id.btntemario);
      //  btnweb = findViewById(R.id.btnweb);
        //btnwhats = findViewById(R.id.btnwhats);
        //btnface = findViewById(R.id.btnface);
        Intent intent = getIntent();
        cont = intent.getIntExtra("id", 0);
        layout = findViewById(R.id.layoutgaleria);
        layout.getBackground().setAlpha(215);

        semblanzas= new int[]{ R.drawable.eriks, R.drawable.tono, R.drawable.cristian, R.drawable.vid, R.drawable.piro, R.drawable.isa, R.drawable.venus, R.drawable.madera, R.drawable.barba, R.drawable.orfe, R.drawable.orfe,R.drawable.pieles,R.drawable.vidrio
        };

        temarios= new int[]{ R.drawable.temadibu, R.drawable.temaescu, R.drawable.temamarque, R.drawable.btntemario, R.drawable.temapiro, R.drawable.temacera,  R.drawable.temadorado, R.drawable.temamadera,R.drawable.temaconcha, R.drawable.temaorfe,R.drawable.temaorfe, R.drawable.temapiel,R.drawable.tematapiz
        };

        titulos = new String[]{getResources().getString(R.string.dibujotit), getResources().getString(R.string.esculturatit), getResources().getString(R.string.marqtit)
                , getResources().getString(R.string.vidriotit), getResources().getString(R.string.pirotit), "CERAMICA", getResources().getString(R.string.doradotit),
                getResources().getString(R.string.maderatit), getResources().getString(R.string.conchatit), getResources().getString(R.string.porcelanatit), getResources().getString(R.string.orfebreriatit), getResources().getString(R.string.pieltit),
                getResources().getString(R.string.tapiztit)};

        fondos = new int[]{R.drawable.fondopintura, R.drawable.fondoescultura, R.drawable.fondomarqueteria, R.drawable.fondovidrio, R.drawable.fondopiro, R.drawable.ceram, R.drawable.fondodorado, R.drawable.fondomadera, R.drawable.fondoconcha, R.drawable.fondoporce, R.drawable.fondoorfe, R.drawable.fondopiel, R.drawable.fondotapiz};

        pdfss = new String[]{"Curso de dibujo y pintura.pdf", "Curso de escultura.pdf", "Curso de marqueteria.pdf", "Curso de vidrio.pdf", "Curso de pirograbado.pdf","Curso de ceramica.pdf", "Curso de dorado.pdf", "Curso de talla en madera.pdf", "Curso de concha.pdf", "Curso esmalte.pdf", "Curso de orfebreria.pdf", "Curso de piel.pdf", ""};

        pdfsc = new String[]{"semblanza dibujo.pdf", "semblanza escultura.pdf", "semblanza marqueteria.pdf", "semblanza vidrio.pdf", "", "semblanza ceramica.pdf", "",
                "", "", "", "", "", "", ""};

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

        ImageView semblanza = findViewById(R.id.semblanza);
        semblanza.setImageDrawable(getResources().getDrawable(semblanzas[cont]));

        Button temario = findViewById(R.id.btntemario);
        temario.setBackground(getResources().getDrawable(temarios[cont]));

        ConstraintLayout fondo = findViewById(R.id.fondo);
        fondo.setBackground(getResources().getDrawable(fondos[cont]));

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
                break;

                //Ceramica
            case 5:
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

                //dorado y estofado
            case 6:
                arrayList.add(R.mipmap.dorado1);
                arrayList.add(R.mipmap.dorado2);
                arrayList.add(R.mipmap.dorado3);
                arrayList.add(R.mipmap.dorado4);
                arrayList.add(R.mipmap.dorado5);
                arrayList.add(R.mipmap.dorado6);
                arrayList.add(R.mipmap.dorado7);

                break;

            case 7:
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
            case 8:
                arrayList.add(R.mipmap.concha);
                arrayList.add(R.mipmap.concha1);
                arrayList.add(R.mipmap.concha2);
                arrayList.add(R.mipmap.concha3);
                arrayList.add(R.mipmap.concha4);
                arrayList.add(R.mipmap.concha5);


                break;
            case 9:
                arrayList.add(R.mipmap.esmalte1);
                arrayList.add(R.mipmap.esmalte2);
                arrayList.add(R.mipmap.esmalte3);
                arrayList.add(R.mipmap.esmalte4);
                arrayList.add(R.mipmap.esmalte5);
                arrayList.add(R.mipmap.esmalte6);


                break;
            case 10:
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
            case 11:
                arrayList.add(R.mipmap.piel1);
                arrayList.add(R.mipmap.piel2);
                arrayList.add(R.mipmap.piel3);
                arrayList.add(R.mipmap.piel4);
                arrayList.add(R.mipmap.piel5);
                arrayList.add(R.mipmap.piel6);
                arrayList.add(R.mipmap.piel7);

                break;
            //tapiz
            case 12:
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

        btnclose = findViewById(R.id.btncloseimage);
        btnclose.setOnClickListener(this);
        viewpager = findViewById(R.id.viewpager);
        viewpagerfull = findViewById(R.id.viewpagerfull);
        adapter = new SliderAdapter(this, arrayList, btnclose, viewpagerfull, layout);
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
                abrirPDF(pdfsc[cont]);

            }
        });
        btntem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPDF(pdfss[cont]);
                Toast.makeText(Galeria.this, "temario", Toast.LENGTH_LONG).show();
            }
        });
        //btnweb.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {
          //      abrirWeb("https://www.google.com");
            //}
        //});
        //btnwhats.setOnClickListener(new View.OnClickListener() {
            //@Override
          //  public void onClick(View v) {
            //    enviarMensajePorWhatsApp("5215530064837", "Holi");
          //  }
        //});
        //btnface.setOnClickListener(new View.OnClickListener() {
            //@Override
          //  public void onClick(View v) {
            //    abrirPerfilFace();
            //}
        //});

    }

    @Override
    public void onClick(View v) {
        layout.setVisibility(View.GONE);
        viewpagerfull.setVisibility(View.GONE);
        btnclose.setVisibility(View.GONE);
        viewpager.setCurrentItem(viewpagerfull.getCurrentItem());
        adapter.isfull = false;
    }

    //habilitar boton hacia atras
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void abrirPDF(String nombre) {
        Intent intent = new Intent(Galeria.this, PdfViewer.class);
        intent.putExtra("nombrepdf", nombre);
        startActivity(intent);
    }

   /* private void abrirWeb(String url) {
        try {
            // Crear un Intent con la acción ACTION_VIEW
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // Establecer el URI del enlace
            intent.setData(Uri.parse(url));
            // Verificar si hay aplicaciones que puedan manejar este Intent
            if (intent.resolveActivity(this.getPackageManager()) != null) {
                // Iniciar la actividad
                this.startActivity(intent);
            } else {
                // Manejo en caso de que no haya aplicaciones disponibles
                System.out.println("No hay aplicaciones disponibles para abrir el enlace.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al intentar abrir el enlace: " + e.getMessage());
        }
    } */

   /* private void enviarMensajePorWhatsApp(String numero, String mensaje) {
        try {
            // Verificar que el número no esté vacío
            if (numero == null || numero.isEmpty()) {
                System.out.println("El número no puede estar vacío.");
                return;
            }

            // Crear la URI con el número y mensaje
            String uri = "https://wa.me/" + numero + "?text=" + Uri.encode(mensaje);

            // Crear un Intent para abrir WhatsApp
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            // Verificar si hay aplicaciones que puedan manejar este Intent

            if (intent.resolveActivity(this.getPackageManager()) != null) {
                // Iniciar la actividad
                this.startActivity(intent);
            } else {
                // Manejo en caso de que WhatsApp no esté instalado
                Toast.makeText(this, "WhatsApp no está instalado en el dispositivo.", Toast.LENGTH_SHORT).show();
                System.out.println("WhatsApp no está instalado en el dispositivo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al intentar enviar el mensaje: " + e.getMessage());
        }
    }

    public void abrirPerfilFace() {
        String facebookId = "fb://profile/100000751622022";
        String facebookUrl = "https://www.facebook.com/po0oncho0o/";

        try {
            // Abre la app de Facebook si está instalada
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId));
            startActivity(intent);
        } catch (Exception e) {
            // Si no está instalada, abre en el navegador
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
            startActivity(intent);
        }
    } */
}