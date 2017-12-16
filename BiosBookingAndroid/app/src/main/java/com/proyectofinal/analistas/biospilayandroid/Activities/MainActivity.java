package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MainActivity extends AppCompatActivity implements GridObrasFragment.OnObraSeleccionadaListener{

    public static final String EXTRA_OBRA = "EXTRA_OBRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void OnObraSeleccionada(DtObra obra) {

        ObraInformationFragment frgDetalleObra = (ObraInformationFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleObra);

        if (frgDetalleObra != null) {
            frgDetalleObra.mostrarObra(obra);
        } else {
            Intent intencionDetalleObra = new Intent(this, ObraDetailActivity.class);
            intencionDetalleObra.putExtra(EXTRA_OBRA, obra);

            startActivity(intencionDetalleObra);
        }
    }
}
