package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class perfil extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvTelefono;
    private String telSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre= findViewById(R.id.nombrePerfil);
        tvTelefono= findViewById(R.id.telefonoPerfil);

        telSesion = getIntent().getStringExtra("cel");

        //Consulta de nombre
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor nom = BaseDeDatos.rawQuery("Select nombres from usuarios where phone= " +telSesion, null);
        Cursor tel = BaseDeDatos.rawQuery("Select phone from usuarios where phone= " +telSesion, null);

        nom.moveToFirst();
        String capturaNombre= nom.getString(0);
        tvNombre.setText(capturaNombre);

        tel.moveToFirst();
        String capturaTelefono= tel.getString(0);
        tvTelefono.setText(capturaTelefono);
    }

    public void recargar(View view){

        Intent intent= new Intent(this, recargasOperador.class);
        startActivity(intent);
        finish();
    }
    public void movimientos(View view){
        Intent intent= new Intent(this, movimientoDeRecargas.class);
        startActivity(intent);
        finish();
    }
    public void inicioHome(View view){

        Intent intent= new Intent(this, recargas.class);
        startActivity(intent);
        finish();
    }
    public void salir(View view){

        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}