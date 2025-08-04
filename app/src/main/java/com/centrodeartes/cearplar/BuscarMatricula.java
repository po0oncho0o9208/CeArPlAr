package com.centrodeartes.cearplar;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class BuscarMatricula extends AppCompatActivity {

    EditText editText;
    Button botonbuscar;
    TextView textviewError;
    FirebaseFirestore db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarmatricula);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        editText = findViewById(R.id.edittextmatricula);
        textviewError = findViewById(R.id.textviewerror);
        botonbuscar = findViewById(R.id.buttonbuscar);
        botonbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().trim().isEmpty()) {
                    cargarDatosUsuario(editText.getText().toString());
                } else {
                    Toast.makeText(BuscarMatricula.this, "Ingresa tu matricula", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cargarDatosUsuario(String matricula) {
        db.collection("usuarios").document(matricula).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {


                        LayoutInflater inflater = getLayoutInflater();
                        View vistaDialogo = inflater.inflate(R.layout.dialogocargardatos, null);
                        AlertDialog.Builder constructor = new AlertDialog.Builder(this);
                        constructor.setView(vistaDialogo);
                        AlertDialog dialogo = constructor.create();
                        dialogo.show();
                        TextView titulo = vistaDialogo.findViewById(R.id.titulo);
                        titulo.setText("Deseas cargar la informacion de " + documentSnapshot.getString("Nombre"));
                        Button botonsi = vistaDialogo.findViewById(R.id.btnsi);
                        botonsi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                SharedPreferences prefs = getSharedPreferences("Preferencias", MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("usuario", matricula);
                                editor.apply();

                                Intent intent = new Intent(BuscarMatricula.this, MostrarMatricula.class);
                                startActivity(intent);

                            }
                        });
                        Button botonno = vistaDialogo.findViewById(R.id.btnno);
                        botonno.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogo.cancel();
                                editText.setText("");
                            }
                        });


                    } else {
                        textviewError.setText("Usuario no encontrado");
                        textviewError.setVisibility(View.VISIBLE);
                    }
                })
                .addOnFailureListener(e -> textviewError.setText("Error: " + e.getMessage()));
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
