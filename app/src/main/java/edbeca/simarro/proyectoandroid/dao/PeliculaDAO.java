package edbeca.simarro.proyectoandroid.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;

import edbeca.simarro.proyectoandroid.BD.MiBD;
import edbeca.simarro.proyectoandroid.pojo.Pelicula;
import edbeca.simarro.proyectoandroid.pojo.Usuario;

public class PeliculaDAO implements PojoDAO{

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Pelicula p = (Pelicula) obj;

        contentValues.put("titulo", p.getTitulo());
        contentValues.put("duracion", p.getDuracion());
        contentValues.put("protagonista", p.getProtagonista());
        contentValues.put("valoracion",p.getValoracion());
        contentValues.put("idUsuario", p.getUsuario().getIdUsuario());
        return MiBD.getDB().insert("peliculas", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Pelicula p = (Pelicula) obj;
        contentValues.put("titulo", p.getTitulo());
        contentValues.put("duracion", p.getDuracion());
        contentValues.put("protagonista", p.getProtagonista());
        contentValues.put("valoracion",p.getValoracion());
        contentValues.put("idUsuario", p.getUsuario().getIdUsuario());

        String condicion = "id=" + String.valueOf(p.getIdPelicula());

        return MiBD.getDB().update("peliculas", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Pelicula p = (Pelicula) obj;
        String condicion = "id=" + String.valueOf(p.getIdPelicula());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("peliculas", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Pelicula p = (Pelicula) obj;
        String condicion = "";
        if(TextUtils.isEmpty(p.getTitulo())){
            condicion = "id=" + String.valueOf(p.getIdPelicula());
        }else{
            condicion = "titulo=" + String.valueOf(p.getTitulo())  + " AND protagonista = " + String.valueOf(p.getProtagonista());
        }

        String[] columnas = {
                "id","titulo","duracion","protagonista","valoracion", "idUsuario"
        };
        Cursor cursor = MiBD.getDB().query("peliculas", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            p.setIdPelicula(cursor.getInt(0));
            p.setTitulo(cursor.getString(1));
            p.setDuracion(cursor.getInt(2));
            p.setProtagonista(cursor.getString(3));
            p.setValoracion(cursor.getInt(4));

            // Obtenemos el cliente y lo asignamos
            Usuario a = new Usuario();
            a.setIdUsuario(cursor.getInt(5));
            a = (Usuario) MiBD.getInstance(null).getUsuarioDAO().search(a);
            p.setUsuario(a);

            // Obtenemos la lista de movimientos y los asignamos
            //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

            return p;
        }else{
            return null;
        }
    }




    @Override
    public ArrayList getAll() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
        String[] columnas = {
                "id","titulo","duracion","protagonista","valoracion", "idUsuario"
        };
        Cursor cursor = MiBD.getDB().query("peliculas", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Pelicula p = new Pelicula();
                p.setIdPelicula(cursor.getInt(0));
                p.setTitulo(cursor.getString(1));
                p.setDuracion(cursor.getInt(2));
                p.setProtagonista(cursor.getString(3));
                p.setValoracion(cursor.getInt(4));

                // Obtenemos el cliente y lo asignamos
                Usuario a = new Usuario();
                a.setIdUsuario(cursor.getInt(5));
                a = (Usuario) MiBD.getInstance(null).getUsuarioDAO().search(a);
                p.setUsuario(a);

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

                listaPeliculas.add(p);

            } while(cursor.moveToNext());
        }
        return listaPeliculas;
    }

    public ArrayList getPeliculas(Usuario usuario) {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
        String condicion = "idUsuario=" + String.valueOf(usuario.getIdUsuario());
        String[] columnas = {
                "id", "titulo", "duracion", "protagonista", "valoracion", "idUsuario"

        };
        Cursor cursor = MiBD.getDB().query("peliculas", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Pelicula p = new Pelicula();
                p.setIdPelicula(cursor.getInt(0));
                p.setTitulo(cursor.getString(1));
                p.setDuracion(cursor.getInt(2));
                p.setProtagonista(cursor.getString(3));
                p.setValoracion(cursor.getInt(4));

                p.setUsuario(usuario);

                listaPeliculas.add(p);

            } while (cursor.moveToNext());
        }
        return listaPeliculas;
    }

}
