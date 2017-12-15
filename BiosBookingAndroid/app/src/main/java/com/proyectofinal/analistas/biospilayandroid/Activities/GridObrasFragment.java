package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMaterial;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDContract;
import com.proyectofinal.analistas.biospilayandroid.R;



public class GridObrasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private SimpleCursorAdapter adaptadorObras;


    protected GridView lvObras;

    public GridObrasFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_grid_obras, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ControladorMaterial controlador = new ControladorMaterial();

        lvObras=(GridView)getView().findViewById(R.id.gvObras);

        adaptadorObras = new SimpleCursorAdapter(this, R.layout.fragment_grid_obras,controlador.listarObras(), BDContract.Obras.COLUMNAS, new int[] { R.id.tvIdObra, R.id.,R.id.tvDireccionObra,}, 0);
        lvObras.setAdapter(adaptadorObras);




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
