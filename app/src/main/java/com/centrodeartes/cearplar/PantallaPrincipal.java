package com.centrodeartes.cearplar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.analytics.FirebaseAnalytics;

public class PantallaPrincipal extends AppCompatActivity implements View.OnClickListener {

    FloatingActionMenu actionMenu;
    FloatingActionButton ubicacion, doctos, llamar, btnquiene;
    Button btnnoticias, btncalendario, btnperfil;
    private AdView mAdView;

    Intent intent;
    private static final int REQUEST_CALL_PHONE = 0;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int PERMISO_NOTIFICACIONES = 1;

    private FirebaseAnalytics mFirebaseAnalytics;

    SharedPreferences sharedPref;
    int contcalifica;
    String linkplaystore = "https://play.google.com/store/apps/details?id=com.centrodeartes.cearplar";

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

        btncalendario = findViewById(R.id.btncalendario);
        btnnoticias = findViewById(R.id.btnnoticias);
        btnperfil = findViewById(R.id.btnperfil);


        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addfragment(new FragmentCursos(), "Cursos");
        vpAdapter.addfragment(new FragmentTalleres(), "Talleres");
        viewPager.setAdapter(vpAdapter);

        ubicacion = findViewById(R.id.ubicacionfloating);
        doctos = findViewById(R.id.requisitosfloating);
        llamar = findViewById(R.id.contactofloating);
        btnquiene = findViewById(R.id.quienessomos);
        ubicacion.setOnClickListener(this);
        doctos.setOnClickListener(this);
        llamar.setOnClickListener(this);
        btnquiene.setOnClickListener(this);
        btnnoticias.setOnClickListener(this);
        btncalendario.setOnClickListener(this);
        btnperfil.setOnClickListener(this);
        pedirPermisonotificaciones();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        sharedPref = getSharedPreferences("inicio", Context.MODE_PRIVATE);
        // validacion para mostrar dialogo de calificar app


        if (!sharedPref.getBoolean("dialogcali", false)) {
            contcalifica = sharedPref.getInt("califica", 0);
            Log.d("dialogo califica", "" + contcalifica);
            if (contcalifica == 12) {
                dialogocalifica();
                contcalifica = 0;
            } else
                contcalifica++;
            sharedPref.edit().putInt("califica", contcalifica).apply();
        }
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


        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent;
        int id = v.getId();

        if (id == R.id.quienessomos) {

            intent = new Intent(this, QuienesSomos.class);
            startActivity(intent);

        } else if (id == R.id.ubicacionfloating) {

            intent = new Intent(this, Ubicacion.class);
            startActivity(intent);

        } else if (id == R.id.requisitosfloating) {

            intent = new Intent(this, Requisitos.class);
            startActivity(intent);

        } else if (id == R.id.btncalendario) {

            intent = new Intent(this, Calendario.class);
            startActivity(intent);

        } else if (id == R.id.btnperfil) {

            SharedPreferences prefs = getSharedPreferences("Preferencias", MODE_PRIVATE);
            String nombre = prefs.getString("usuario", "");

            if (nombre.isEmpty()) {
                intent = new Intent(this, BuscarMatricula.class);
            } else {
                intent = new Intent(this, MostrarMatricula.class);
            }

            startActivity(intent);

        } else if (id == R.id.btnnoticias) {

            registroFirebaseAn("btnnoticias");
            cambioActivityUrl(
                    "https://centroartesanalindependencia.blogspot.com/",
                    "Noticias CAI."
            );

        } else if (id == R.id.contactofloating) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Para obtener informacion comunicate al numero 55-55-95-05-98, deseas marcar ahora?")
                    .setPositiveButton("si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestCallPhonePermission();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Usuario canceló
                        }
                    });

            builder.create();
            builder.show();
        }


    }

    private void cambioActivityUrl(String url, String titulo) {

        intent = new Intent(this, Urls.class);
        intent.putExtra("url", url);
        intent.putExtra("titulo", titulo);

        startActivity(intent);
        finish();
    }

    private void registroFirebaseAn(String buttonName) {
        Bundle bundle = new Bundle();
        bundle.putString("Nombre_boton", buttonName);
        mFirebaseAnalytics.logEvent("click", bundle);

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View vi = inflater.inflate(R.layout.dialogoconfirm, null);
            builder.setView(vi);
            final android.app.AlertDialog dialog = builder.create();
            quitarbordesdialogo(dialog);
            //decidir despues si sera cancelable o no
            dialog.setCancelable(false);
            Button botonsi = vi.findViewById(R.id.botonsi);
            botonsi.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            PantallaPrincipal.super.onDestroy();
                            System.exit(0);
                        }
                    }
            );
            Button botonno = vi.findViewById(R.id.botonno);
            botonno.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();

                        }
                    }
            );
            dialog.show();
            //Metodos.dialogo( this, getLayoutInflater(), "¿seguro deseas salir de la aplicacion?", 0 );
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void quitarbordesdialogo(android.app.AlertDialog dialog) {
        ColorDrawable dialogColor = new ColorDrawable(Color.GRAY);
        dialogColor.setAlpha(0);
        dialog.getWindow().setBackgroundDrawable(dialogColor);
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

    private void dialogocalifica() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PantallaPrincipal.this);
        final LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.dialogocalifica, null);
        builder.setView(vi);
        final android.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        quitarbordesdialogo(dialog);
        Button botonsi = vi.findViewById(R.id.botonsi);
        final CheckBox chbx = vi.findViewById(R.id.chbxcalifica);
        botonsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkplaystore)));
                if (chbx.isChecked()) {
                    sharedPref.edit().putBoolean("dialogcali", true).apply();
                }
                dialog.dismiss();
            }
        });
        Button botonno = vi.findViewById(R.id.botonno);
        botonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbx.isChecked()) {
                    sharedPref.edit().putBoolean("dialogcali", true).apply();
                }
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (chbx.isChecked()) {
                    sharedPref.edit().putBoolean("dialogcali", true).apply();
                }
            }
        });
        dialog.show();
    }
}