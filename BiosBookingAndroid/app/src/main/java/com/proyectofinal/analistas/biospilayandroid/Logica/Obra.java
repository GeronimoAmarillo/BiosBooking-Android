package com.proyectofinal.analistas.biospilayandroid.Logica;

import java.util.Date;

/**
 * Created by matias on 12/12/2017.
 */

public class Obra {


    private String nombreCliente;
    private String metrosCuadrados;
    private Date fechadeContrato;
    private String direccion;
    private String foto;


    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getMetrosCuadrados() {
        return metrosCuadrados;
    }
    public Date getFechadeContrato() {
        return fechadeContrato;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFoto() {
        return foto;
    }

    public Obra(){
        this("sin definir", "0", new Date(), "sin direccion","sin especificar");
    }

    public Obra(String nombre, String metrosCuadrados, Date fechadeContrato, String direccion , String foto) {
        this.nombreCliente = nombre;
        this.metrosCuadrados = metrosCuadrados;
        this.fechadeContrato= fechadeContrato;
        this.direccion = direccion;
    }


}
