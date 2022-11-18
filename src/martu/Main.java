package martu;
import tda.*;

import java.util.ArrayList;


public class Main {

    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones,GrafoDirigidoTDA mapa){ //los primeros vuelos son de los que va a salir cada tripulacicion
        ConjuntoTDA conjunto= mapa.adyacentes(tripulaciones.get(0).getOrigen());
        for (Tripulacion trip: tripulaciones) {
            String aeropuerto = conjunto.elegir();
            trip.getCamino().Agregar(aeropuerto);
            conjunto.sacar(aeropuerto);
            CaminosPosibles(trip,mapa);
        }
    }

    public static ArrayList<Camino> CaminosPosibles(Tripulacion tripulacion, GrafoDirigidoTDA mapa, int etapa, ){
        for (int i = 0; i <= 1; i++) {


        }
    }

    public static void sumaSubconjuntos(VectorTDA<Integer> v, int m, VectorTDA<Integer> solucionActual, int sumaActual, int etapa) {
        for (int i = 0; i <= 1; i++) {
            solucionActual.agregarElemento(etapa, i);
            sumaActual += v.recuperarElemento(etapa) * i;
            if (etapa == v.capacidadVector() - 1) {
                if (sumaActual == m) {
                    System.out.println(solucionActual);
                }
            } else {
                if (sumaActual <= m) {
                    sumaSubconjuntos(v, m, solucionActual, sumaActual, etapa + 1);
                }
            }
        }
    }



}
