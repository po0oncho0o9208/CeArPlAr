package com.centrodeartes.cearplar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
}