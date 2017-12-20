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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades.AdaptadorMateriales;
import com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades.AdaptadorObras;
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


public class MaterialesListFragment extends Fragment {

    protected DtObra obra;

    private MaterialesListFragment.OnMaterialSeleccionadoListener listener;
    protected ListView lvMateriales;


    AdaptadorMateriales adaptador;


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

        lvMateriales=(ListView) getView().findViewById(R.id.lvMateriales);

    }

    protected void lvMaterialesOnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.onMaterialSeleccionado((DTMaterial) parent.getItemAtPosition(position), obra.getIdObra());
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public interface OnMaterialSeleccionadoListener {

        void onMaterialSeleccionado(DTMaterial material, int idObra);
    }

    protected void listarMateriales(DtObra obra){

        adaptador = new AdaptadorMateriales(getActivity(), obra.getMateriales(), obra.getIdObra());
        lvMateriales.setAdapter(adaptador);

        lvMateriales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvMaterialesOnItemClick(parent, view, position, id);
            }
        });
    }
}
