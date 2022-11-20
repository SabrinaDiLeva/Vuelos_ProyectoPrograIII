package clases;

import java.util.ArrayList;

public class Camino {

    private ArrayList<Vuelo> caminoDeVuelos;

    private  int costo;


    public Camino(){
        ArrayList<Vuelo> caminoDeVuelos= new ArrayList<Vuelo>();
        this.caminoDeVuelos=caminoDeVuelos;
        costo=0;
    }

    public void Agregar(Vuelo ciudad){
        caminoDeVuelos.add(ciudad);
    }

    public ArrayList<Vuelo> getCaminoDeVuelos() {
        return caminoDeVuelos;
    }


    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }



}
