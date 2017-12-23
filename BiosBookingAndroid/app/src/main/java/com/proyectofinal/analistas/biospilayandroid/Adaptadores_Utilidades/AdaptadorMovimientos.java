package com.proyectofinal.analistas.biospilayandroid.Adaptadores_Utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMovimiento;
import com.proyectofinal.analistas.biospilayandroid.Logica.Movimiento;
import com.proyectofinal.analistas.biospilayandroid.R;

import java.util.List;

/**
 * Created by Geronimo on 22/12/2017.
 */

public class AdaptadorMovimientos extends BaseAdapter {
    private Context contexto;
    private List<DTMovimiento> movimientos;


    public AdaptadorMovimientos(Context contexto, List<DTMovimiento> movimientos){
        this.contexto = contexto;
        this.movimientos = movimientos;
    }

    @Override
    public int getCount() {
        return movimientos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return movimientos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        AdaptadorMovimientos.MovimientoViewHolder movimientoViewHolder;

        if (item == null) {
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_movimiento, parent, false);

            movimientoViewHolder = new AdaptadorMovimientos.MovimientoViewHolder(item);
            item.setTag(movimientoViewHolder);
        } else {
            movimientoViewHolder = (AdaptadorMovimientos.MovimientoViewHolder) item.getTag();
        }

        movimientoViewHolder.enlazarMovimiento(movimientos.get(position));

        return item;
    }

    protected class MovimientoViewHolder {

        private TextView tvCantidad;
        private TextView tvObservacion;


        public MovimientoViewHolder(View vista) {
            tvCantidad = (TextView) vista.findViewById(R.id.tvCantidad);
            tvObservacion = (TextView)vista.findViewById(R.id.tvObservacion);
        }

        public void enlazarMovimiento(DTMovimiento movimiento) {

            tvCantidad.setText(String.valueOf(movimiento.getCantidad()));
            tvObservacion.setText(movimiento.getObservacion());
        }
    }
}
