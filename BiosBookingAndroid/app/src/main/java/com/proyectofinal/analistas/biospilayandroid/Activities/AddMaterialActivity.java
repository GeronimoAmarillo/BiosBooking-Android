package com.proyectofinal.analistas.biospilayandroid.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.proyectofinal.analistas.biospilayandroid.Logica.ControladorMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DTMaterial;
import com.proyectofinal.analistas.biospilayandroid.Logica.DtObra;
import com.proyectofinal.analistas.biospilayandroid.Persistencia.BDHelper;
import com.proyectofinal.analistas.biospilayandroid.R;

import org.w3c.dom.Text;

public class AddMaterialActivity extends AppCompatActivity {

    TextView tvObra;
    EditText etNombreMaterial;
    EditText etDescripcion;
    EditText etStock;
    Button btnAgregarMaterial;

    ControladorMaterial controlador;

    DtObra obra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        tvObra = (TextView)findViewById(R.id.tvObra);
        etNombreMaterial = (EditText)findViewById(R.id.etNombreMaterial);
        etDescripcion = (EditText)findViewById(R.id.etDescripcion);
        etStock = (EditText)findViewById(R.id.etStock);
        btnAgregarMaterial = (Button)findViewById(R.id.btnAgregarMaterial);

        controlador = new ControladorMaterial();

        Bundle extras = getIntent().getExtras();
        obra = (DtObra)extras.getSerializable("Obra");

        tvObra.setText(String.valueOf(obra.getIdObra()));

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

        boolean exito = false;

        exito = controlador.AltaMaterial(materialAagregar, obra.getIdObra(), bd);

        Intent intencion = new Intent(getApplicationContext(), MaterialListActivity.class);

        if(exito){
            intencion.putExtra("MENSAJE", "Se realizo el alta de material exitosamente!.");
        }else{
            intencion.putExtra("MENSAJE", "Se produjo un error al intentar dar de alta el material!.");
        }

        intencion.putExtra("Obra", obra);

        startActivity(intencion);
    }
}
