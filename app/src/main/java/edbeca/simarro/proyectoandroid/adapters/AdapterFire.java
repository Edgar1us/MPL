package edbeca.simarro.proyectoandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;

public class AdapterFire<P> extends ArrayAdapter<Pelicula> {

    Activity context;
    ArrayList<Pelicula> ps;

    public AdapterFire(Activity context, ArrayList<Pelicula> ps) {
        super(context, R.layout.element_peliculas_list, ps);
        this.context = context;
        this.ps = ps;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.element_peliculas_list, null);

        TextView t = item.findViewById(R.id.txtTitulo);
        t.setText(ps.get(position).getTitulo());

        TextView pr = item.findViewById(R.id.txtProtagonista);
        pr.setText(ps.get(position).getProtagonista());

        return item;
    }
}
