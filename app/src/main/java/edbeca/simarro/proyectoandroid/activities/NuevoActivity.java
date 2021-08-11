package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.dao.PeliculaDAO;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class NuevoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitulo, edtProtagonista, edtDuracion;
    private Spinner sValoracion;
    private String[] valoraciones = new String[]{"1 estrella","2 estrellas","3 estrellas","4 estrellas","5 estrellas"};
    private ArrayAdapter<String> adapterValoraciones;
    private Button btnAdd;
    private Usuario us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        us = (Usuario)getIntent().getSerializableExtra("Usuario");

        edtTitulo = (EditText)findViewById(R.id.edtTitulo);
        edtProtagonista = (EditText)findViewById(R.id.edtProta);
        edtDuracion = (EditText)findViewById(R.id.edtDuracion);
        sValoracion = (Spinner)findViewById(R.id.sValoracion);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        adapterValoraciones = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoraciones);
        adapterValoraciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sValoracion.setAdapter(adapterValoraciones);

    }

    @Override
    public void onClick(View view) {

        String titulo = edtTitulo.getText().toString();
        int duracion = Integer.parseInt(edtDuracion.getText().toString());
        String prota = edtProtagonista.getText().toString();
        String aux = sValoracion.getSelectedItem().toString();
        int valoracion = Integer.parseInt(aux.substring(0, 1));

        if (us.getNombre().equals("")){
            Pelicula pelicula = new Pelicula(titulo, duracion, prota, valoracion);
            ArrayList<Pelicula> peliss = new ArrayList<Pelicula>();
            peliss.add(pelicula);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Peliculas", (Serializable)peliss);
            Intent i = new Intent(this, ListaPelisFirebaseActivity.class);
            i.putExtra("BUNDLE", bundle);
            i.putExtra("Usuario", us);
            startActivity(i);
        } else {

            Pelicula pelicula = new Pelicula(titulo, duracion, prota, valoracion, us);
            PeliculaDAO pDAO = new PeliculaDAO();
            pDAO.add(pelicula);

            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("Usuario", us);
            startActivity(i);
        }

    }
}

