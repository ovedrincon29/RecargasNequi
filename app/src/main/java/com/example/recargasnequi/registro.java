package com.example.recargasnequi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registro extends AppCompatActivity {

    private EditText nombresApellidos, correo, telefono, contraseña, confirmaContraseña;
    private int enteroSaldo = 200000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombresApellidos = findViewById(R.id.nombresApellidos);
        correo = findViewById(R.id.correo);
        telefono = findViewById(R.id.telefono);
        contraseña = findViewById(R.id.contraseña);
        confirmaContraseña = findViewById(R.id.confirmaContraseña);
    }

    public void registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombres = nombresApellidos.getText().toString();
        String contraseñaUno = contraseña.getText().toString();
        String contraseñaDos = confirmaContraseña.getText().toString();
        String phone = telefono.getText().toString();
        String email = correo.getText().toString();

        if (!contraseñaUno.isEmpty() && !contraseñaDos.isEmpty() && !nombres.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {

            if (!contraseñaUno.equals(contraseñaDos) || telefono.length()!=10 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()|| contraseñaUno.length()!=4 || contraseñaDos.length()!=4){
                if (!contraseñaUno.equals(contraseñaDos)){
                    confirmaContraseña.setError("Verifica la contraseña");
                }
                if (telefono.length()!=10){
                    telefono.setError("Ingresa un teléfono válido");
                }
                if (contraseñaUno.length()!=4 || contraseñaDos.length()!=4){
                    Toast.makeText(this, "Mínimo 4 números en la contraseña", Toast.LENGTH_SHORT).show();
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    correo.setError("Correo inválido");
                }
            }else {
                ContentValues registros = new ContentValues();
                registros.put("contraseñaUno", contraseñaUno);
                registros.put("nombres", nombres);
                registros.put("email", email);
                registros.put("phone", phone);
                registros.put("saldo", enteroSaldo);


                BaseDeDatos.insert("usuarios", null, registros);
                BaseDeDatos.close();

                nombresApellidos.setText("");
                correo.setText("");
                telefono.setText("");
                contraseña.setText("");
                confirmaContraseña.setText("");

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}