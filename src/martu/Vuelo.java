package martu;

import java.util.Date;

public class Vuelo {
    private int codigo;
    private Object origen;
    private Object destino;
    private Date fecha_despegue;
    private Date fecha_aterrizaje;
    private Tripulacion tripulacion;

    public Vuelo(int codigo, Object origen, Object destino, Date fecha_despegue, Date fecha_aterrizaje) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fecha_despegue = fecha_despegue;
        this.fecha_aterrizaje = fecha_aterrizaje;
        this.tripulacion = null;
    }

    public int getCodigo() {
        return codigo;
    }


    public Object getOrigen() {
        return origen;
    }


    public Object getDestino() {
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