package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.adapters.AdapterFire;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class ListaPelisFirebaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Pelicula> peliculasFire = new ArrayList<Pelicula>();
    private Usuario usuario;
    private TextView txtFirewelcome;
    private Button fireAdd;
    private ListView fireList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pelis_firebase);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        fireList = (ListView)findViewById(R.id.fireList);

        Bundle bundle = this.getIntent().getBundleExtra("BUNDLE");
        if (bundle!=null){
            this.peliculasFire =(ArrayList<Pelicula>) bundle.getSerializable("Peliculas");
            fireList.setAdapter(new AdapterFire<Pelicula>(this, peliculasFire));
        }


        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");
        //usuario.setPeliculas(new Pelicula(""));


        txtFirewelcome = (TextView)findViewById(R.id.txtFirewelcome);
        fireAdd = (Button)findViewById(R.id.fireAdd);

        fireAdd.setOnClickListener(this);

        if (usuario!=null)
            txtFirewelcome.setText(usuario.getEmail());

    }

    @Override
    public void onClick(View view) {

        Intent in = new Intent(ListaPelisFirebaseActivity.this, NuevoActivity.class);
        in.putExtra("Usuario", usuario);
        startActivity(in);
//        fireList.setAdapter(new AdapterPeliculas<>(this, usuario.getPeliculas()));


    }
}