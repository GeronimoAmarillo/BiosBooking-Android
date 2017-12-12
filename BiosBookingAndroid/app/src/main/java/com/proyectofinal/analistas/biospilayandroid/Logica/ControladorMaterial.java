package com.proyectofinal.analistas.biospilayandroid.Logica;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class ControladorMaterial {

    public Material materialSeleccionado;

    public void SeleccionarMaterial(DTMaterial datosMaterial, int idObra){

        materialSeleccionado.setNombre(datosMaterial.getNombre());
        materialSeleccionado.setDescripcion(datosMaterial.getDescripcion());
        materialSeleccionado.setFechaAlta(datosMaterial.getFechaAlta());
        materialSeleccionado.setStock(datosMaterial.getStock());

    }
}
