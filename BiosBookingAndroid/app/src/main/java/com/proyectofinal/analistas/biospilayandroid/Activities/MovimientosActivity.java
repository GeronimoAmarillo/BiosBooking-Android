package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;

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

        tvIdObra = (TextView)findViewById(R.id.tvObra);
        tvNombreMaterial = (TextView)findViewById(R.id.tvMaterial);
        tvStock = (TextView)findViewById(R.id.tvStock);
        cbTipo = (CheckBox)findViewById(R.id.cbTipoMovimiento);
        etCantidad = (EditText)findViewById(R.id.etCantidad);
        etObservacion = (EditText)findViewById(R.id.etObservacion);

        Bundle extras = getIntent().getExtras();

        tvIdObra.setText(String.valueOf(ControladorGral.getObraSeleccionada().getIdObra()));
        tvNombreMaterial.setText(ControladorGral.getMaterialSeleccionado().getNombre());
        tvStock.setText(String.valueOf(ControladorGral.getMaterialSeleccionado().getStock()));
    }

    public void RealizarMovimientoOnClick(View v){

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

        try{

            idObra = Integer.parseInt(tvIdObra.getText().toString());

        }catch(Exception ex) {
            Toast.makeText(this, "Ocurrio un error al convertir el id de obra a entero.", Toast.LENGTH_SHORT);
        }

        nombreMaterial = tvNombreMaterial.getText().toString();

        try{

            stock = Integer.parseInt(tvStock.getText().toString());

        }catch(Exception ex) {
            Toast.makeText(this, "Ocurrio un error al convertir el stock a entero.", Toast.LENGTH_SHORT);
        }

        if(!cbTipo.isChecked()){

            try{

                cantidad = Integer.parseInt(etCantidad.getText().toString());

                if(cantidad == 0){
                    throw new IOException("Error logico: la cantidad ingresada es 0.");
                }

            }catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT);

            } catch(Exception ex) {
                Toast.makeText(this, "Codigo Convert1: Ocurrio un error al convertir la cantidad ingresada a entero.", Toast.LENGTH_SHORT);
            }

        }else{

            try{

                cantidad = Integer.parseInt("-" + etCantidad.getText().toString());

                if(stock + (cantidad) == 0){
                    throw new IOException("Error logico: la cantidad ingresada es superior al stock disponible.");
                }


            }catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT);

            } catch(Exception ex) {
                Toast.makeText(this, "Codigo Convert2: Ocurrio un error al convertir la cantidad ingresada a entero.", Toast.LENGTH_SHORT);
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

        if(exito){
            intencion.putExtra("MENSAJE", "Se realizo el movimiento exitosamente!.");
        }else{
            intencion.putExtra("MENSAJE", "Se produjo un error al intentar registrar el movimiento!.");
        }

        ControladorGral.actualizarRepositorio(bd);

        startActivity(intencion);

    }
}
