package martu;

import java.util.ArrayList;

public class Tripulacion {
    private String codigo;
    private Object origen;
    private Camino camino;

    public ArrayList<Camino> getCaminos() {
        return caminos;
    }

    private ArrayList<Camino> caminos;


    public Tripulacion(String codigo, Object origen) {
        this.codigo = codigo;
        this.origen = origen;
        Camino cam=new Camino();
        cam.Agregar(origen);
        ArrayList<Camino> caminos =new ArrayList<Camino>();

    }

    public Camino getCamino() {
        return camino;
    }

    public String getCodigo() {
        return codigo;
    }

    public Object getOrigen() {
        return origen;
    }




}