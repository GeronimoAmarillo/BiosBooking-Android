package com.proyectofinal.analistas.biospilayandroid.Logica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private BDHelper helper;
    private SQLiteDatabase bbdd;
    private SimpleCursorAdapter adaptador;

    public Material materialSeleccionado;

    public void SeleccionarMaterial(DTMaterial datosMaterial, int idObra){

        materialSeleccionado.setNombre(datosMaterial.getNombre());
        materialSeleccionado.setDescripcion(datosMaterial.getDescripcion());
        materialSeleccionado.setFechaAlta(datosMaterial.getFechaAlta());
        materialSeleccionado.setStock(datosMaterial.getStock());

    }

    public Cursor listarObras() {

        return bbdd.query(BDContract.TABLA_OBRA, BDContract.Obras.COLUMNAS, null, null, null, null, BDContract.Obras.COLUMNA_NOMBRE_CLIENTE + " DESC");
    }

}
