package com.proyectofinal.analistas.biospilayandroid.Logica;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;

import java.util.Date;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class ControladorMovimiento {

    public static final String MIS_LOGS = "MIS_LOGS";

    private BDHelper helper;
    private SQLiteDatabase bbdd;
    private SimpleCursorAdapter adaptadorMovimientos;

    Movimiento movimientoRecordado;

    public boolean realizarMovimiento(DTMovimiento datosMovimiento, int idObra, String nombreMaterial){

        ContentValues valores = new ContentValues();

        bbdd.beginTransaction();

        try {
            valores.put(BDContract.Movimientos.COLUMNA_CANTIDAD, datosMovimiento.getCantidad());
            valores.put(BDContract.Movimientos.COLUMNA_FECHA_MOVIMIENTO, new Date().toString());
            valores.put(BDContract.Movimientos.COLUMNA_MATERIAL, nombreMaterial);
            valores.put(BDContract.Movimientos.COLUMNA_OBRA, idObra);
            valores.put(BDContract.Movimientos.COLUMNA_OBSERVACION, datosMovimiento.getObservacion());
            bbdd.insert(BDContract.TABLA_MOVIMIENTO, null, valores);

            bbdd.setTransactionSuccessful();

            return true;
        } catch (Exception ex) {

            Log.e(MIS_LOGS, "No se pudo realizar el movimiento con exito.");
            return false;

        } finally {

            bbdd.endTransaction();

        }
    }

}
