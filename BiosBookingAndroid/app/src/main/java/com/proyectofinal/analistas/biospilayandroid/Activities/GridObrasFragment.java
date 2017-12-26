package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades.AdaptadorObras;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMovimiento;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.Logica.Obra;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GridObrasFragment extends Fragment {

    public static final String MIS_PREFERENCIAS = "MIS_PREFERENCIAS";
    public static final String PREFERENCIA_INICIALES = "PREFERENCIA_INICIALES";

    private BDHelper helper;
    private SQLiteDatabase baseDatos;
    protected SharedPreferences preferencias;
    private boolean datosInicialesIngresados;

    private OnObraSeleccionadaListener listener;
    protected GridView gvObras;

    public static GridObrasFragment getInstance() {
        return new GridObrasFragment();
    }


    public GridObrasFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnObraSeleccionadaListener) {
            listener = (OnObraSeleccionadaListener)context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_grid_obras, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        try{

            super.onActivityCreated(savedInstanceState);

            helper = new BDHelper(getActivity());
            baseDatos = helper.getWritableDatabase();

            ControladorMaterial controlador = new ControladorMaterial();

            gvObras=(GridView)getView().findViewById(R.id.gvObras);

            boolean datosIniciados = false;

            preferencias = getActivity().getSharedPreferences(MIS_PREFERENCIAS, Context.MODE_PRIVATE);

            datosIniciados = preferencias.getBoolean(PREFERENCIA_INICIALES, false);

            ControladorGral.setControlMovimientos(new ControladorMovimiento());
            ControladorGral.setControlMateriales(new ControladorMaterial());

            if(!datosIniciados){
                controlador.cargarDatosIniciales(baseDatos);

                ControladorGral.actualizarRepositorio(baseDatos);

                datosInicialesIngresados = true;

                guardarIniciados();
            }

            ControladorGral.actualizarRepositorio(baseDatos);

            AdaptadorObras adaptadorObras = new AdaptadorObras(getActivity(), ControladorGral.getControlMateriales().getObras());

            gvObras.setAdapter(adaptadorObras);

            gvObras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    gvObrasOnItemClick(parent, view, position, id);
                }
            });

        }catch(Exception ex){
            Toast.makeText(getActivity(), "ERROR: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    protected void gvObrasOnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.OnObraSeleccionada((DtObra)parent.getItemAtPosition(position));
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public interface OnObraSeleccionadaListener {

        void OnObraSeleccionada(DtObra obra);
    }

    protected void guardarIniciados() {
        SharedPreferences.Editor editorPreferencias = preferencias.edit();
        editorPreferencias.putBoolean(PREFERENCIA_INICIALES, datosInicialesIngresados);
        editorPreferencias.apply();
    }
}
