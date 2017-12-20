package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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



    public MaterialInfoFragment() {

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



        lvMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lvMovimientosOnItemClick(adapterView, view, i, l);
            }
        });
    }

    public void mostrarMaterial(DTMaterial material, int idObra) {

        tvNombreMaterial.setText(material.getNombre());
        tvStock.setText(String.valueOf(material.getStock()));
        tvObra.setText(idObra);


    }

    public void onClickMateriales(View v){
        /*Intent intencion = new Intent(getActivity().getApplicationContext(), MaterialInformationActivity.class);

        intencion.putExtra("Obra", );

        startActivity(intencion);*/
    }

    protected void lvMovimientosOnItemClick(AdapterView<?> adapterView, View view, int i, long l){

    }

}
