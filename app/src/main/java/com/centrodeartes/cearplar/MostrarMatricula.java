package com.centrodeartes.cearplar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class MostrarMatricula extends AppCompatActivity {

    String usuario;
    FirebaseFirestore db;
    TextView texto;
    Button btnsalir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarmatricula);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        texto = findViewById(R.id.texto);
        SharedPreferences prefs = getSharedPreferences("Preferencias", MODE_PRIVATE);
        usuario = prefs.getString("usuario", "");

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(usuario).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {

                String nombre = documentSnapshot.getString("Nombre");
                String condicion = documentSnapshot.getString("Condicion");
                String actividad = documentSnapshot.getString("Actividad");
                String estatus = documentSnapshot.getString("Estatus");
                String pago = documentSnapshot.getString("Pago_realizado");


                String resultado = "Nombre: " + nombre + "\n\n" + "Condicion: " + condicion + "\n\n" + "Actividad: " + actividad + "\n\n" + "Esatus: " + estatus + "\n\n" + "Pago: " + pago;

                texto.setText(resultado);
                texto.setVisibility(View.VISIBLE);

            } else {
                texto.setText("Usuario no encontrado regresa a la pantalla anterior");
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("usuario", "");
                editor.apply();
            }
        }).addOnFailureListener(e -> texto.setText("Error: " + e.getMessage()));

        btnsalir = findViewById(R.id.btnsalir);
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("usuario", "");
                editor.apply();
                Intent intent = new Intent(MostrarMatricula.this, BuscarMatricula.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
        return false;
    }
}

