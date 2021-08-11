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
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.BD.MiAppOperacional;
import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEntrar;
    private EditText edtUsuario;
    private EditText edtPassword;
    private MiAppOperacional o;
    public static ArrayList<Button> botones;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        o = MiAppOperacional.getInstance(this);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtPassword = (EditText)findViewById(R.id.edtPass);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(this);

        edtUsuario.setText("pedro.pitia.es");
        edtPassword.setText("1234");

        botones = new ArrayList<>();
        botones.add(btnEntrar = findViewById(R.id.btnEntrar));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(pref);

    }

    @Override
    public void onClick(View view) {

        Usuario usuario = new Usuario();

        usuario.setEmail(edtUsuario.getText().toString());
        usuario.setClaveSeguridad(edtPassword.getText().toString());

        usuario = o.login(usuario);
        if (usuario!=null){

            Intent i = new Intent(this, MenuActivity.class);
            i.putExtra("Usuario", usuario);
            startActivity(i);

        } else {
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
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