package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Registrate(View view){
        Intent registrarse = new Intent(this, registro.class);
        startActivity(registrarse);
    }

    public void ingreso(View view){
        Intent loguin = new Intent(this, inicioSesion.class);
        startActivity(loguin);
    }
}