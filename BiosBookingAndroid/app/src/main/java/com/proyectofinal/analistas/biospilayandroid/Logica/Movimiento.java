package com.proyectofinal.analistas.biospilayandroid.Logica;

/**
 * Created by Geronimo on 12/12/2017.
 */

public class Movimiento {
    private String observacion;
    private int cantidad;



    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Movimiento(){
        this("sin observacion.", 0);
    }

    public Movimiento(String observacion, int cantidad) {
        this.observacion = observacion;
        this.cantidad = cantidad;
    }
}
