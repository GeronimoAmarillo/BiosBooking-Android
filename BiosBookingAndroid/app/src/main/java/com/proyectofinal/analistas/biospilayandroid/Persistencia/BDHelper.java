package com.proyectofinal.analistas.biospilayandroid.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Geronimo on 03/12/2017.
 */

public class BDHelper extends SQLiteOpenHelper {

    private Context contexto;


    public BDHelper(Context contexto) {
        super(contexto, BDContract.NOMBRE_BASE_DATOS, null, BDContract.VERSION_BASE_DATOS);

        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BDContract.Obras.SQL_CREAR_TABLA);

        db.execSQL(BDContract.Obras.SQL_INSERTAR_DATOS_INICIALES);

        db.execSQL(BDContract.Materiales.SQL_CREAR_TABLA);

        db.execSQL(BDContract.Movimientos.SQL_CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
