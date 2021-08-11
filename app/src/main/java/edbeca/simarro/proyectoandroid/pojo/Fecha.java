package edbeca.simarro.proyectoandroid.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fecha {

    @SerializedName("Fecha")
    @Expose
    private String fecha;

    @SerializedName("ResultadoConsulta")
    @Expose
    private String resultadoConsulta;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(String resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

}
