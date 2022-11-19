package martu;

import java.util.ArrayList;

public class Camino {

    private ArrayList<Object> caminoDeVuelos;

    private  int costo;


    public Camino(){
        ArrayList<Object> caminoDeVuelos= new ArrayList<Object>();
        this.caminoDeVuelos=caminoDeVuelos;
        costo=0;
    }

    public void Agregar(Object ciudad){
        caminoDeVuelos.add(ciudad);
    }

    public ArrayList<Object> getCaminoDeVuelos() {
        return caminoDeVuelos;
    }


    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }



}
