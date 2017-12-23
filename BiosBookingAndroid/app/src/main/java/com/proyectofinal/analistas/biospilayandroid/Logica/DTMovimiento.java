package com.proyectofinal.analistas.biospilayandroid.Logica;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Geronimo on 12/12/2017.
 */

public class DTMovimiento implements Serializable{


    private int id;
    private String observacion;
    private int cantidad;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DTMovimiento(String observacion, int cantidad, Date fecha, int id) {
        this.observacion = observacion;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.id = id;
    }
    public DTMovimiento() {
        this("sin observacion", 0, new Date(), -1);
    }
}
