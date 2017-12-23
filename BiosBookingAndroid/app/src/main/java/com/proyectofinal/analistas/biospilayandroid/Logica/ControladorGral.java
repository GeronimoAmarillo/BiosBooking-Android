package com.proyectofinal.analistas.biospilayandroid.Logica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geronimo on 23/12/2017.
 */

public class ControladorGral implements Serializable {

    public ControladorGral(){

    }



    private static DtObra obraSeleccionada;
    private static DTMaterial materialSeleccionado;

    private static ControladorMaterial controlMateriales;
    private static ControladorMovimiento controlMovimientos;

    public static DtObra getObraSeleccionada() {
        return obraSeleccionada;
    }

    public static void setObraSeleccionada(DtObra obraSeleccionada) {
        ControladorGral.obraSeleccionada = obraSeleccionada;
    }

    public static DTMaterial getMaterialSeleccionado() {
        return materialSeleccionado;
    }

    public static void setMaterialSeleccionado(DTMaterial materialSeleccionado) {
        ControladorGral.materialSeleccionado = materialSeleccionado;
    }

    public static ControladorMaterial getControlMateriales() {
        return controlMateriales;
    }

    public static void setControlMateriales(ControladorMaterial controlMateriales) {
        ControladorGral.controlMateriales = controlMateriales;
    }

    public static ControladorMovimiento getControlMovimientos() {
        return controlMovimientos;
    }

    public static void setControlMovimientos(ControladorMovimiento controlMovimientos) {
        ControladorGral.controlMovimientos = controlMovimientos;
    }

    public static void actualizarRepositorio(SQLiteDatabase baseDatos){

        List<DtObra> obras = new ArrayList<DtObra>();
        DtObra obra = null;

        Cursor lista = baseDatos.query(BDContract.TABLA_OBRA, BDContract.Obras.COLUMNAS, null, null, null, null, BDContract.Obras._ID + " DESC");

        int columnaId = lista.getColumnIndex(BDContract.Obras._ID);
        int columnaFecha = lista.getColumnIndex(BDContract.Obras.COLUMNA_FECHA_CONTRATO);
        int columnaMetros = lista.getColumnIndex(BDContract.Obras.COLUMNA_METROS_CUADRADOS);
        int columnaCliente = lista.getColumnIndex(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE);
        int columnaDireccion = lista.getColumnIndex(BDContract.Obras.COLUMNA_DIRECCION);
        int columnaFoto = lista.getColumnIndex(BDContract.Obras.COLUMNA_FOTO);


        while(lista.moveToNext()){

            obra = new DtObra();

            obra.setIdObra(Integer.parseInt(lista.getString(columnaId)));
            obra.setFechadeContrato(java.sql.Date.valueOf(lista.getString(columnaFecha)));
            obra.setMetrosCuadrados(Double.parseDouble(lista.getString(columnaMetros)));
            obra.setNombreCliente(lista.getString(columnaCliente));
            obra.setDireccion(lista.getString(columnaDireccion));
            obra.setFoto(lista.getString(columnaFoto));
            obra.setMateriales(controlMateriales.ListarMaterialesXObra(obra.getIdObra(), baseDatos));

            obras.add(obra);
        }

        controlMateriales.setObras(obras);


        if(getObraSeleccionada() != null){
            setObraSeleccionada(SeleccionarObra(obraSeleccionada.getIdObra()));
        }

        if(getMaterialSeleccionado() != null){
            setMaterialSeleccionado(SeleccionarMaterial(materialSeleccionado.getNombre()));
        }

    }

    public static DtObra SeleccionarObra(int idObra){

        DtObra obraSeleccionada = null;

        for (DtObra o: controlMateriales.getObras()) {
            if(o.getIdObra() == idObra){
                obraSeleccionada = o;
            }
        }

        setObraSeleccionada(obraSeleccionada);

        return obraSeleccionada;
    }

    public static DTMaterial SeleccionarMaterial(String nombre){

        DTMaterial materialSeleccionado = null;

        for (DTMaterial m: obraSeleccionada.getMateriales()) {
            if(m.getNombre().equals(nombre)){
                materialSeleccionado = m;
            }
        }

        setMaterialSeleccionado(materialSeleccionado);

        return materialSeleccionado;
    }

}
