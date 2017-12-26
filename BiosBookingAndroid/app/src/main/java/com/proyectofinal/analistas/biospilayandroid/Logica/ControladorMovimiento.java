package com.proyectofinal.analistas.biospilayandroid.Logica;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class ControladorMovimiento implements Serializable{

    public ControladorMovimiento(){

    }

    public static final String MIS_LOGS = "MIS_LOGS";

    private BDHelper helper;
    private SQLiteDatabase bbdd;
    private SimpleCursorAdapter adaptadorMovimientos;

    Movimiento movimientoRecordado;

    public boolean realizarMovimiento(DTMovimiento datosMovimiento, int idObra, String nombreMaterial, int nuevoStock, SQLiteDatabase db) throws Exception {

        try{

            ContentValues valores = new ContentValues();

            db.beginTransaction();


            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String fecha = formato.format(new Date());

            try {



                valores.put(BDContract.Movimientos.COLUMNA_CANTIDAD, String.valueOf(datosMovimiento.getCantidad()));
                valores.put(BDContract.Movimientos.COLUMNA_FECHA_MOVIMIENTO, fecha);
                valores.put(BDContract.Movimientos.COLUMNA_MATERIAL, nombreMaterial);
                valores.put(BDContract.Movimientos.COLUMNA_OBRA, idObra);
                valores.put(BDContract.Movimientos.COLUMNA_OBSERVACION, datosMovimiento.getObservacion());
                db.insert(BDContract.TABLA_MOVIMIENTO, null, valores);

                valores.clear();
                valores.put(BDContract.Materiales.COLUMNA_STOCK, nuevoStock);
                db.update(BDContract.TABLA_MATERIAL, valores, BDContract.Materiales.COLUMNA_OBRA + " = ? AND " + BDContract.Materiales.COLUMNA_NOMBRE + " = ?", new String[] {String.valueOf(idObra), nombreMaterial});

                db.setTransactionSuccessful();

                return true;
            } catch (Exception ex) {

                Log.e(MIS_LOGS, "No se pudo realizar el movimiento con exito." + ex);
                return false;

            } finally {

                db.endTransaction();

            }

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }

    }

}
