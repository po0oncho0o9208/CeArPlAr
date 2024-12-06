package com.centrodeartes.cearplar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.Objects;

public class PdfViewer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdfviewer);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        PDFView pdfView = findViewById(R.id.pdfView);
        String pdfFileName = getIntent().getStringExtra("nombrepdf");
        setTitle(pdfFileName);
        // Cargar un archivo PDF desde los recursos
        pdfView.fromAsset(pdfFileName) // Reemplaza "sample.pdf" con el nombre de tu archivo en assets
                .enableSwipe(true) // Habilita el deslizamiento
                .swipeHorizontal(false) // Configuración para deslizar horizontalmente
                .enableAnnotationRendering(true) // Renderizar anotaciones (si las hay)
                .load();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
