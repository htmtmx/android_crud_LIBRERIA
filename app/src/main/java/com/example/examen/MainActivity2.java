package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText et_id, et_user, et_pwd, et_pwd_confirm;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*et_id = (EditText) findViewById(R.id.edtxt_id_reg);
        et_user = (EditText) findViewById(R.id.edtxt_usuario_reg);
        et_pwd = (EditText) findViewById(R.id.edtxt_pwd_reg);
        et_pwd_confirm = (EditText) findViewById(R.id.edtxt_pwd_reg_confirm);*/
    }

    public void onClick (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = et_id.getText().toString();
        String user = et_user.getText().toString();
        String pwd = et_pwd.getText().toString();
        String pwd_confirm = et_pwd_confirm.getText().toString();
        //Toast.makeText(this, pwd+" - "+pwd_confirm, Toast.LENGTH_SHORT).show();
        if (!id.isEmpty() && !user.isEmpty() && !pwd.isEmpty() && !pwd_confirm.isEmpty()){
            if (pwd.equals(pwd_confirm)){
                ContentValues registro = new ContentValues();
                registro.put("id", id);
                registro.put("nombre", user);
                registro.put("pwd", pwd);
                db.insert("usuarios",null,registro);
                db.close();
                limpiar();
                Toast.makeText(this, "Tu registro ha sido exitoso!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Las contraseñas no coinciden, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    public void regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

    public void registrar(View view){
        Intent registrar = new Intent(this, ActivityRegBook.class);
        startActivity(registrar);
    }
    public void update(View view){
        Intent update = new Intent(this, ActivityUpdate.class);
        startActivity(update);
    }

    public void consultar(View view){
        Intent consultar = new Intent(this, ActivityCons.class);
        startActivity(consultar);
    }

    private void limpiar() {
        et_id.setText("");
        et_user.setText("");
        et_pwd.setText("");
        et_pwd_confirm.setText("");
    }
}