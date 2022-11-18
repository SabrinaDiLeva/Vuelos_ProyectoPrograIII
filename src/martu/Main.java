package martu;

import java.util.ArrayList;


public class Main {

    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulacions,ArrayList<Vuelo> vuelos){ //los primeros vuelos son de los que va a salir cada tripulacicion
        for (Tripulacion trip: tripulacions){
            trip.getCamino().Agregar(vuelos.get(0).getDestino());
            vuelos.remove(0);

        }
    }

    public static void CaminosPosibles(ArrayList<Vuelo> vuelos, Tripulacion trip){



    }

}
