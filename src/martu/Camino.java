package martu;

import java.util.ArrayList;

public class Camino {

    private ArrayList<String> caminoDeVuelos;

    private  int costo;


    public Camino(){
        ArrayList<String> caminoDeVuelos= new ArrayList<String>();
        costo=0;
    }

    public void Agregar(String ciudad){
        caminoDeVuelos.add(ciudad);
    }

    public ArrayList<String> getCaminoDeVuelos() {
        return caminoDeVuelos;
    }


    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }



}
