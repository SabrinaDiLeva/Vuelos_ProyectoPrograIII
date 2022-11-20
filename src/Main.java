import clases.Camino;
import clases.Tripulacion;
import clases.Vuelo;
import tda.*;
import tda.impl.GrafoDirigido;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Main {

    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones, GrafoDirigidoTDA mapa){ //los primeros vuelos son de los que va a salir cada tripulacicion
        LocalDateTime horain=LocalDateTime.of(1999,12,12,12,12); //van a ser todos adyacentes
        ConjuntoTDA<Vuelo> conjunto= mapa.adyacentes(tripulaciones.get(0).getOrigen(),horain); //vuelos q salen de origen
        for (Tripulacion trip: tripulaciones) {
            Vuelo vueloasignar = conjunto.elegir();
            System.out.println(vueloasignar.getDestino().toString()+ " d "+vueloasignar.getCodigo());
            trip.getCamino().Agregar(vueloasignar);
            for (int d=0;d<trip.getCamino().getCaminoDeVuelos().size();d++){
                System.out.println(trip.getCamino().getCaminoDeVuelos().get(d).getDestino());
            }
            conjunto.sacar(vueloasignar);
            LocalDateTime hora= vueloasignar.getFecha_aterrizaje(); //
            ArrayList<Vuelo> solucionP= new ArrayList<Vuelo>();
            solucionP.add(0,vueloasignar);
            ConjuntoTDA<Vuelo> c=mapa.adyacentes(vueloasignar.getDestino(),hora); //no anda ady
            System.out.println(c.conjuntoVacio());
            while (!c.conjuntoVacio()){
                Vuelo v=c.elegir();
                System.out.println("ady"+ v.getCodigo());
                c.sacar(v);
            }

            CaminosPosibles(trip,mapa,1,c,solucionP);
            ArrayList<Camino> caminos= trip.getCaminos();
            for (int i=0;i<caminos.size();i++){
                ArrayList<Vuelo> camino=caminos.get(i).getCaminoDeVuelos();
                for (int d=0;d<camino.size();d++){
                    System.out.println(camino.get(d).getDestino());
                }


            }


        }
    }

    public static void CaminosPosibles(Tripulacion tripulacion, GrafoDirigidoTDA mapa, int etapa,ConjuntoTDA<Vuelo> ady,ArrayList<Vuelo> solucion){
        System.out.println("ENTRE");
        Vuelo vueloaux= ady.elegir();
        System.out.println(vueloaux.getCodigo());

        for (int i = 0; i <= 1; i++) {
            if (i==1){ //representa el si, cambia a los adyacentes del prox nodo
                solucion.add(etapa,vueloaux);
                ConjuntoTDA adyacentes= mapa.adyacentes(vueloaux.getDestino(),vueloaux.getFecha_aterrizaje());
                ady=adyacentes;
            }else{ //representa el no, sigue con el resto de adyacentes
                ady.sacar(vueloaux);
            }
            if (etapa==mapa.vertices().capacidad() || vueloaux.getDestino()== tripulacion.getOrigen()){ //si paso x todos los vuelos o volvio al origen.
                if (vueloaux.getDestino()== tripulacion.getOrigen()){
                    Camino cam=new Camino();
                    for (int d=0;d<solucion.size();d++){
                        Vuelo x=solucion.get(d);
                        cam.Agregar(x);
                    }
                    tripulacion.getCaminos().add(cam);

                }
            }
            else {
                CaminosPosibles(tripulacion,mapa,etapa+1,ady,solucion);
            }

        }
    }






}
