package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edbeca.simarro.proyectoandroid.BD.MiAppOperacional;
import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.dao.UsuarioDAO;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regUsuario, regPass, regEmail;
    private Button btnRegistrarme;
    private MiAppOperacional ao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        ao = MiAppOperacional.getInstance(this);

        regUsuario = (EditText)findViewById(R.id.regUsuario);
        regPass = (EditText)findViewById(R.id.regPass);
        regEmail = (EditText)findViewById(R.id.regEmail);

        btnRegistrarme = (Button)findViewById(R.id.btnRegistarse);
        btnRegistrarme.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String user = regUsuario.getText().toString();
        String password = regPass.getText().toString();
        String email = regEmail.getText().toString();
        Usuario usuario = new Usuario(user, password, email);

        UsuarioDAO uDAO = new UsuarioDAO();
        uDAO.add(usuario);

        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);


    }
}