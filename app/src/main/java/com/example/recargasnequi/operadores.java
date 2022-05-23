package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class operadores extends AppCompatActivity {

    private String telSesion;

    private EditText celRecarga, precioRecarga;
    private Spinner operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operadores);

        telSesion = getIntent().getStringExtra("cel");

        celRecarga = findViewById(R.id.numCelularARecargar);
        precioRecarga = findViewById(R.id.valorARecargar);
        operador = findViewById(R.id.spinnerOperadores);

        String[] opcionesOperador = {"Claro", "Movistar", "WOM", "Flash Mobile", "Avantel", "Tigo", "Virgin", "ETB"};

        //Conecto el arreglo con el spinner del xml
        ArrayAdapter<String> conexAlSpinner = new ArrayAdapter<String>(this, R.layout.spinner_item_estilo, opcionesOperador);
        operador.setAdapter(conexAlSpinner);
    }

    //Método del botón recarga
    public void hacerRecarga(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String telefonorecarga = celRecarga.getText().toString();
        String valorrecargado = precioRecarga.getText().toString();

        int enteroRecarga = Integer.parseInt(valorrecargado);
        String seleccionDeOperador = operador.getSelectedItem().toString();




        ContentValues registroMovimientos = new ContentValues();
        registroMovimientos.put("telefonorecarga", telefonorecarga);
        registroMovimientos.put("valorrecargado", enteroRecarga);
        registroMovimientos.put("operador", seleccionDeOperador);
        registroMovimientos.put("phone", telSesion );

        BaseDeDatos.insert("registrosmovimientos", null, registroMovimientos);
        BaseDeDatos.close();

        celRecarga.setText("");
        precioRecarga.setText("");

        Toast.makeText(this, "Recargar Exitosa", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, recargasOperador.class);
        intent.putExtra("valorRecarga", enteroRecarga);
        startActivity(intent);

    }
    public void recargar(View view){

        Intent intent= new Intent(this, recargasOperador.class);
        startActivity(intent);
    }
    public void irPerfil(View view){

        Intent intent= new Intent(this, perfil.class);
        startActivity(intent);
    }
    public void movimientos(View view){
        Intent intent= new Intent(this, movimientoDeRecargas.class);
        startActivity(intent);
    }
    public void inicioHome(View view){

        Intent intent= new Intent(this, recargas.class);
        startActivity(intent);
    }
}