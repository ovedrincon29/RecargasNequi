package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class inicioSesion extends AppCompatActivity {

    private EditText telefono, contraseña;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        telefono = findViewById(R.id.telefono);
        contraseña = findViewById(R.id.contraseña);
    }

    public void ingreso(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String var1 = contraseña.getText().toString();
        String var2 = telefono.getText().toString();

        if (!var1.isEmpty() && !var2.isEmpty()) {

            Cursor contra = BaseDeDatos.rawQuery("Select * from usuarios where contraseñaUno= " + var1, null);
            Cursor tel = BaseDeDatos.rawQuery("Select * from usuarios where phone= " + var2, null);

            if (contra.moveToFirst() && tel.moveToFirst()) {

                Intent intent = new Intent(this, recargas.class);
                //Envío telefono a clase recargas
                intent.putExtra("cel", var2);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Teléfono y contraseña incorrecto", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
        }
    }
}