package clases;

import java.time.LocalDateTime;
import java.util.Date;

public class Vuelo {
    private int codigo;
    private Object origen;
    private Object destino;
    private LocalDateTime fecha_despegue;
    private LocalDateTime fecha_aterrizaje;
    private Tripulacion tripulacion;

    public Vuelo(int codigo,  Object origen, Object destino, LocalDateTime fecha_despegue, LocalDateTime fecha_aterrizaje) {
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


    public LocalDateTime getFecha_despegue() {
        return fecha_despegue;
    }


    public LocalDateTime getFecha_aterrizaje() {
        return fecha_aterrizaje;
    }

    public Tripulacion getTripulacion() {
        return tripulacion;
    }

}