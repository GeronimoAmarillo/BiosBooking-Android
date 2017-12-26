package com.proyectofinal.analistas.biospilayandroid.Logica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class ControladorMaterial implements Serializable{

    public List<DtObra> getObras() {
        return obras;
    }

    public void setObras(List<DtObra> obras) {
        this.obras = obras;
    }

    protected List<DtObra> obras;

    public ControladorMaterial(){

    }

    public static final String MIS_LOGS = "MIS_LOGS";



    public Material materialSeleccionado;

    public void SeleccionarMaterial(DTMaterial datosMaterial, int idObra) throws Exception {

        try{

            materialSeleccionado.setNombre(datosMaterial.getNombre());
            materialSeleccionado.setDescripcion(datosMaterial.getDescripcion());
            materialSeleccionado.setFechaAlta(datosMaterial.getFechaAlta());
            materialSeleccionado.setStock(datosMaterial.getStock());

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }



    }

    public List<DTMaterial> ListarMaterialesXObra(int idObra, SQLiteDatabase db) throws Exception {
        List<DTMaterial> materiales = new ArrayList<DTMaterial>();

        DTMaterial material = null;


        try{

            Cursor cursor = db.query(BDContract.TABLA_MATERIAL, BDContract.Materiales.COLUMNAS, BDContract.Materiales.COLUMNA_OBRA + " = ?", new String[]{String.valueOf(idObra)}, null, null, BDContract.Materiales.COLUMNA_FECHA_ALTA + " DESC");



            int columnaNombre = cursor.getColumnIndex(BDContract.Materiales.COLUMNA_NOMBRE);
            int columnaStock = cursor.getColumnIndex(BDContract.Materiales.COLUMNA_STOCK);
            int columnaDescripcion = cursor.getColumnIndex(BDContract.Materiales.COLUMNA_DESCRIPCION);
            int columnaFecha = cursor.getColumnIndex(BDContract.Materiales.COLUMNA_FECHA_ALTA);


            while(cursor.moveToNext()){

                material = new DTMaterial();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                material.setNombre(cursor.getString(columnaNombre));
                material.setStock(Integer.parseInt(cursor.getString(columnaStock)));
                material.setDescripcion(cursor.getString(columnaDescripcion));
                material.setMovimientos(ListarMovimientosXMaterial(idObra, material.getNombre(), db));

                String fechaTexto = cursor.getString(columnaFecha);

                Date fecha = null;
                try {
                    fecha = formatoFecha.parse(fechaTexto);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                material.setFechaAlta(fecha);

                materiales.add(material);
            }

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }

        return materiales;
    }

    public List<DTMovimiento> ListarMovimientosXMaterial(int idObra, String nombreMaterial, SQLiteDatabase db) throws Exception {
        List<DTMovimiento> movimientos = new ArrayList<DTMovimiento>();

        DTMovimiento movimiento = null;


        try{

            Cursor cursor = db.query(BDContract.TABLA_MOVIMIENTO, BDContract.Movimientos.COLUMNAS, BDContract.Movimientos.COLUMNA_OBRA + " = ?  AND " + BDContract.Movimientos.COLUMNA_MATERIAL + " = ?", new String[]{String.valueOf(idObra), nombreMaterial}, null, null, BDContract.Movimientos._ID + " DESC");

            int columnaId = cursor.getColumnIndex(BDContract.Movimientos._ID);
            int columnaObservacion = cursor.getColumnIndex(BDContract.Movimientos.COLUMNA_OBSERVACION);
            int columnaCantidad = cursor.getColumnIndex(BDContract.Movimientos.COLUMNA_CANTIDAD);
            int columnaFecha = cursor.getColumnIndex(BDContract.Movimientos.COLUMNA_FECHA_MOVIMIENTO);


            while(cursor.moveToNext()){

                movimiento = new DTMovimiento();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                movimiento.setId(cursor.getInt(columnaId));
                movimiento.setObservacion(cursor.getString(columnaObservacion));
                movimiento.setCantidad(Integer.parseInt(cursor.getString(columnaCantidad)));

                String fechaTexto = cursor.getString(columnaFecha);

                Date fecha = null;
                try {
                    fecha = formatoFecha.parse(fechaTexto);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                movimiento.setFecha(fecha);

                movimientos.add(movimiento);
            }

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }

        return movimientos;
    }

    public List<DtObra> listarObras(SQLiteDatabase bbdd) throws Exception {

        List<DtObra> obras = new ArrayList<DtObra>();
        DtObra obra = null;

        try{


            Cursor lista = bbdd.query(BDContract.TABLA_OBRA, BDContract.Obras.COLUMNAS, null, null, null, null, BDContract.Obras._ID + " DESC");


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
                obra.setMateriales(this.ListarMaterialesXObra(obra.getIdObra(), bbdd));

                obras.add(obra);
            }

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }

        return obras;
    }

    public boolean AltaMaterial(DTMaterial material, int idObra, SQLiteDatabase bd) throws Exception {



        try{

            ContentValues valores = new ContentValues();

            bd.beginTransaction();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String fecha = formato.format(material.getFechaAlta());

            try {
                valores.put(BDContract.Materiales.COLUMNA_NOMBRE, material.getNombre());
                valores.put(BDContract.Materiales.COLUMNA_DESCRIPCION, material.getDescripcion());
                valores.put(BDContract.Materiales.COLUMNA_STOCK, material.getStock());
                valores.put(BDContract.Materiales.COLUMNA_FECHA_ALTA, fecha);
                valores.put(BDContract.Materiales.COLUMNA_OBRA, idObra);
                bd.insert(BDContract.TABLA_MATERIAL, null, valores);

                bd.setTransactionSuccessful();

                return true;
            } catch (Exception ex) {
                Log.e(MIS_LOGS, "No se pudo insertar el material!.");

                return false;
            } finally {
                bd.endTransaction();
            }

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }

    }


    public void cargarDatosIniciales(SQLiteDatabase baseDatos) throws Exception {

        try{

            ContentValues valores = new ContentValues();

            String foto = Uri.parse("android.resource://com.proyectofinal.analistas.biospilayandroid/" + R.drawable.ic_launcher_foreground).toString();
            baseDatos.beginTransaction();

            try {
                valores.put(BDContract.Obras.COLUMNA_DIRECCION, "Elm Street");
                valores.put(BDContract.Obras.COLUMNA_FECHA_CONTRATO, "2017-11-21");
                valores.put(BDContract.Obras.COLUMNA_METROS_CUADRADOS, 1000);
                valores.put(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE, "Roberto Gomez");

                //foto = Uri.parse("android.resource://com.proyectofinal.analistas.biospilayandroid/" + R.mipmap.o1).toString();
                valores.put(BDContract.Obras.COLUMNA_FOTO, String.valueOf(R.mipmap.o1));
                baseDatos.insert(BDContract.TABLA_OBRA, null, valores);

                valores.put(BDContract.Obras.COLUMNA_DIRECCION, "21 Jump Street");
                valores.put(BDContract.Obras.COLUMNA_FECHA_CONTRATO, "2017-08-11");
                valores.put(BDContract.Obras.COLUMNA_METROS_CUADRADOS, 1300);
                valores.put(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE, "Natalia Natalia");

                //foto = Uri.parse("android.resource://com.proyectofinal.analistas.biospilayandroid/" + R.mipmap.o2).toString();
                valores.put(BDContract.Obras.COLUMNA_FOTO,  String.valueOf(R.mipmap.o2));

                baseDatos.insert(BDContract.TABLA_OBRA, null, valores);

                valores.put(BDContract.Obras.COLUMNA_DIRECCION, "Avenida Cloverfield 10");
                valores.put(BDContract.Obras.COLUMNA_FECHA_CONTRATO,  "2017-10-14");
                valores.put(BDContract.Obras.COLUMNA_METROS_CUADRADOS, 960);
                valores.put(BDContract.Obras.COLUMNA_NOMBRE_CLIENTE, "Caro Pardiaco");

                //foto = Uri.parse("android.resource://com.proyectofinal.analistas.biospilayandroid/" + R.mipmap.o3).toString();
                valores.put(BDContract.Obras.COLUMNA_FOTO,  String.valueOf(R.mipmap.o3));

                baseDatos.insert(BDContract.TABLA_OBRA, null, valores);


                baseDatos.setTransactionSuccessful();
            } catch (Exception ex) {
                Log.e(MIS_LOGS, "No se pudo insertar las obras de prueba.");
            } finally {
                baseDatos.endTransaction();
            }

        }catch(Exception ex){
            throw new Exception("ERROR: " + ex.getMessage());
        }

    }

}
