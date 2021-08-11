package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Toast;

import edbeca.simarro.proyectoandroid.BD.MiAppOperacional;
import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.fragments.PeliculasListener;
import edbeca.simarro.proyectoandroid.fragments.TodasFragment;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class TodasActivity extends AppCompatActivity implements PeliculasListener {

    private MiAppOperacional ao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        ao = MiAppOperacional.getInstance(this);
        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        TodasFragment fTodas = (TodasFragment)getSupportFragmentManager().findFragmentById(R.id.fTodas);

        Bundle bundle = new Bundle();
        bundle.putSerializable("MisPeliculas", ao);
        fTodas.setArguments(bundle);
        fTodas.setPeliculasListener(this);
        //fTodas

    }


    @Override
    public void onPeliculaSeleccionada(Pelicula pelicula) {
        Toast.makeText(this, "Esta pelicula pertenece a " + pelicula.getUsuario().getNombre(), Toast.LENGTH_SHORT).show();

    }
}