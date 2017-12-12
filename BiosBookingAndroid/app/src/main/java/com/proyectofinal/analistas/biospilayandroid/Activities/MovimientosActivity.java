package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.R;

public class MovimientosActivity extends AppCompatActivity {

    protected TextView tvIdObra;
    protected TextView tvNombreMaterial;
    protected TextView tvStock;
    protected CheckBox cbTipo;
    protected EditText etCantidad;
    protected EditText etObservacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        tvIdObra = (TextView)findViewById(R.id.tvIdObra);
        tvNombreMaterial = (TextView)findViewById(R.id.tvNombreMaterial);
        tvStock = (TextView)findViewById(R.id.tvStock);
        cbTipo = (CheckBox)findViewById(R.id.cbTipoMovimiento);
        etCantidad = (EditText)findViewById(R.id.etCantidad);
        etObservacion = (EditText)findViewById(R.id.etCantidad);

        Intent intencion = getIntent();

        tvIdObra.setText(intencion.getExtras().getString("OBRA"));
        tvNombreMaterial.setText(intencion.getExtras().getString("MATERIAL"));
        tvStock.setText(intencion.getExtras().getString("STOCK"));
    }

    public void RealizarMovimientoOnClick(View v){

    }
}
