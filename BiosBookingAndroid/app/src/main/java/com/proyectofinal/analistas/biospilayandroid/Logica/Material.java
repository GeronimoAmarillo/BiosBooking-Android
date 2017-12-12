package com.proyectofinal.analistas.biospilayandroid.Logica;

import java.util.Date;

/**
 * Created by Geronimo on 12/12/2017.
 */

public class Material {

    private String nombre;
    private int stock;
    private Date fechaAlta;
    private String descripcion;


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

    public Material(){
        this("sin definir", 0, new Date(), "Sin descripcion");
    }

    public Material(String nombre, int stock, Date fechaAlta, String descripcion) {
        this.nombre = nombre;
        this.stock = stock;
        this.fechaAlta = fechaAlta;
        this.descripcion = descripcion;
    }
}
