import clases.Camino;
import clases.Tripulacion;
import clases.Vuelo;
import tda.*;
import tda.impl.GrafoDirigido;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Main {

    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones, GrafoDirigidoTDA mapa){ //los primeros vuelos son de los que va a salir cada tripulacicion
        LocalDateTime horain=LocalDateTime.of(1999,12,12,12,12); //van a ser todos adyacentes
        ConjuntoTDA<Vuelo> conjunto= mapa.adyacentes(tripulaciones.get(0).getOrigen(),horain); //vuelos q salen de origen
        int cont=0;
        for (Tripulacion trip: tripulaciones) {
            Vuelo vueloasignar = conjunto.elegir(0);
            cont++;
            trip.getCamino().Agregar(vueloasignar);
            conjunto.sacar(vueloasignar);
            LocalDateTime hora= vueloasignar.getFecha_aterrizaje(); //
            Vuelo solucionP[]= new Vuelo[100];
            solucionP[0]=vueloasignar;
            ConjuntoTDA<Vuelo> c=mapa.adyacentes(vueloasignar.getDestino(),hora);
            int j=0;
            CaminosPosibles(trip,mapa,1,c,solucionP, j);
            ArrayList<Camino> caminos= trip.getCaminos();
            for (int i=0;i<caminos.size();i++){
                ArrayList<Vuelo> camino=caminos.get(i).getCaminoDeVuelos();
                System.out.println(caminos.size()+" "+camino.size() );

                for (int d=0;d<camino.size();d++){
                    System.out.println(camino.get(d).getDestino() +" "+ camino.get(d).getCodigo());
                }


            }
            System.out.println("PROX TRIP");


        }
    }

    public static void CaminosPosibles(Tripulacion tripulacion, GrafoDirigidoTDA mapa, int etapa,ConjuntoTDA<Vuelo> ady,Vuelo solucion[],int j){ //AGREGAR COSTO!
        if(j< ady.capacidad()){
            Vuelo vueloaux= ady.elegir(j);
            for (int i = 0; i <= 1; i++) {
                if (i == 1) { //representa el si, cambia a los adyacentes del prox nodo
                    solucion[etapa]=vueloaux;
                    ConjuntoTDA adyacentes = mapa.adyacentes(vueloaux.getDestino(), vueloaux.getFecha_aterrizaje());
                    ady = adyacentes;
                    j=-1;
                }

                if (i==1 &&(etapa == mapa.vertices().capacidad() || vueloaux.getDestino() == tripulacion.getOrigen())) { //si paso x todos los vuelos o volvio al origen.
                    if (vueloaux.getDestino() == tripulacion.getOrigen()) {
                        Camino cam = new Camino();
                        for (int d = 0; d < solucion.length; d++) {
                            Vuelo x = solucion[d];
                            if (x!=null)
                                cam.Agregar(x);
                        }
                        tripulacion.getCaminos().add(cam);

                    }
                } else {
                    CaminosPosibles(tripulacion, mapa, etapa + 1, ady, solucion,j+1);
                }


            }


        }

    }








}
