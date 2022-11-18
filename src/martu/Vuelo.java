package martu;

import java.util.Date;

public class Vuelo {
    private String codigo;
    private String origen;
    private String destino;
    private Date fecha_despegue;
    private Date fecha_aterrizaje;
    private Tripulacion tripulacion;

    public Vuelo(String codigo, String origen, String destino, Date fecha_despegue, Date fecha_aterrizaje) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fecha_despegue = fecha_despegue;
        this.fecha_aterrizaje = fecha_aterrizaje;
        this.tripulacion = null;
    }

    public String getCodigo() {
        return codigo;
    }


    public String getOrigen() {
        return origen;
    }


    public String getDestino() {
        return destino;
    }


    public Date getFecha_despegue() {
        return fecha_despegue;
    }


    public Date getFecha_aterrizaje() {
        return fecha_aterrizaje;
    }

    public Tripulacion getTripulacion() {
        return tripulacion;
    }

}