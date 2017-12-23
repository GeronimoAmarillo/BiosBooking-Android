package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades.AdaptadorMovimientos;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;


public class MaterialInfoFragment extends Fragment {

    public static MaterialInfoFragment getInstance() {
        return new MaterialInfoFragment();
    }


    protected TextView tvNombreMaterial;
    protected TextView tvStock;
    protected TextView tvObra;
    protected ListView lvMovimientos;
    protected FloatingActionButton btnAgregarMovimiento;

    protected DTMaterial material;

    private MaterialInfoFragment.OnMaterialSeleccionadoListener listener;


    public MaterialInfoFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof GridObrasFragment.OnObraSeleccionadaListener) {
            listener = (MaterialInfoFragment.OnMaterialSeleccionadoListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material_info_and_movement, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvNombreMaterial = (TextView)getView().findViewById(R.id.tvNombreMaterial);
        tvStock= (TextView)getView().findViewById(R.id.tvStock);
        tvObra = (TextView)getView().findViewById(R.id.tvObra);
        lvMovimientos = (ListView) getView().findViewById(R.id.lvMovimientos);


        btnAgregarMovimiento = (FloatingActionButton)getView().findViewById(R.id.btnAgregarMovimiento);

        btnAgregarMovimiento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onAgregarMovimientoClick(view);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public void mostrarMaterial(DTMaterial material) {

        tvNombreMaterial.setText(ControladorGral.getMaterialSeleccionado().getNombre());
        tvStock.setText(String.valueOf(ControladorGral.getMaterialSeleccionado().getStock()));
        tvObra.setText(String.valueOf(ControladorGral.getObraSeleccionada().getIdObra()));

        AdaptadorMovimientos adaptadorMovimientos = new AdaptadorMovimientos(getActivity(), ControladorGral.getMaterialSeleccionado().getMovimientos());
        lvMovimientos.setAdapter(adaptadorMovimientos);

        this.material = material;
    }

    protected void onAgregarMovimientoClick(View view){

        Intent intencion = new Intent(getActivity(), MovimientosActivity.class);

        startActivity(intencion);

    }

    public interface OnMaterialSeleccionadoListener {

        void OnMaterialSeleccionado(DtObra obra);

    }

}
