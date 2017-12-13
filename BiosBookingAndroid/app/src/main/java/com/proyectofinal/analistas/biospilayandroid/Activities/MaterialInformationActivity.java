package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.R;

public class MaterialInformationActivity extends AppCompatActivity {

    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_information);

        Intent intent = getIntent();
        mensaje = intent.getExtras().getString("MENSAJE");

        if(mensaje != null || !mensaje.isEmpty()){
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        }

    }
}
