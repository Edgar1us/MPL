package edbeca.simarro.proyectoandroid.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import edbeca.simarro.proyectoandroid.R;

import static edbeca.simarro.proyectoandroid.activities.LoginActivity.botones;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInicioSesion, btnResgistro, btnFirebase;
    private TextView textView;
    SharedPreferences prefsManager;
    Locale localizacion;
    MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsManager = PreferenceManager.getDefaultSharedPreferences(InicioActivity.this);

        if (!prefsManager.getString("idioma", "").isEmpty()){
            String idioma = prefsManager.getString("idioma", "");

            if (idioma.equalsIgnoreCase("ESP"))
                localizacion = new Locale("es", "ES");
            else if (idioma.equalsIgnoreCase("ENG"))
                localizacion = new Locale("en", "US");

            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.locale = localizacion;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getBoolean("reproducirMusica", false)){
            mediaPlayer = MediaPlayer.create(this, R.raw.sound_relax);
            mediaPlayer.start();
        }

        setContentView(R.layout.activity_inicio);

        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textView);
        btnInicioSesion = (Button)findViewById(R.id.btnInicioSesion);
        btnResgistro = (Button)findViewById(R.id.btnRegistro);
        btnFirebase = (Button)findViewById(R.id.btnFirebase);

        textView.setAnimation(animacion);

        btnInicioSesion.setOnClickListener(this);
        btnResgistro.setOnClickListener(this);
        btnFirebase.setOnClickListener(this);

        botones = new ArrayList<>();
        botones.add(btnInicioSesion = findViewById(R.id.btnInicioSesion));
        botones.add(btnResgistro = findViewById(R.id.btnRegistro));
        botones.add(btnFirebase = findViewById(R.id.btnFirebase));

        prefsManager = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(prefsManager);

    }

    @Override
    public void onClick(View view) {
        Intent i ;
        switch (view.getId()){
            case R.id.btnInicioSesion:
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.btnRegistro:
                i = new Intent(this, RegistroActivity.class);
                startActivity(i);
                break;
            case R.id.btnFirebase:
                i = new Intent(this, FirebaseActivity.class);
                startActivity(i);
                break;
        }

    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void colorFondoBotones(SharedPreferences pref){
        if (!pref.getString("color_fondo_botones", "").isEmpty()){
            for (Button boton : botones){
                boton.setBackgroundColor(Color.parseColor(pref.getString("color_fondo_botones", "")));
            }
        }else
            for (Button boton : botones){
                boton.setScrollBarStyle(R.style.BotonesNormal);
            }
    }
}