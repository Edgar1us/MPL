package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.io.File;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class ConfiguracionActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox reproducirMusica;
    CheckBox reproducirVideo;
    Button btPreferencias;
    Button btGuardar;
    SharedPreferences prefs;
    Usuario usuario;

    InicioActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        reproducirMusica = findViewById(R.id.checkBoxReproducirMusica);
        reproducirVideo = findViewById(R.id.checkBoxReproducirVideo);
        btPreferencias = findViewById(R.id.btPreferencias);
        btGuardar = findViewById(R.id.btGuardar);

        usuario = (Usuario) getIntent().getSerializableExtra("Usuario");

        prefs = getSharedPreferences("mispreferencias", Context.MODE_PRIVATE);

        File fichero = new File("/data/data/edbeca.simarro.proyectoandroid/shared_prefs/mispreferencias.xml");
        if (fichero.exists()){
            reproducirMusica.setChecked(prefs.getBoolean("musica", false));
            reproducirVideo.setChecked(prefs.getBoolean("video", false));
        }

        btGuardar.setOnClickListener(this);
        btPreferencias.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == btGuardar.getId()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("musica", reproducirMusica.isChecked());
            editor.putBoolean("video", reproducirVideo.isChecked());
            editor.commit();

            intent = new Intent(view.getContext(), HomeActivity.class);
            intent.putExtra("Usuario", usuario);
            startActivity(intent);
        }else if (view.getId() == btPreferencias.getId()){
            intent = new Intent(view.getContext(), PreferenceActivity.class);
            startActivity(intent);
        }


    }
}