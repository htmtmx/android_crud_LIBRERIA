package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user, pwd;
    Button iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.edtxt_usuario);
        pwd = (EditText) findViewById(R.id.edtxt_pwd);
        iniciar = (Button) findViewById(R.id.btnLogin);
    }

    public void onClick (View view){
        String nameLog = user.getText().toString();
        String pwdLog = pwd.getText().toString();
        if (user.equals("") && pwd.equals("")){
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            if (iniciar(nameLog, pwdLog)==1){
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                Intent log = new Intent(this, MainActivity2.class);
                startActivity(log);
            }else {
                Toast.makeText(this, "Los datos ingresados son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registrar(View view){
        Intent registrar = new Intent(this, ActivityRegistrarUser.class);
        startActivity(registrar);
    }

    public int iniciar(String u, String p){
        int a= 0;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios ", null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                if (cursor.getString(1).equals(u) && cursor.getString(2).equals(p)){
                    a++;
                }
            }while (cursor.moveToNext());
        }
        return a;
    }
}