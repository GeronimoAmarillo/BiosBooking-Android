package com.proyectofinal.analistas.biospilayandroid.Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by matias on 12/12/2017.
 */

public class DtObra implements Serializable {



    private String nombreCliente;
    private Double metrosCuadrados;
    private Date fechadeContrato;
    private String direccion;
    private String foto;
    private int idObra;
    private List<DTMaterial> materiales;

    public DtObra(String nombreCliente, Double metrosCuadrados, Date fechadeContrato, String direccion, String foto, int idObra, List<DTMaterial> materiales) {
        this.nombreCliente = nombreCliente;
        this.metrosCuadrados = metrosCuadrados;
        this.fechadeContrato = fechadeContrato;
        this.direccion = direccion;
        this.foto = foto;
        this.idObra = idObra;
        this.materiales = materiales;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }

    public Double getMetrosCuadrados() {
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

    public DtObra(){
        this("sin definir", 0.0, new Date(), "sin direccion","sin especificar", -1, new ArrayList<DTMaterial>());
    }


    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public List<DTMaterial> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<DTMaterial> materiales) {
        this.materiales = materiales;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setMetrosCuadrados(Double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public void setFechadeContrato(Date fechadeContrato) {
        this.fechadeContrato = fechadeContrato;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
