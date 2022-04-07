package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityUpdate extends AppCompatActivity {
    private EditText et_idBook, et_nombreBook, et_autor,et_editorial, et_no_sesionesCon, et_profesor_cursoCon, et_correo_profesorCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_idBook = (EditText) findViewById(R.id.edtxt_id_upd_book);
        et_nombreBook = (EditText) findViewById(R.id.edtxt_book_nombre);
        et_autor = (EditText) findViewById(R.id.edtxt_autor);
        et_editorial = (EditText) findViewById(R.id.edtxt_editorial);
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelperBook admin = new AdminSQLiteOpenHelperBook(this, "libros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = et_idBook.getText().toString();
        if (!id.isEmpty()){
            Cursor fila = db.rawQuery
                    ("SELECT nombre, autor, editorial FROM libros WHERE id="+id, null);
            if (fila.moveToFirst()){
                et_nombreBook.setText(fila.getString(0));
                et_autor.setText(fila.getString(1));
                et_editorial.setText(fila.getString(2));
                db.close();
            }else {
                Toast.makeText(this, "No existe ese libro", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir el ID del libro", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar (View view){
        AdminSQLiteOpenHelperBook admin = new AdminSQLiteOpenHelperBook(this, "libros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = et_idBook.getText().toString();
        String nombre = et_nombreBook.getText().toString();
        String autor = et_autor.getText().toString();
        String editorial = et_editorial.getText().toString();

        if (!id.isEmpty() && !nombre.isEmpty() && !autor.isEmpty() && !editorial.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("nombre", nombre);
            registro.put("autor", autor);
            registro.put("editorial", editorial);
            int cantidad = db.update("libros",registro,"id=" + id,null);
            db.close();
            if (cantidad == 1){
                Toast.makeText(this, "Libro actualizado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El libro no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar() {
        et_idBook.setText("");
        et_nombreBook.setText("");
        et_autor.setText("");
        et_editorial.setText("");
    }

    public void regresar(View view){
        Intent regresar = new Intent(this, MainActivity2.class);
        startActivity(regresar);
    }
}