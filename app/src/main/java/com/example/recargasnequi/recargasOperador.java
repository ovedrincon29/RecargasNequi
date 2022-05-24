package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class recargasOperador extends AppCompatActivity {
    private String telSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recargas_operador);


    }

    public void inicioHome(View view){

        Intent intent= new Intent(this, recargas.class);
        startActivity(intent);
        finish();
    }

    public void irAOperadores(View view){

        Intent intent= new Intent(this, operadores.class);
        telSesion = getIntent().getStringExtra("cel");
        intent.putExtra("cel", telSesion);
        startActivity(intent);
        finish();
    }

    public void irPerfil(View view){

        Intent intent= new Intent(this, perfil.class);
        startActivity(intent);
        finish();
    }

    public void movimientos(View view){

        Intent intent= new Intent(this, movimientoDeRecargas.class);
        startActivity(intent);
        finish();
    }
}