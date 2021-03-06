package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MainActivity extends AppCompatActivity implements GridObrasFragment.OnObraSeleccionadaListener/*, ObraInformationFragment.OnClickMaterialesListener*/{

    public static final String EXTRA_OBRA = "EXTRA_OBRA";
    MaterialesListFragment frgMaterialesList;
    FloatingActionButton btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalir = (FloatingActionButton)findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSalirOnClick(view);
            }
        });
    }

    @Override
    public void OnObraSeleccionada(DtObra obra) {

        try{

            ObraInformationFragment frgDetalleObra = (ObraInformationFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleObra);

            ControladorGral.SeleccionarObra(obra.getIdObra());

            if (frgDetalleObra != null) {
                frgDetalleObra.mostrarObra();
            } else {
                Intent intencionDetalleObra = new Intent(this, ObraDetailActivity.class);
                intencionDetalleObra.putExtra(EXTRA_OBRA, obra);

                startActivity(intencionDetalleObra);
            }

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    /*@Override
    public void OnClickMateriales() {
        try{

            frgMaterialesList = (MaterialesListFragment) getSupportFragmentManager().findFragmentById(R.id.frgMaterialesList);

            if (frgMaterialesList != null) {
                frgMaterialesList.listarMateriales();
            } else {

                Intent intencion = new Intent(this, MaterialListActivity.class);

                startActivity(intencion);
            }

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }*/

    protected void btnSalirOnClick(View v){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
