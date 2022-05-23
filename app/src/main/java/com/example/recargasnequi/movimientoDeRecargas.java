package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class movimientoDeRecargas extends AppCompatActivity {

    private String nombreTelefono, nombreSaldo, nombreOperador;

    private TextView tvcelRecargado, tvValorRecargado, tvOperadorRecargado;
    private String telSesion, capturaTelefono, capturaValor, capturaOperador;

    public movimientoDeRecargas(String datoSaldo, String datoTelefono, String datoOperador){

        this.nombreTelefono= datoTelefono;
        this.nombreSaldo= datoSaldo;
        this.nombreOperador= datoOperador;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_de_recargas);


        tvcelRecargado = findViewById(R.id.tvtelefono);
        tvValorRecargado = findViewById(R.id.tvrecarga);
        tvOperadorRecargado = findViewById(R.id.tvoperador);

        telSesion = getIntent().getStringExtra("cel");

        //Consulta de nombre
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor valor = BaseDeDatos.rawQuery("Select * from registrosmovimientos where phone=" + telSesion, null);

        valor.moveToFirst();
        capturaTelefono = valor.getString(1);
        capturaValor = valor.getString(2);
        capturaOperador = valor.getString(3);

        tvcelRecargado.setText(capturaTelefono);
        tvValorRecargado.setText(capturaValor);
        tvOperadorRecargado.setText(capturaOperador);
    }

    public void recargar(View view) {

        Intent irARecargar = new Intent(this, recargasOperador.class);
        startActivity(irARecargar);
        finish();
    }

    public void irPerfil(View view) {

        Intent irAlPerfil = new Intent(this, perfil.class);
        startActivity(irAlPerfil);
        finish();
    }

    public void inicioHome(View view) {

        Intent irAInicio = new Intent(this, recargas.class);
        startActivity(irAInicio);
    }
}