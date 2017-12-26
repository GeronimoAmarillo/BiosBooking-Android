package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades.AdaptadorMateriales;
import com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades.AdaptadorObras;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.Logica.Material;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class MaterialesListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    protected DtObra obra;

    private MaterialesListFragment.OnMaterialSeleccionadoListener listener;
    protected ListView lvMateriales;
    protected FloatingActionButton btnAgregarMaterial;

    protected Spinner spFiltro;


    AdaptadorMateriales adaptador;

    public static final String ESTADO_DISPONIBLE = "c/Disponibilidad";
    public static final String ESTADO_NO_DISPONIBLE = "s/Disponibilidad";
    public static final String TODOS = "Todos";



    public static GridObrasFragment getInstance() {
        return new GridObrasFragment();
    }


    public MaterialesListFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MaterialesListFragment.OnMaterialSeleccionadoListener) {
            listener = (MaterialesListFragment.OnMaterialSeleccionadoListener)context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_materiales_list, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try{

            lvMateriales=(ListView) getView().findViewById(R.id.lvMateriales);
            btnAgregarMaterial = (FloatingActionButton) getView().findViewById(R.id.btnAgregarMaterial);

            spFiltro = (Spinner)getView().findViewById(R.id.spFiltro);

            String[] filtros = { ESTADO_DISPONIBLE, ESTADO_NO_DISPONIBLE, TODOS };

            ArrayAdapter<String> adaptadorFiltros = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, filtros);
            adaptadorFiltros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spFiltro.setAdapter(adaptadorFiltros);

            spFiltro.setOnItemSelectedListener(this);

            btnAgregarMaterial.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    onAgregarMaterialClick(view);
                }
            });

        }catch(Exception ex){

            Toast.makeText(getActivity(), "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }



    }

    private void onAgregarMaterialClick(View view) {
        try{

            Intent intencion = new Intent(getActivity(), AddMaterialActivity.class);

            intencion.putExtra("Obra", obra);

            startActivity(intencion);

        }catch(Exception ex){

            Toast.makeText(getActivity(), "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    protected void lvMaterialesOnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.onMaterialSeleccionado((DTMaterial) parent.getItemAtPosition(position));
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public interface OnMaterialSeleccionadoListener {

        void onMaterialSeleccionado(DTMaterial material);
    }

    protected void listarMateriales(){
        try{

            final DtObra obraSeleccionada = ControladorGral.getObraSeleccionada();

            adaptador = new AdaptadorMateriales(getActivity(), obraSeleccionada.getMateriales(), obraSeleccionada.getIdObra());
            lvMateriales.setAdapter(adaptador);

            this.obra = obraSeleccionada;

            lvMateriales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (listener != null) {
                        listener.onMaterialSeleccionado((DTMaterial) parent.getItemAtPosition(position));
                    }
                }
            });

        }catch(Exception ex){

            Toast.makeText(getActivity(), "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        try{

            adaptador = new AdaptadorMateriales(getActivity(), ControladorGral.ListaFiltrada(parent, view, position, id), ControladorGral.getObraSeleccionada().getIdObra());
            lvMateriales.setAdapter(adaptador);

        }catch(Exception ex){

            Toast.makeText(getActivity(), "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
