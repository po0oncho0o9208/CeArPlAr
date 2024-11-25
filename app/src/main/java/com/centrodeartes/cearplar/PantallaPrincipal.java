package com.centrodeartes.cearplar;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;

public class PantallaPrincipal extends AppCompatActivity implements View.OnClickListener {

    FloatingActionMenu actionMenu;
    FloatingActionButton ubicacion, doctos, llamar;
    private AdView mAdView;
    private static final int REQUEST_CALL_PHONE = 0;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int PERMISO_NOTIFICACIONES = 1;
    LinearLayout btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        actionMenu = findViewById(R.id.menufloating);
        actionMenu.setClosedOnTouchOutside(true);

        setTitle("Centro de Artes plasticas y artesanias");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);


        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addfragment(new FragmentCursos(), "Cursos");
        vpAdapter.addfragment(new FragmentTalleres(), "Talleres");
        viewPager.setAdapter(vpAdapter);

        ubicacion = findViewById(R.id.ubicacionfloating);
        doctos = findViewById(R.id.requisitosfloating);
        llamar = findViewById(R.id.contactofloating);
        ubicacion.setOnClickListener(this);
        doctos.setOnClickListener(this);
        llamar.setOnClickListener(this);
        pedirPermisonotificaciones();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btninsta) {
            //Toast.makeText(this, "Abriendo Instagram", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("https://www.instagram.com/artesanias_ind/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.instagram.android");

            try {
                startActivity(intent);
                Toast.makeText(this, "Abriendo Instagram", Toast.LENGTH_SHORT).show();
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No tienes instalado Instagram, se mostrara el contenido en tu navegador", Toast.LENGTH_SHORT).show();
                //No encontró la aplicación, abre la versión web.
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/artesanias_ind/")));

            }
        }



        if (item.getItemId() == R.id.face) {
            //Toast.makeText(this, "Abriendo Instagram", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("https://www.facebook.com/ArteSaludBienestar?locale=es_LA");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.facebook.android");

            try {
                startActivity(intent);
                Toast.makeText(this, "Abriendo Facebook", Toast.LENGTH_SHORT).show();
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No tienes instalado Facebook, se mostrara el contenido en tu navegador", Toast.LENGTH_SHORT).show();
                //No encontró la aplicación, abre la versión web.
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/ArteSaludBienestar?locale=es_LA")));

            }
        }



        if (item.getItemId() == R.id.btnnews) {

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://centroartesanalindependencia.blogspot.com/")));
            Toast.makeText(this, "hola news", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ubicacionfloating:
                intent = new Intent(this, Ubicacion.class);
                startActivity(intent);
                break;
            case R.id.requisitosfloating:
                intent = new Intent(this, Requisitos.class);
                startActivity(intent);
                break;
            case R.id.contactofloating:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Para obtener informacion comunicate al numero 55-55-95-05-98, deseas marcar ahora?")
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                requestCallPhonePermission();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create();
                builder.show();
                break;
            default:

                break;
        }

    }

    private void requestCallPhonePermission() {
        String callPermission = android.Manifest.permission.CALL_PHONE;
        int hasPermission = ActivityCompat.checkSelfPermission(this, callPermission);
        String[] permissions = new String[]{callPermission};
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, REQUEST_CALL_PHONE);
        } else {
            hacerllamada();
        }
    }

    private void hacerllamada() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:555-595-0598"));
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hacerllamada();
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void pedirPermisonotificaciones() {
        //Comprobación 'Racional'
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.POST_NOTIFICATIONS)) {

            android.app.AlertDialog AD;
            android.app.AlertDialog.Builder ADBuilder = new android.app.AlertDialog.Builder(PantallaPrincipal.this);
            ADBuilder.setMessage("Las notificaciones te mantienen al día, activalas para recibir informacion.");
            ADBuilder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Solicitamos permisos
                    ActivityCompat.requestPermissions(
                            PantallaPrincipal.this,
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                            PERMISO_NOTIFICACIONES);
                }
            });

            AD = ADBuilder.create();
            AD.show();

        } else {
            ActivityCompat.requestPermissions(
                    PantallaPrincipal.this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    PERMISO_NOTIFICACIONES);
        }


    }
}