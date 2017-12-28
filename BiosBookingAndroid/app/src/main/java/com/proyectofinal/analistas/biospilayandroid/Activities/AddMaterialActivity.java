package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorGral;
import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;
import com.proyectofinal.analistas.biospilayandroid.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMaterialActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String TAG_SELECTOR_FECHA = "TAG_SELECTOR_FECHA";
    public static final String TAG_SELECTOR_HORA = "TAG_SELECTOR_HORA";


    protected EditText etFecha;
    protected EditText etHora;

    TextView tvObra;
    EditText etNombreMaterial;
    EditText etDescripcion;
    EditText etStock;
    Button btnAgregarMaterial;
    FloatingActionButton btnSalir;

    ControladorMaterial controlador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            setContentView(R.layout.activity_add_material);

            tvObra = (TextView)findViewById(R.id.tvObra);
            etNombreMaterial = (EditText)findViewById(R.id.etNombreMaterial);
            etDescripcion = (EditText)findViewById(R.id.etDescripcion);
            etStock = (EditText)findViewById(R.id.etStock);
            btnAgregarMaterial = (Button)findViewById(R.id.btnAgregarMaterial);
            etFecha = (EditText)findViewById(R.id.etFecha);
            etHora = (EditText)findViewById(R.id.etHora);
            btnSalir = (FloatingActionButton)findViewById(R.id.btnSalir);

            controlador = new ControladorMaterial();

            Bundle extras = getIntent().getExtras();

            tvObra.setText(ControladorGral.getObraSeleccionada().getDireccion());

            btnAgregarMaterial.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    onAgregarMaterialClick(view);
                }
            });

            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSalirOnClick(view);
                }
            });

        }catch(Exception ex){
            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void onAgregarMaterialClick(View view) {

        try{

            BDHelper helper = new BDHelper(this);
            SQLiteDatabase bd = helper.getWritableDatabase();

            DTMaterial materialAagregar = new DTMaterial();

            if(etNombreMaterial.getText().toString().length() < 1){
                throw new Exception("Debe ingresar el nombre del material");
            }else{
                materialAagregar.setNombre(etNombreMaterial.getText().toString());
            }

            materialAagregar.setDescripcion(etDescripcion.getText().toString());

            try{

                int stock = 0;

                if(!etStock.getText().toString().isEmpty()){
                    stock = Integer.parseInt(etStock.getText().toString());
                }

                materialAagregar.setStock(stock);

            }catch (Exception ex){
                throw new Exception("Error al convertir el stock ingresado.");
            }

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String fechaTexto = etFecha.getText().toString() + " " + etHora.getText().toString() + ":00";

            Date fecha = null;
            try {
                fecha = formatoFecha.parse(fechaTexto);
            } catch (ParseException e) {
                throw new Exception("La fecha no fue ingresada o no tiene el formato correcto.");
            }

            materialAagregar.setFechaAlta(fecha);

            boolean exito = false;

            exito = controlador.AltaMaterial(materialAagregar, ControladorGral.getObraSeleccionada().getIdObra(), bd);

            Intent intencion = new Intent(getApplicationContext(), MaterialListActivity.class);

            if(exito){
                intencion.putExtra("MENSAJE", "Se realizo el alta de material exitosamente!.");
            }else{
                intencion.putExtra("MENSAJE", "Se produjo un error al intentar dar de alta el material!.");
            }

            ControladorGral.actualizarRepositorio(bd);

            startActivity(intencion);

        }catch(Exception ex){

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }



    public void btnElegirFechaOnClick(View v) {
        SelectorFechaDialog dialogoFecha = new SelectorFechaDialog();
        dialogoFecha.show(getSupportFragmentManager(), TAG_SELECTOR_FECHA);
    }

    public void btnElegirHoraOnClick(View v) {
        SelectorHoraDialog dialogoHora = new SelectorHoraDialog();
        dialogoHora.show(getSupportFragmentManager(), TAG_SELECTOR_HORA);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String fechaTexto = String.format("%1$02d", dayOfMonth) + "/" + String.format("%1$02d", (month + 1)) + "/" + year + " 00:00:00";

        Date fechaElejida = null;
        try {

            fechaElejida = formato.parse(fechaTexto);

        } catch (Exception ex) {

            Toast.makeText(this, "ERROR: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

        Date fechaActual = new Date();

        if(fechaElejida.getTime() > fechaActual.getTime()){

            Toast.makeText(this, "ERROR: No puede seleccionar una fecha posterior a hoy.", Toast.LENGTH_SHORT).show();

        }else{

            etFecha.setText(String.format("%1$02d", dayOfMonth) + "/" + String.format("%1$02d", (month + 1)) + "/" + year);

        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        etHora.setText(String.format("%1$02d", hourOfDay) + ":" + String.format("%1$02d", minute));
    }

    public static class SelectorFechaDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            try{

                Calendar calendario = Calendar.getInstance();
                int anio = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(), anio, mes, dia);

                return picker;

            }catch(Exception ex){
                Toast.makeText(getActivity(), "ERROR: Se produjo un error al generar la fecha", Toast.LENGTH_LONG).show();
                return null;
            }


        }

    }

    public static class SelectorHoraDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            try{

                Calendar calendario = Calendar.getInstance();
                int hora = calendario.get(Calendar.HOUR_OF_DAY);
                int minutos = calendario.get(Calendar.MINUTE);

                return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(), hora, minutos, true);

            }catch(Exception ex){
                Toast.makeText(getActivity(), "ERROR: Se produjo un error al generar la hora", Toast.LENGTH_LONG).show();
                return null;
            }


        }

    }

    public void btnSalirOnClick(View v){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
