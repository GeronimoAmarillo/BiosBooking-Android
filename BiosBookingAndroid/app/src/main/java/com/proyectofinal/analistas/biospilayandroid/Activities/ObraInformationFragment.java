package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.text.SimpleDateFormat;


public class ObraInformationFragment extends Fragment {

    public static ObraInformationFragment getInstance() {
        return new ObraInformationFragment();
    }


    protected TextView tvIdObra;
    protected TextView tvNombreDueño;
    protected TextView tvDireccion;
    protected Button btnMateriales;
    private ImageView ivFoto;
    protected ImageView ivFotoObra;
    private DtObra obra;
    private TextView tvMetros;
    private TextView tvFecha;

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
        btnMateriales = (Button)getView().findViewById(R.id.btnVerMateriales);
        tvMetros = (TextView)getView().findViewById(R.id.tvMetrosCuadrados);
        tvFecha = (TextView)getView().findViewById(R.id.tvFechaContrato);
        ivFoto = (ImageView)getView().findViewById(R.id.ivFotoObra);

        btnMateriales.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onClickMateriales(view);
            }
        });
    }

    public void mostrarObra() {

        tvIdObra.setText("Identificador: " + String.valueOf(ControladorGral.getObraSeleccionada().getIdObra()));
        tvNombreDueño.setText("Dueño: " + ControladorGral.getObraSeleccionada().getNombreCliente());
        tvDireccion.setText("Direccion: " + ControladorGral.getObraSeleccionada().getDireccion());
        Bitmap imagen = (((BitmapDrawable) ContextCompat.getDrawable(getActivity(), Integer.parseInt(ControladorGral.getObraSeleccionada().getFoto()))).getBitmap());

        ivFoto.setImageBitmap(imagen);

        tvMetros.setText("Superficie: " + String.valueOf(ControladorGral.getObraSeleccionada().getMetrosCuadrados()) + " mts2");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String fecha = formato.format(ControladorGral.getObraSeleccionada().getFechadeContrato());

        tvFecha.setText("Fecha de contrato: " + fecha);

        this.obra = ControladorGral.getObraSeleccionada();
    }

    public void onClickMateriales(View v){
        Intent intencion = new Intent(getActivity().getApplicationContext(), MaterialListActivity.class);

        startActivity(intencion);
    }
}
