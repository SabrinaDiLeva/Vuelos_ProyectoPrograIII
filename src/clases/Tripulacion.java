package clases;

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
        ArrayList<Camino> caminos =new ArrayList<Camino>();
        this.caminos=caminos;
        this.camino=cam;

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