import clases.Camino;
import clases.Tripulacion;
import clases.Vuelo;
import tda.*;
import tda.impl.Conjunto;
import tda.impl.GrafoDirigido;

import java.awt.*;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;



public class Main {


    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones, GrafoDirigidoTDA mapa){ //los primeros vuelos son de los que va a salir cada tripulacicion
        LocalDateTime horain=LocalDateTime.of(1999,12,12,12,12); //van a ser todos adyacentes
        ConjuntoTDA<Vuelo> conjunto= mapa.adyacentes(tripulaciones.get(0).getOrigen(),horain); //vuelos q salen de origen
        for (Tripulacion trip: tripulaciones) {
            System.out.println("TRIPULACION "+trip.getCodigo());
            Vuelo vueloasignar = conjunto.elegir(0);
            trip.getCamino().Agregar(vueloasignar);
            conjunto.sacar(vueloasignar);
            LocalDateTime hora= vueloasignar.getFecha_aterrizaje(); //
            Vuelo solucionP[]= new Vuelo[100];
            solucionP[0]=vueloasignar;
            ConjuntoTDA<Vuelo> c=mapa.adyacentes(vueloasignar.getDestino(),hora);
            int j=0;
            int costo=0;
            CaminosPosibles(trip,mapa,1,c,solucionP, j,costo,0);
            ArrayList<Camino> caminos= trip.getCaminos();
            for (int i=0;i<caminos.size();i++){
                System.out.println("CAMINO:");
                ArrayList<Vuelo> camino=caminos.get(i).getCaminoDeVuelos();
                //System.out.println(caminos.size()+" "+camino.size() );
                for (int d=0;d<camino.size();d++){
                    System.out.println("Codigo de vuelo: "+ camino.get(d).getCodigo());
                }


            }
            System.out.println("PROX TRIP");


        }
    }

    public static void CaminosPosibles(Tripulacion tripulacion, GrafoDirigidoTDA mapa, int etapa,ConjuntoTDA<Vuelo> ady,Vuelo solucion[],int j,int costo,int ultimaEtapa){ //AGREGAR COSTO!
        if(j< ady.capacidad()){
            Vuelo vueloaux= ady.elegir(j);
            for (int i = 0; i <= 1; i++) {
                if (i == 1) { //representa el si, cambia a los adyacentes del prox nodo
                    solucion[etapa]=vueloaux;
                    costo+=costoEntreDosVuelos(solucion[etapa], solucion[ultimaEtapa]);
                    ultimaEtapa =etapa;

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
                    CaminosPosibles(tripulacion, mapa, etapa + 1, ady, solucion,j+1,costo,ultimaEtapa);
                }


            }


        }

    }

    public static boolean CombinarCaminosTripulacion(ArrayList<Tripulacion> tripulaciones,int etapa,Camino combValida[]){
        boolean solucion = false;

        Camino camino = tripulaciones.get(etapa).getCaminos().get(0);
        combValida[etapa]=camino;
        
        //while comb valida .size < cant tripulaciones
        while(etapa<tripulaciones.size() && !solucion){
            Camino camino2 = tripulaciones.get(etapa+1).getCaminos().get(0);
            combValida[etapa+1]=camino;

            if(combinacionValida(combValida,etapa)){
                if (etapa==tripulaciones.size()-1){
                    solucion=true;
                }else{
                    solucion = CombinarCaminosTripulacion(tripulaciones, etapa+1, combValida);
                }
            }
        }
        return solucion;
    }

    public static boolean combinacionValida(Camino comb[],int etapa){
        ConjuntoTDA vuelosUsados = new Conjunto<Vuelo>();

        for(Camino camino: comb){
            for(Vuelo vuelo : camino.getCaminoDeVuelos()){
                if(vuelosUsados.pertenece(vuelo)){
                    return false;
                }else{
                    vuelosUsados.agregar(vuelo);
                }
            }
        }

        return true;
    }

    public static int costoEntreDosVuelos(Vuelo vuelo1, Vuelo vuelo2){
        int costo=0;
        /*Duration diferencia = Duration.between(solucion[etapa].getFecha_aterrizaje(), solucion[ultimaEtapa].getFecha_despegue());
        System.out.print("diferencia" +diferencia);*/
        
        return costo;
    }



}
