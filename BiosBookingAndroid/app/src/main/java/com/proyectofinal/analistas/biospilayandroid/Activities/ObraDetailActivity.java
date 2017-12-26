package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class ObraDetailActivity extends AppCompatActivity /*implements ObraInformationFragment.OnClickMaterialesListener*/ {

    ObraInformationFragment frgDetalleObra;
    MaterialesListFragment frgMaterialesList;

    DtObra obra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

            setContentView(R.layout.activity_obra_detail);

            frgDetalleObra = (ObraInformationFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleObra);

            Bundle extras = getIntent().getExtras();
            obra = (DtObra)extras.getSerializable(MainActivity.EXTRA_OBRA);

        }catch(Exception ex){
            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        try{

            frgDetalleObra.mostrarObra();

        }catch(Exception ex){
            Toast.makeText(this, "ERROR: Se produjo un error al mostrar la informacion de la obra.", Toast.LENGTH_LONG).show();
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
}
