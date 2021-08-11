package edbeca.simarro.proyectoandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;

public class AdapterPeliculas<T> extends ArrayAdapter<T> {

    public AdapterPeliculas(Fragment context, List<T> objects) {
        super(context.getActivity(), 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.element_peliculas_list,parent,false);
        }
        //Obteniendo instancias de los text views
        //ImageView imagen = (ImageView)listItemView.findViewById(R.id.imagen);
        TextView titulo = (TextView)listItemView.findViewById(R.id.txtTitulo);
        TextView protagonista = (TextView)listItemView.findViewById(R.id.txtProtagonista);
        //Obteniendo instancia del ítem en la posición actual
        Pelicula item = (Pelicula) getItem(position);
        //imagen.setImageResource(item.getImagen());
        titulo.setText(item.getTitulo());
        protagonista.setText(item.getProtagonista());

        /*if (item.getSaldoActual()<0){
            saldo.setTextColor(Color.parseColor("#FFFF0009"));
        }else{
            saldo.setTextColor(Color.parseColor("#005004"));
        }*/

        //Devolver al ListView la fila creada
        return listItemView;
    }

}
