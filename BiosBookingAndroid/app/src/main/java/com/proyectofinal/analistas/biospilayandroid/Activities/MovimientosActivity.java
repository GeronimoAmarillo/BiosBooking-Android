package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMovimiento;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMovimiento;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MovimientosActivity extends AppCompatActivity {

    protected TextView tvIdObra;
    protected TextView tvNombreMaterial;
    protected TextView tvStock;
    protected CheckBox cbTipo;
    protected EditText etCantidad;
    protected EditText etObservacion;
    FloatingActionButton btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try{

            tvIdObra = (TextView)findViewById(R.id.tvObra);
            tvNombreMaterial = (TextView)findViewById(R.id.tvMaterial);
            tvStock = (TextView)findViewById(R.id.tvStock);
            cbTipo = (CheckBox)findViewById(R.id.cbTipoMovimiento);
            etCantidad = (EditText)findViewById(R.id.etCantidad);
            etObservacion = (EditText)findViewById(R.id.etObservacion);
            btnSalir = (FloatingActionButton)findViewById(R.id.btnSalir);

            Bundle extras = getIntent().getExtras();

            tvIdObra.setText(ControladorGral.getObraSeleccionada().getDireccion());
            tvNombreMaterial.setText(ControladorGral.getMaterialSeleccionado().getNombre());
            tvStock.setText(String.valueOf(ControladorGral.getMaterialSeleccionado().getStock()));


            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSalirOnClick(view);
                }
            });

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    public void RealizarMovimientoOnClick(View v){

        try{

            ControladorMovimiento controlador = new ControladorMovimiento();

            DTMovimiento datosMovimiento = new DTMovimiento();
            String nombreMaterial = "S/N";
            int idObra = 0;
            int stock = 0;
            int cantidad = 0;
            String observacion = "N/D";
            boolean exito = false;
            int nuevoStock = 0;
            int ultimoId = 0;


             idObra = ControladorGral.getObraSeleccionada().getIdObra();

            nombreMaterial = tvNombreMaterial.getText().toString();

            try{

                stock = Integer.parseInt(tvStock.getText().toString());

            }catch(Exception ex) {
                throw new Exception("Ocurrio un error al convertir el stock a entero.");
            }

            if(!cbTipo.isChecked()){

                try{

                    cantidad = Integer.parseInt(etCantidad.getText().toString());

                }catch(Exception ex){

                    throw new Exception("Codigo Convert1: Ocurrio un error al convertir la cantidad ingresada a entero.");

                }

                if(cantidad == 0){
                    throw new Exception("Error logico: la cantidad ingresada es 0.");
                }

            }else{

                try{

                    cantidad = Integer.parseInt("-" + etCantidad.getText().toString());

                }catch(Exception ex){

                    throw new Exception("Codigo Convert2: Ocurrio un error al convertir la cantidad ingresada a entero.");

                }

                if(stock + (cantidad) < 0){
                    throw new Exception("Error logico: la cantidad ingresada es superior al stock disponible.");
                }
            }

            nuevoStock = stock + cantidad;

            observacion = etObservacion.getText().toString();

            datosMovimiento.setCantidad(cantidad);
            datosMovimiento.setObservacion(observacion);

            BDHelper helper = new BDHelper(this);
            SQLiteDatabase bd = helper.getWritableDatabase();

            exito = controlador.realizarMovimiento(datosMovimiento, idObra, nombreMaterial, nuevoStock, bd);


            Intent intencion = new Intent(getApplicationContext(), MaterialInformationActivity.class);

            if(Configuration.SCREENLAYOUT_SIZE_LARGE == getResources().getConfiguration().screenLayout || Configuration.SCREENLAYOUT_SIZE_LARGE == getResources().getConfiguration().screenLayout){

                intencion = new Intent(getApplicationContext(), MaterialListActivity.class);
            }

            if(exito){
                intencion.putExtra("MENSAJE", "Se realizo el movimiento exitosamente!.");
            }else{
                intencion.putExtra("MENSAJE", "Se produjo un error al intentar registrar el movimiento!.");
            }

            ControladorGral.actualizarRepositorio(bd);

            startActivity(intencion);

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
