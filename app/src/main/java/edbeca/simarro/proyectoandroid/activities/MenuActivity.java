package edbeca.simarro.proyectoandroid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Usuario usuario;
    private TextView textNickNav, textEmailNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        navigationView = (NavigationView)findViewById(R.id.nav_view);

        View view = navigationView.getHeaderView(0);
        textNickNav = (TextView)view.findViewById(R.id.textNickNav);
        textEmailNav = (TextView)view.findViewById(R.id.textEmailNav);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        if (usuario!=null){
            textNickNav.setText(usuario.getNombre());
            textEmailNav.setText(usuario.getEmail());
        }


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                Bundle args = new Bundle();
                switch (item.getItemId()){
                    case R.id.nav_home:

                        i = new Intent(MenuActivity.this, HomeActivity.class);
                        i.putExtra("Usuario", usuario);
                        startActivity(i);

                        break;
                    case R.id.nav_clave:
                        i = new Intent(MenuActivity.this, ClaveActivity.class);
                        i.putExtra("Usuario", usuario);
                        startActivity(i);
                        break;
                    case R.id.nav_cerrar:
                        i = new Intent(MenuActivity.this, InicioActivity.class);
                        startActivity(i);
                        break;
                }

                return true;
            }
        });

    }

}