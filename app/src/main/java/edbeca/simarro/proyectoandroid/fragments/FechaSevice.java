package edbeca.simarro.proyectoandroid.fragments;

import edbeca.simarro.proyectoandroid.pojo.Fecha;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FechaSevice {
    @GET("ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroProvincia/46")
    Call<Fecha> getFecha();
}
