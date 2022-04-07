package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examen.entidades.Libro;

import java.util.ArrayList;

public class ActivityCons extends AppCompatActivity {
    ListView listViewLibros;
    ArrayList<String> listaInformacion;
    ArrayList<Libro>  listaLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons);

        listViewLibros = (ListView) findViewById(R.id.listLibros);
        consultaCursos();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewLibros.setAdapter(adapter);
        listViewLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String informacion = "id: "+ listaLibros.get(i).getId()+ "\n";
                informacion+="Nombre: "+ listaLibros.get(i).getNombreLibro()+ "\n";
                Toast.makeText(ActivityCons.this, informacion, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void consultaCursos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "libros",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Libro libro = null;
        listaLibros = new ArrayList<Libro>();
        Cursor cursor = db.rawQuery("SELECT * FROM libros",null);
        while (cursor.moveToNext()){
            libro = new Libro();
            libro.setId(cursor.getInt(0));
            libro.setNombreLibro(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setEditorial(cursor.getString(3));
            listaLibros.add(libro);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaLibros.size(); i++){
            listaInformacion.add(listaLibros.get(i).getId()+ " - "  + listaLibros.get(i).getNombreLibro());
        }
    }
    public void onClick (View view){
        Intent regresarCon = new Intent(this, MainActivity.class);
        startActivity(regresarCon);
    }

    public void regresar(View view){
        Intent regresar = new Intent(this, MainActivity2.class);
        startActivity(regresar);
    }
}