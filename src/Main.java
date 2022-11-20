import clases.Camino;
import clases.Tripulacion;
import tda.*;

import java.util.ArrayList;


public class Main {

    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones, GrafoDirigidoTDA mapa){ //los primeros vuelos son de los que va a salir cada tripulacicion
        ConjuntoTDA conjunto= mapa.adyacentes(tripulaciones.get(0).getOrigen());
        for (Tripulacion trip: tripulaciones) {
            Object aeropuerto = conjunto.elegir();
            trip.getCamino().Agregar(aeropuerto);
            conjunto.sacar(aeropuerto);
            Camino caminosol=new Camino();
            CaminosPosibles(trip,mapa,0, mapa.adyacentes(aeropuerto),caminosol );
        }
    }

    public static void CaminosPosibles(Tripulacion tripulacion, GrafoDirigidoTDA mapa, int etapa,ConjuntoTDA ady,Camino solucion){
        for (int i = 0; i <= 1; i++) {
            Object destino= ady.elegir();
            if (i==1){ //representa el si, cambia a los adyacentes del prox nodo
                ConjuntoTDA adyacentes= mapa.adyacentes(destino);
                ady=adyacentes;
                solucion.Agregar(destino);
            }else{ //representa el no, sigue con el resto de adyacentes
                ady.sacar(destino);
            }
            if (etapa==mapa.vertices().capacidad() || destino== tripulacion.getOrigen()){ //si paso x todos los vuelos o volvio al origen.
                if (destino== tripulacion.getOrigen()){
                    tripulacion.getCaminos().add(solucion);
                }
            }
            else {
                CaminosPosibles(tripulacion,mapa,etapa+1,ady,solucion);
            }

        }
    }


    public static void main(String[] args) {

    }



}
