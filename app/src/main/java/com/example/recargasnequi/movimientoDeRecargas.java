package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class movimientoDeRecargas extends AppCompatActivity {

    //private ListView lvMovimientosRecargas;
    //private String nombreTelefono, nombreSaldo, nombreOperador;

    private TextView tvcelRecargado, tvValorRecargado, tvOperadorRecargado;
    private String telSesion, capturaTelefono, capturaValor, capturaOperador;

    //ArrayList<String> listaMovimientos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_de_recargas);

       // lvMovimientosRecargas=(ListView) findViewById(R.id.lvMovimientos);
        tvcelRecargado = findViewById(R.id.tvtelefono);
        tvValorRecargado = findViewById(R.id.tvrecarga);
        tvOperadorRecargado = findViewById(R.id.tvoperador);


        //consultaListaMovimientos();

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

    /*private void consultaListaMovimientos() {
        SQLiteDatabase BaseDeDAtos=admin.getReadableDatabase();

        operadores operador= null;
        listaMovimientos=new ArrayList<operadores>();
        Cursor cursor= BaseDeDAtos.rawQuery("Select * from  registrosmovimientos" , null);

        while (cursor.moveToNext()){
            operador=new operadores();
            operador.(cursor.getInt(0));
            operador.setTelefono(cursor.getString(1));
            operador.setSaldo(cursor.getString(2));
            operador.setOperador(cursor.getString(3));

            listaMovimientos.add(operador);
        }
    }*/

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
        finish();
    }
}