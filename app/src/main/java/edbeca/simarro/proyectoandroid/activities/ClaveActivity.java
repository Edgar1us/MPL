package edbeca.simarro.proyectoandroid.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.dao.UsuarioDAO;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

import static edbeca.simarro.proyectoandroid.activities.LoginActivity.botones;

public class ClaveActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtVieja, edtNueva;
    private Button btnEnviar;
    private Usuario usuario;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        edtVieja = (EditText)findViewById(R.id.edtVieja);
        edtNueva = (EditText)findViewById(R.id.edtNueva);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(this);

        botones = new ArrayList<>();
        botones.add(btnEnviar = findViewById(R.id.btnEnviar));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(pref);

    }

    @Override
    public void onClick(View view) {

        String vieja = edtVieja.getText().toString();
        String nueva = edtNueva.getText().toString();

        if (vieja.compareToIgnoreCase(usuario.getClaveSeguridad())==0){
            usuario.setClaveSeguridad(nueva);
            UsuarioDAO uDAO = new UsuarioDAO();
            uDAO.update(usuario);
        }

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

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