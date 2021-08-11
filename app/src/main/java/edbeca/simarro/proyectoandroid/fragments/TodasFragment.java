package edbeca.simarro.proyectoandroid.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import edbeca.simarro.proyectoandroid.BD.MiAppOperacional;
import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.adapters.AdapterPeliculas;
import edbeca.simarro.proyectoandroid.dao.PeliculaDAO;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;

public class TodasFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lTodas;
    private PeliculasListener listener;
    private MiAppOperacional ao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        ao = (MiAppOperacional)getArguments().get("MisPeliculas");

        lTodas = (ListView)getView().findViewById(R.id.lTodas);

        PeliculaDAO pdao = new PeliculaDAO();

        lTodas.setAdapter(new AdapterPeliculas<>(this, pdao.getAll()));
        lTodas.setOnItemClickListener(this);


    }

    public void setPeliculasListener(PeliculasListener listener){
        this.listener = listener;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (listener!=null)
            listener.onPeliculaSeleccionada((Pelicula)lTodas.getAdapter().getItem(i));
    }
}