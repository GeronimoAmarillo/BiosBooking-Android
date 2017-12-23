package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MaterialInformationActivity extends AppCompatActivity implements MaterialInfoFragment.OnMaterialSeleccionadoListener {

    String mensaje;

    private MaterialInfoFragment frgMaterialInfo;
    DTMaterial material;
    int idObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_information);

        frgMaterialInfo = (MaterialInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleMaterial);

        Bundle extras = getIntent().getExtras();

        material = (DTMaterial)extras.getSerializable(MaterialListActivity.EXTRA_MATERIAL);
        idObra = extras.getInt(MaterialListActivity.OBRA_DUEÑA_EXTRA);

        mensaje = extras.getString("MENSAJE");

        if(mensaje != null){
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        frgMaterialInfo.mostrarMaterial(material, idObra);
    }

    @Override
    public void OnMaterialSeleccionado(DtObra obra) {


    }
}
