package com.proyectofinal.analistas.biospilayandroid.Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Geronimo on 12/12/2017.
 */

public class DTMaterial implements Serializable{
    private String nombre;
    private int stock;
    private Date fechaAlta;
    private String descripcion;

    public List<DTMovimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<DTMovimiento> movimientos) {
        this.movimientos = movimientos;
    }

    private List<DTMovimiento> movimientos;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DTMaterial(String nombre, int stock, Date fechaAlta, String descripcion, List<DTMovimiento> movimientos) {
        this.nombre = nombre;
        this.stock = stock;
        this.fechaAlta = fechaAlta;
        this.descripcion = descripcion;
        this.movimientos = movimientos;
    }

    public DTMaterial() {
        this("S/N", 0, new Date(), "S/D", new ArrayList<DTMovimiento>());
    }
}
