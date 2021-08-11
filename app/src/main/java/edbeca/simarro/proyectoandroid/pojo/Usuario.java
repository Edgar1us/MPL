package edbeca.simarro.proyectoandroid.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    private int idUsuario;
    private String nombre;
    private String claveSeguridad;
    private String email;
    private ArrayList<Pelicula> peliculas;

    public Usuario() {
    }

    public Usuario(String nombre, String claveSeguridad, String email) {
        this.nombre = nombre;
        this.claveSeguridad = claveSeguridad;
        this.email = email;
        this.peliculas = new ArrayList<Pelicula>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", claveSeguridad='" + claveSeguridad + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
