package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MaterialListActivity extends AppCompatActivity implements MaterialesListFragment.OnMaterialSeleccionadoListener {

    public static final String EXTRA_MATERIAL = "EXTRA_MATERIAL";
    public static final String OBRA_DUEÑA_EXTRA = "OBRA_DUEÑA_EXTRA";

    public DtObra obra;
    String mensaje;
    private MaterialesListFragment frgMaterialesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);

        frgMaterialesList = (MaterialesListFragment) getSupportFragmentManager().findFragmentById(R.id.frgMaterialesList);

        Bundle extras = getIntent().getExtras();
        obra = (DtObra) extras.getSerializable("Obra");

        mensaje = extras.getString("MENSAJE");

        if(mensaje != null){
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        frgMaterialesList.listarMateriales(obra);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onMaterialSeleccionado(DTMaterial material, int idObra) {

        MaterialInfoFragment frgDetalleMaterial = (MaterialInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleMaterial);

        if (frgDetalleMaterial != null) {
            frgDetalleMaterial.mostrarMaterial(material, idObra);
        } else {
            Intent intencionDetalleMaterial = new Intent(this, MaterialInformationActivity.class);
            intencionDetalleMaterial.putExtra(OBRA_DUEÑA_EXTRA, idObra);
            intencionDetalleMaterial.putExtra(EXTRA_MATERIAL, material);

            startActivity(intencionDetalleMaterial);
        }

    }
}
