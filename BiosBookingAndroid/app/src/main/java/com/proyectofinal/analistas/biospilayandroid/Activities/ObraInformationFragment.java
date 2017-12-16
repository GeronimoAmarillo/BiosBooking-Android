package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;


public class ObraInformationFragment extends Fragment {

    public static ObraInformationFragment getInstance() {
        return new ObraInformationFragment();
    }


    protected TextView tvIdObra;
    protected TextView tvNombreDueño;
    protected TextView tvDireccion;
    protected ImageView ivFotoObra;


    public ObraInformationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_obra_information, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvIdObra = (TextView)getView().findViewById(R.id.tvIdObra);
        tvNombreDueño = (TextView)getView().findViewById(R.id.tvDueñoObra);
        tvDireccion = (TextView)getView().findViewById(R.id.tvDireccionObra);
    }

    public void mostrarObra(DtObra obra) {
        tvIdObra.setText(String.valueOf(obra.getIdObra()));
        tvNombreDueño.setText(obra.getNombreCliente());
        tvDireccion.setText(obra.getDireccion());
    }
}
