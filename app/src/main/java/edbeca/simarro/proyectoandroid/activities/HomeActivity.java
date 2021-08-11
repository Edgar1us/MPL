package edbeca.simarro.proyectoandroid.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.BD.Constantes;
import edbeca.simarro.proyectoandroid.BD.MiAppOperacional;
import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.dao.PeliculaDAO;
import edbeca.simarro.proyectoandroid.dao.UsuarioDAO;
import edbeca.simarro.proyectoandroid.fragments.HomeFragment;
import edbeca.simarro.proyectoandroid.fragments.PeliculasListener;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private TextView txtBienvenido;
    private MiAppOperacional ao;
    private ImageButton ibtnPerfil;
    public static ArrayList<ImageButton> botones;
    private AppBarConfiguration mAppBarConfiguration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);


        ao = MiAppOperacional.getInstance(this);
        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        ibtnPerfil = (ImageButton)findViewById(R.id.ibtnPerfil);
        ibtnPerfil.setOnClickListener(this);


        HomeFragment fHome = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.fHome);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Usuario", usuario);
        bundle.putSerializable("MisPeliculas", ao);

        fHome.setArguments(bundle);
        fHome.saludaUsuario();
        fHome.mostrarPeliculas();
        //fHome.setPeliculasListener(this);

        botones = new ArrayList<>();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!usuario.getNombre().equals(""))
            getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent();
        switch (item.getItemId()) {
            case R.id.action_nuevo:
                i.setClass(HomeActivity.this, NuevoActivity.class);
                break;
            case R.id.action_cuenta:
                i.setClass(HomeActivity.this, MiCuentaActivity.class);
                i.putExtra(Constantes.C_MODO, Constantes.C_VISUALIZAR);
                i.putExtra(Constantes.FIELD_USUARIO_ID, usuario.getIdUsuario());
                startActivityForResult(i, Constantes.C_VISUALIZAR);
                break;
            case R.id.action_cambiarClave:
                i.setClass(HomeActivity.this, ClaveActivity.class);
                break;
            case R.id.action_verTodas:
                i.setClass(HomeActivity.this, TodasActivity.class);
                break;
            case R.id.action_configuracion:
                i.setClass(HomeActivity.this, PreferenceActivity.class);
                break;
            case R.id.action_mapa:
                i.setClass(HomeActivity.this, MapsActivity.class);
                break;
            case R.id.action_retrofit:
                i.setClass(HomeActivity.this, RetrofitActivity.class);
                break;
        }
        if (item.getItemId() != R.id.action_cuenta){
            i.putExtra("Usuario", usuario);
            startActivity(i);
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        String id = String.valueOf(usuario.getIdUsuario());

        Toast.makeText(this, usuario.toString(), Toast.LENGTH_SHORT).show();

    }

    /*@Override
    public void onPeliculaSeleccionada(Pelicula pelicula) {

        PeliculaDAO pDAO = new PeliculaDAO();
        pDAO.delete(pelicula);


    }*/

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void colorFondoBotones(SharedPreferences pref){
        if (!pref.getString("color_fondo_botones", "").isEmpty()){
            for (ImageButton boton : botones){
                boton.setBackgroundColor(Color.parseColor(pref.getString("color_fondo_botones", "")));
            }
        }else
            for (ImageButton boton : botones){
                boton.setScrollBarStyle(R.style.BotonesNormal);
            }
    }
}