package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofinal.analistas.biospilayandroid.R;


public class MaterialesListFragment extends Fragment {

    public MaterialesListFragment() {
    }

    public static MaterialesListFragment newInstance(String param1, String param2) {
        MaterialesListFragment fragment = new MaterialesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_materiales_list, container, false);
    }
}
