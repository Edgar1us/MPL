package edbeca.simarro.proyectoandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edbeca.simarro.proyectoandroid.BD.MiAppOperacional;
import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.adapters.AdapterPeliculas;
import edbeca.simarro.proyectoandroid.dao.PeliculaDAO;
import edbeca.simarro.proyectoandroid.dialog.PeliculasDialog;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Usuario usuario;
    private ListView lPeliculas;
    private MiAppOperacional ao;
    private TextView txtBievenido;
    private PeliculasListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void saludaUsuario(){
        txtBievenido = (TextView)getView().findViewById(R.id.txtBienvenido);

        usuario = (Usuario)getArguments().get("Usuario");
        txtBievenido.setText(usuario.getNombre());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void mostrarPeliculas(){

        usuario = (Usuario)getArguments().get("Usuario");
        ao = (MiAppOperacional)getArguments().get("MisPeliculas");
        lPeliculas = (ListView) getView().findViewById(R.id.lPeliculas);

        PeliculaDAO peliculaDAO = new PeliculaDAO();

        //Toast.makeText(this.getActivity(), ao.getPeliculas(usuario).get(0).getTitulo(), Toast.LENGTH_SHORT).show();
        lPeliculas.setAdapter(new AdapterPeliculas<>(this, peliculaDAO.getPeliculas(usuario)));
        lPeliculas.setOnItemClickListener(this);


    }

    public void setPeliculasListener(PeliculasListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentManager fragment = this.getActivity().getSupportFragmentManager();
        PeliculasDialog dialog = newInstance((Pelicula) adapterView.getItemAtPosition(i));
        dialog.show(fragment, "tag");

        /*if (listener!=null)
            listener.onPeliculaSeleccionada((Pelicula)lPeliculas.getAdapter().getItem(i));*/
    }

    static PeliculasDialog newInstance(Pelicula p){
        PeliculasDialog dialogPeliculas = new PeliculasDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Pelicula", p);
        dialogPeliculas.setArguments(bundle);
        return dialogPeliculas;
    }

}