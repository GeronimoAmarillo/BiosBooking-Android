package com.proyectofinal.analistas.biospilayandroid.Logica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class ControladorMaterial {


    public ControladorMaterial(){

    }

    public static final String MIS_LOGS = "MIS_LOGS";


    public Material materialSeleccionado;

    public void SeleccionarMaterial(DTMaterial datosMaterial, int idObra){

        materialSeleccionado.setNombre(datosMaterial.getNombre());
        materialSeleccionado.setDescripcion(datosMaterial.getDescripcion());
        materialSeleccionado.setFechaAlta(datosMaterial.getFechaAlta());
        materialSeleccionado.setStock(datosMaterial.getStock());

    }

    public Cursor listarObras(SQLiteDatabase bbdd) {

        return bbdd.query(BDContract.TABLA_OBRA, BDContract.Obras.COLUMNAS, null, null, null, null, BDContract.Obras._ID + " DESC");
    }

    public void cargarDatosIniciales(SQLiteDatabase baseDatos){

        ContentValues valores = new ContentValues();

        baseDatos.beginTransaction();

        try {
            valores.put(BDContract.Obras.COLUMNA_DIRECCION, "Elm Street");
            valores.put(BDContract.Obras.COLUMNA_FECHA_CONTRATO, "2017-11-21");
            valores.put(BDContract.Obras.COLUMNA_METROS_CUADRADOS, 1000);
            valores.put(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE, "Roberto Gomez");
            valores.put(BDContract.Obras.COLUMNA_FOTO, "/ruta");
            baseDatos.insert(BDContract.TABLA_OBRA, null, valores);

            valores.put(BDContract.Obras.COLUMNA_DIRECCION, "21 Jump Street");
            valores.put(BDContract.Obras.COLUMNA_FECHA_CONTRATO, "2017-08-11");
            valores.put(BDContract.Obras.COLUMNA_METROS_CUADRADOS, 1300);
            valores.put(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE, "Natalia Natalia");
            valores.put(BDContract.Obras.COLUMNA_FOTO, "/ruta");
            baseDatos.insert(BDContract.TABLA_OBRA, null, valores);

            valores.put(BDContract.Obras.COLUMNA_DIRECCION, "Avenida Cloverfield 10");
            valores.put(BDContract.Obras.COLUMNA_FECHA_CONTRATO,  "2017-10-14");
            valores.put(BDContract.Obras.COLUMNA_METROS_CUADRADOS, 960);
            valores.put(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE, "Caro Pardiaco");
            valores.put(BDContract.Obras.COLUMNA_FOTO, "/ruta");
            baseDatos.insert(BDContract.TABLA_OBRA, null, valores);


            baseDatos.setTransactionSuccessful();
        } catch (Exception ex) {
            Log.e(MIS_LOGS, "No se pudo insertar las obras de prueba.");
        } finally {
            baseDatos.endTransaction();
        }
    }

}
