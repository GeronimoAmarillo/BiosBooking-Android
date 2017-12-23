package com.proyectofinal.analistas.biospilayandroid.Persistencia;

import android.provider.BaseColumns;

import com.proyectofinal.analistas.biospilayandroid.Logica.Material;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class BDContract {

    public static final String NOMBRE_BASE_DATOS = "BaseDatosBiosPilay.sqlite3";
    public static final int VERSION_BASE_DATOS = 1;

    public static final String TABLA_OBRA = "Obras";
    public static final String TABLA_MATERIAL = "Materiales";
    public static final String TABLA_MOVIMIENTO = "Movimientos";


    private BDContract() {

    }


    public static abstract class Obras implements BaseColumns {

        public static final String COLUMNA_FECHA_CONTRATO = "FechaContrato";
        public static final String COLUMNA_METROS_CUADRADOS = "MetrosCuadrados";
        public static final String COLUMNA_NOMBRE_CLIENTE = "NombreCliente";
        public static final String COLUMNA_DIRECCION = "Direccion";
        public static final String COLUMNA_FOTO = "Foto";

        public static final String[] COLUMNAS = { _ID, COLUMNA_FECHA_CONTRATO, COLUMNA_METROS_CUADRADOS, COLUMNA_NOMBRE_CLIENTE, COLUMNA_DIRECCION, COLUMNA_FOTO };

        public static final String SQL_CREAR_TABLA = new StringBuilder("CREATE TABLE ").append(TABLA_OBRA).append(" (")
                .append(_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMNA_FECHA_CONTRATO).append(" TEXT NOT NULL, ")
                .append(COLUMNA_METROS_CUADRADOS).append(" REAL NOT NULL, ")
                .append(COLUMNA_NOMBRE_CLIENTE).append(" TEXT NOT NULL, ")
                .append(COLUMNA_DIRECCION).append(" TEXT NOT NULL, ")
                .append(COLUMNA_FOTO).append(" TEXT NOT NULL);").toString();

        public static final String SQL_ELIMINAR_TABLA = new StringBuilder("DROP TABLE IF EXISTS ").append(TABLA_OBRA).append(";").toString();

        public static final String SQL_INSERTAR_DATOS_INICIALES = new StringBuilder("INSERT INTO ").append(TABLA_OBRA). append("(" + _ID + ", " + COLUMNA_FECHA_CONTRATO+", " + COLUMNA_METROS_CUADRADOS +", "+ COLUMNA_NOMBRE_CLIENTE+", "+COLUMNA_DIRECCION+", "+COLUMNA_FOTO+") VALUES ")
                .append("(NULL, '2017-11-21', 1000, 'Pepe' , 'Yi 123', 'rutaFoto'),")
                .append("(NULL, 2017-11-21, 750, 'Roberto Gomez', '21 Jump Street', 'fotoRuta'),")
                .append("(NULL, 2017-11-21, 800, 'Mary Curie', 'LaCalle Pou', 'fotoRuta');").toString();

    }

    public static abstract class Materiales {

        public static final String COLUMNA_STOCK = "Stock";
        public static final String COLUMNA_NOMBRE = "Nombre";
        public static final String COLUMNA_FECHA_ALTA = "FechaAlta";
        public static final String COLUMNA_DESCRIPCION = "Descripcion";
        public static final String COLUMNA_OBRA = "Obra";

        public static final String[] COLUMNAS = { COLUMNA_STOCK, COLUMNA_NOMBRE, COLUMNA_FECHA_ALTA, COLUMNA_DESCRIPCION, COLUMNA_OBRA};

        public static final String SQL_CREAR_TABLA = new StringBuilder("CREATE TABLE ").append(TABLA_MATERIAL).append(" (")
                .append(COLUMNA_STOCK).append(" INTEGER NOT NULL, ")
                .append(COLUMNA_NOMBRE).append(" TEXT NOT NULL, ")
                .append(COLUMNA_FECHA_ALTA).append(" TEXT NOT NULL, ")
                .append(COLUMNA_DESCRIPCION).append(" TEXT NOT NULL, ")
                .append(COLUMNA_OBRA).append(" INTEGER NOT NULL, ")
                .append("FOREIGN KEY (").append(COLUMNA_OBRA + ")").append(" REFERENCES " + TABLA_OBRA + "("+ Obras._ID +"), ")
                .append("PRIMARY KEY ("+ COLUMNA_NOMBRE +", "+ COLUMNA_OBRA +"));").toString();

        public static final String SQL_ELIMINAR_TABLA = new StringBuilder("DROP TABLE IF EXISTS ").append(TABLA_MATERIAL).append(";").toString();

    }

    public static abstract class Movimientos implements BaseColumns{

        public static final String COLUMNA_CANTIDAD = "Cantidad";
        public static final String COLUMNA_OBSERVACION = "Observacion";
        public static final String COLUMNA_MATERIAL = "Material";
        public static final String COLUMNA_OBRA = "Obra";
        public static final String COLUMNA_FECHA_MOVIMIENTO = "FechaMovimiento";

        public static final String[] COLUMNAS = {_ID, COLUMNA_CANTIDAD, COLUMNA_OBSERVACION, COLUMNA_MATERIAL, COLUMNA_OBRA, COLUMNA_FECHA_MOVIMIENTO};

        public static final String SQL_CREAR_TABLA = new StringBuilder("CREATE TABLE ").append(TABLA_MOVIMIENTO).append(" (")
                .append(_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMNA_CANTIDAD).append(" INTEGER NOT NULL, ")
                .append(COLUMNA_OBSERVACION).append(" TEXT NOT NULL, ")
                .append(COLUMNA_MATERIAL).append(" INTEGER NOT NULL, ")
                .append(COLUMNA_OBRA).append(" INTEGER NOT NULL, ")
                .append(COLUMNA_FECHA_MOVIMIENTO).append(" TEXT NOT NULL, ")
                .append("FOREIGN KEY (").append(COLUMNA_MATERIAL + ")").append(" REFERENCES " + TABLA_MATERIAL + "("+ Materiales.COLUMNA_NOMBRE +"), ")
                .append("FOREIGN KEY (").append(COLUMNA_OBRA + ")").append(" REFERENCES " + TABLA_MATERIAL + "("+ Materiales.COLUMNA_OBRA + "));").toString();


        public static final String SQL_ELIMINAR_TABLA = new StringBuilder("DROP TABLE IF EXISTS ").append(TABLA_MATERIAL).append(";").toString();

    }

}
