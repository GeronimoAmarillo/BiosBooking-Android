package com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Geronimo on 19/12/2017.
 */

public class AdaptadorMateriales extends BaseAdapter {

    private Context contexto;
    private List<DTMaterial> materiales;
    private int idObra;


    public AdaptadorMateriales(Context contexto, List<DTMaterial> materiales, int idObra){
        this.contexto = contexto;
        this.materiales = materiales;
        this.idObra = idObra;
    }

    @Override
    public int getCount() {
        return materiales.size();
    }

    @Override
    public Object getItem(int posicion) {
        return materiales.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        AdaptadorMateriales.MaterialViewHolder materialViewHolder;

        if (item == null) {
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_material, parent, false);

            materialViewHolder = new AdaptadorMateriales.MaterialViewHolder(item);
            item.setTag(materialViewHolder);
        } else {
            materialViewHolder = (AdaptadorMateriales.MaterialViewHolder) item.getTag();
        }

        materialViewHolder.enlazarMaterial(materiales.get(position), idObra);

        return item;
    }

    protected class MaterialViewHolder {

        private TextView tvIdObra;
        private TextView tvMaterial;
        private TextView tvStock;


        public MaterialViewHolder(View vista) {
            tvIdObra = (TextView) vista.findViewById(R.id.tvIdObra);
            tvMaterial = (TextView)vista.findViewById(R.id.tvNombreMaterial);
            tvStock = (TextView)vista.findViewById(R.id.tvStock);
        }

        public void enlazarMaterial(DTMaterial material, int idObra) {

            tvIdObra.setText(String.valueOf(idObra));
            tvMaterial.setText(material.getNombre());
            tvStock.setText(String.valueOf(material.getStock()));
        }

    }
}
