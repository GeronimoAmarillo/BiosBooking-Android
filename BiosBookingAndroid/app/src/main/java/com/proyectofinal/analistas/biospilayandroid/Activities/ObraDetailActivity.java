package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class ObraDetailActivity extends AppCompatActivity {

    ObraInformationFragment frgDetalleObra;

    DtObra obra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_obra_detail);

        frgDetalleObra = (ObraInformationFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleObra);

        Bundle extras = getIntent().getExtras();
        obra = (DtObra)extras.getSerializable(MainActivity.EXTRA_OBRA);
    }

    @Override
    protected void onStart() {
        super.onStart();

        frgDetalleObra.mostrarObra();
    }
}
