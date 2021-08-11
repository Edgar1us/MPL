package edbeca.simarro.proyectoandroid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class FirebaseActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private EditText nombreUsuario;
    private EditText claveUsuario;
    private EditText nickUsuario;
    private TextView txtRequisitos;
    private TextView txtNick;
    private Button inicioSesion;
    private RadioButton registrar;
    private SharedPreferences prefs;
    private FirebaseAuth auth;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        nombreUsuario = findViewById(R.id.editTextUsuario);
        claveUsuario = findViewById(R.id.editTextClave);
        nickUsuario = findViewById(R.id.editoNombre);
        txtNick = findViewById(R.id.txtoNombre);
        txtRequisitos = findViewById(R.id.txtRequisitos);
        inicioSesion = findViewById(R.id.btAceptar);
        registrar = findViewById(R.id.radioButtonRegistrar);

        registrar.setOnCheckedChangeListener(this);
        inicioSesion.setOnClickListener(this);

        prefs = getSharedPreferences("prefersUsuario", Context.MODE_PRIVATE);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
            nickUsuario.setVisibility(View.VISIBLE);
            txtNick.setVisibility(View.VISIBLE);
            inicioSesion.setText("Registrar");
        }else{
            nickUsuario.setVisibility(View.GONE);
            txtNick.setVisibility(View.GONE);
            txtRequisitos.setVisibility(View.GONE);
            inicioSesion.setText("Iniciar sesión");
        }
    }

    @Override
    public void onClick(View view) {

        String emailPasada = nombreUsuario.getText().toString();
        String clavePasada = claveUsuario.getText().toString();
        String nickPasado = nickUsuario.getText().toString();

        usuario = new Usuario(nickPasado, clavePasada, emailPasada);

        if (!registrar.isChecked()){
            // Login en Firebase
            login(emailPasada, clavePasada);
        }else {
            register(emailPasada, clavePasada, nickPasado);
        }

    }

    private void register(String emailPasada, String clavePasada, final String nickPasado) {

        auth.createUserWithEmailAndPassword(emailPasada, clavePasada)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Se cambia el nick de las preferencias del usuario
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("nick", nickPasado);
                            editor.apply();
                            auth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(FirebaseActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            // Task failed with an exception
                            Toast.makeText(FirebaseActivity.this, "Fallo en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void login(final String emailPasada, String clavePasada) {

        auth.signInWithEmailAndPassword(emailPasada, clavePasada)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Se cambia el email de las preferencias del usuario
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("email", emailPasada);
                            editor.apply();
                            Intent intent = new Intent(getApplication(), FirebaseUsuarioActivity.class);
                            intent.putExtra("Usuario", usuario);
                            startActivity(intent);
                        } else {
                            // Task failed with an exception
                            Toast.makeText(FirebaseActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}