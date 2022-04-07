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

public class ActivityRegBook extends AppCompatActivity {
    EditText et_id, et_book, et_autor, et_editorial;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_book);

        et_id = (EditText) findViewById(R.id.edtxt_id_regbook);
        et_book = (EditText) findViewById(R.id.edtxt_book_reg);
        et_autor = (EditText) findViewById(R.id.edtxt_autor);
        et_editorial = (EditText) findViewById(R.id.edtxt_editorial);
    }

    public void onClick (View view){
        AdminSQLiteOpenHelperBook admin = new AdminSQLiteOpenHelperBook(this, "libros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = et_id.getText().toString();
        String book = et_book.getText().toString();
        String autor = et_autor.getText().toString();
        String editorial = et_editorial.getText().toString();
        //Toast.makeText(this, pwd+" - "+pwd_confirm, Toast.LENGTH_SHORT).show();
        if (!id.isEmpty() && !book.isEmpty() && !autor.isEmpty() && !editorial.isEmpty()){
                ContentValues registro = new ContentValues();
                registro.put("id", id);
                registro.put("nombre", book);
                registro.put("autor", autor);
            registro.put("editorial", editorial);
                db.insert("libros",null,registro);
                db.close();
                limpiar();
                Toast.makeText(this, "Tu registro ha sido exitoso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    public void regresar(View view){
        Intent regresar = new Intent(this, MainActivity2.class);
        startActivity(regresar);
    }

    private void limpiar() {
        et_id.setText("");
        et_book.setText("");
        et_autor.setText("");
        et_editorial.setText("");
    }
}