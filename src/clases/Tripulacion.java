package clases;

import java.util.ArrayList;

public class Tripulacion {
    private String codigo;
    private Object origen;


    public ArrayList<Camino> getCaminos() {
        return caminos;
    }


    public void setCaminos(ArrayList<Camino> caminos) {
        this.caminos = caminos;
    }

    private ArrayList<Camino> caminos;


    public Tripulacion(String codigo, Object origen) {
        this.codigo = codigo;
        this.origen = origen;
        Camino cam=new Camino();
        ArrayList<Camino> caminos =new ArrayList<Camino>();
        this.caminos=caminos;


    }



    public String getCodigo() {
        return codigo;
    }

    public Object getOrigen() {
        return origen;
    }




}