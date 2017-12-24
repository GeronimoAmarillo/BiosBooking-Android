package com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geronimo on 16/12/2017.
 */

public class AdaptadorObras extends BaseAdapter {

    private Context contexto;
    private List<DtObra> obras;


    public AdaptadorObras(Context contexto, List<DtObra> obras){
        this.contexto = contexto;
        this.obras = obras;
    }

    @Override
    public int getCount() {
        return obras.size();
    }

    @Override
    public Object getItem(int posicion) {
        return obras.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ObraViewHolder obraViewHolder;

        if (item == null) {
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_obra, parent, false);

            obraViewHolder = new ObraViewHolder(item);
            item.setTag(obraViewHolder);
        } else {
            obraViewHolder = (ObraViewHolder) item.getTag();
        }

        obraViewHolder.enlazarEmpleado(obras.get(position));

        return item;
    }

    protected class ObraViewHolder {

        private TextView tvIdObra;
        private TextView tvDireccion;
        private ImageView ivFoto;



        public ObraViewHolder(View vista) {
            tvIdObra = (TextView) vista.findViewById(R.id.tvIdObra);
            tvDireccion = (TextView)vista.findViewById(R.id.tvDireccionObra);
            ivFoto = (ImageView)vista.findViewById(R.id.ivFotoObra);

        }

        public void enlazarEmpleado(DtObra obra) {

            tvIdObra.setText(String.valueOf(obra.getIdObra()));
            tvDireccion.setText(obra.getDireccion());
            Bitmap imagen = (((BitmapDrawable)ContextCompat.getDrawable(contexto, Integer.parseInt(obra.getFoto()))).getBitmap());

            ivFoto.setImageBitmap(imagen);


        }

    }
}
