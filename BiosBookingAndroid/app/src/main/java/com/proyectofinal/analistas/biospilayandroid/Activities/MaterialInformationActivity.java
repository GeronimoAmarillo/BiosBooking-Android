package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

public class MaterialInformationActivity extends AppCompatActivity implements MaterialInfoFragment.OnMaterialSeleccionadoListener {

    String mensaje;

    private MaterialInfoFragment frgMaterialInfo;
    FloatingActionButton btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

            setContentView(R.layout.activity_material_information);

            frgMaterialInfo = (MaterialInfoFragment) getSupportFragmentManager().findFragmentById(R.id.frgDetalleMaterial);
            btnSalir = (FloatingActionButton)findViewById(R.id.btnSalir);

            Bundle extras = getIntent().getExtras();


            if(extras != null){
                if(extras.getString("MENSAJE") != null){
                    mensaje = extras.getString("MENSAJE");

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

        try{

            frgMaterialInfo.mostrarMaterial();

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: Se produjo un error al mistrar los Materiales", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void OnMaterialSeleccionado(DtObra obra) {


    }

    protected void btnSalirOnClick(View v){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
