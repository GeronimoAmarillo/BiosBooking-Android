package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

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

    ControladorMaterial controlador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        tvObra = (TextView)findViewById(R.id.tvObra);
        etNombreMaterial = (EditText)findViewById(R.id.etNombreMaterial);
        etDescripcion = (EditText)findViewById(R.id.etDescripcion);
        etStock = (EditText)findViewById(R.id.etStock);
        btnAgregarMaterial = (Button)findViewById(R.id.btnAgregarMaterial);
        etFecha = (EditText)findViewById(R.id.etFecha);
        etHora = (EditText)findViewById(R.id.etHora);

        controlador = new ControladorMaterial();

        Bundle extras = getIntent().getExtras();

        tvObra.setText(String.valueOf(ControladorGral.getObraSeleccionada().getIdObra()));

        btnAgregarMaterial.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onAgregarMaterialClick(view);
            }
        });
    }

    private void onAgregarMaterialClick(View view) {

        BDHelper helper = new BDHelper(this);
        SQLiteDatabase bd = helper.getWritableDatabase();

        DTMaterial materialAagregar = new DTMaterial();

        materialAagregar.setNombre(etNombreMaterial.getText().toString());
        materialAagregar.setDescripcion(etDescripcion.getText().toString());
        materialAagregar.setStock(Integer.parseInt(etStock.getText().toString()));

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String fechaTexto = etFecha.getText().toString() + " " + etHora.getText().toString();

        Date fecha = null;
        try {
            fecha = formatoFecha.parse(fechaTexto);
        } catch (ParseException e) {
            e.printStackTrace();
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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        etFecha.setText(String.format("%1$02d", dayOfMonth) + "/" + String.format("%1$02d", (month + 1)) + "/" + year);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        etHora.setText(String.format("%1$02d", hourOfDay) + ":" + String.format("%1$02d", minute));
    }

    public static class SelectorFechaDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendario = Calendar.getInstance();
            int anio = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(), anio, mes, dia);
        }

    }

    public static class SelectorHoraDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendario = Calendar.getInstance();
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(), hora, minutos, true);
        }

    }
}
