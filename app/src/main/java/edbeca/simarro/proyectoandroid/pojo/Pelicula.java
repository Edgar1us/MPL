package edbeca.simarro.proyectoandroid.pojo;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private int idPelicula;
    private String titulo;
    private int duracion;
    private String protagonista;
    private int valoracion;
    private Usuario usuario;

    public Pelicula() {
    }

    public Pelicula(String titulo, int duracion, String protagonista, int valoracion, Usuario usuario) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.protagonista = protagonista;
        this.valoracion = valoracion;
        this.usuario = usuario;
    }

    public Pelicula(String titulo, int duracion, String protagonista, int valoracion) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.protagonista = protagonista;
        this.valoracion = valoracion;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                ", titulo='" + titulo + '\'' +
                ", protagonista='" + protagonista + '\'' +
                '}';
    }
}
