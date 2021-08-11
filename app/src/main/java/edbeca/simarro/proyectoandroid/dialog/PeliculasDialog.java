package edbeca.simarro.proyectoandroid.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.dao.PeliculaDAO;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;

public class PeliculasDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Pelicula p = (Pelicula) this.getArguments().getSerializable("Pelicula");

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        PeliculaDAO pDAO = new PeliculaDAO();
                        pDAO.delete(p);
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Quieres eliminar esta pelicula de tú lista?").setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_peliculas, null);

        ImageView imgFoto = view.findViewById(R.id.ImgFoto);
        TextView textoTitulo = view.findViewById(R.id.textoTitulo);
        TextView textoProta = view.findViewById(R.id.textoProta);


        textoTitulo.setText(p.getTitulo());
        textoProta.setText(p.getProtagonista());

        switch (p.getValoracion()) {
            case 1:
                imgFoto.setImageResource(R.drawable.estrella);
                break;
            case 2:
                imgFoto.setImageResource(R.drawable.dosestrellas);
                break;
            case 3:
                imgFoto.setImageResource(R.drawable.clasificar);
                break;
            case 4:
                imgFoto.setImageResource(R.drawable.cuatroestrellas);
                break;
            case 5:
                imgFoto.setImageResource(R.drawable.cincoestrellas);
        }

        builder.setView(view);
        return builder.create();
    }

}