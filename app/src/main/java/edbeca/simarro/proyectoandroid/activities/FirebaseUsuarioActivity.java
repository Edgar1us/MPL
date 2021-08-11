package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class FirebaseUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private EditText edt, edt2;
    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_usuario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        edt = (EditText)findViewById(R.id.edt);
        edt2 = (EditText)findViewById(R.id.edt2);
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        if (usuario!=null){
            edt.setText(usuario.getEmail().toString());
            edt2.setText(usuario.getClaveSeguridad());
            Toast.makeText(this, usuario.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(FirebaseUsuarioActivity.this, ListaPelisFirebaseActivity.class);
        i.putExtra("Usuario", usuario);
        startActivity(i);
    }
}