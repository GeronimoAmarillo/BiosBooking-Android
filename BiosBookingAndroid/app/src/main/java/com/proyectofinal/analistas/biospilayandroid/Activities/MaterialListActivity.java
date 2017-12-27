package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MaterialListActivity extends AppCompatActivity implements MaterialesListFragment.OnMaterialSeleccionadoListener{

    public static final String EXTRA_MATERIAL = "EXTRA_MATERIAL";
    public static final String OBRA_DUEÑA_EXTRA = "OBRA_DUEÑA_EXTRA";

    String mensaje;
    MaterialesListFragment frgMaterialesList;
    FloatingActionButton btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try{

            setContentView(R.layout.activity_material_list);

            frgMaterialesList = (MaterialesListFragment) getSupportFragmentManager().findFragmentById(R.id.frgMaterialesList);
            btnSalir = (FloatingActionButton)findViewById(R.id.btnSalir);

            Bundle extras = getIntent().getExtras();

            if(extras != null){
                if(extras.getString("MENSAJE") != null){
                    mensaje = extras.getString("MENSAJE");
                }

                if(mensaje != null){
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onStart() {
        super.onStart();



        frgMaterialesList.listarMateriales();
    }


    @Override
    public void onMaterialSeleccionado(DTMaterial material) {

        try{

            MaterialInfoFragment frgDetalleMaterial = (MaterialInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleMaterial);

            ControladorGral.SeleccionarMaterial(material.getNombre());

            if (frgDetalleMaterial != null) {
                frgDetalleMaterial.mostrarMaterial();
            } else {
                Intent intencionDetalleMaterial = new Intent(this, MaterialInformationActivity.class);
                intencionDetalleMaterial.putExtra(EXTRA_MATERIAL, material);

                startActivity(intencionDetalleMaterial);
            }

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    protected void btnSalirOnClick(View v){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
