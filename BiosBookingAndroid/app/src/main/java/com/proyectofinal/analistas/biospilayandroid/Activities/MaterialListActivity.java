package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MaterialListActivity extends AppCompatActivity implements MaterialesListFragment.OnMaterialSeleccionadoListener {

    public DtObra obra;
    private MaterialesListFragment frgMaterialesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);

        frgMaterialesList = (MaterialesListFragment) getSupportFragmentManager().findFragmentById(R.id.frgListadoMateriales);

        Bundle extras = getIntent().getExtras();
        obra = (DtObra) extras.getSerializable("Obra");


    }

    @Override
    protected void onStart() {
        super.onStart();

        frgMaterialesList.listarMateriales(obra);
    }

    @Override
    public void onMaterialSeleccionado(DTMaterial material, int idObra) {

        /*MaterialInfoFragment frgDetalleMaterial = (MaterialInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleMaterial);

        if (frgDetalleMaterial != null) {
            frgDetalleMaterial(obra);
        } else {
            Intent intencionDetalleObra = new Intent(this, ObraDetailActivity.class);
            intencionDetalleObra.putExtra(EXTRA_OBRA, obra);

            startActivity(intencionDetalleObra);
        }*/

    }
}
