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
import java.time.temporal.ChronoUnit;
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
        Camino combValida[] = new Camino[tripulaciones.size()];
        int costo=0;
        int costoParcial=0;
        CombinarCaminosTripulacion(tripulaciones, 0, combValida,costo,costoParcial);
        System.out.println("SOLUCION");
        for(Camino camino : combValida){
            System.out.println("Camino: ");
            for(Vuelo vuelo : camino.getCaminoDeVuelos()){
                System.out.println("Vuelo " +vuelo.getCodigo());
            }
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
                        cam.setCosto(costo);

                    }
                } else {
                    CaminosPosibles(tripulacion, mapa, etapa + 1, ady, solucion,j+1,costo,ultimaEtapa);
                }


            }


        }

    }

    public static boolean CombinarCaminosTripulacion(ArrayList<Tripulacion> tripulaciones,int etapa,Camino combValida[],int costo, int costoParcial){
        boolean solucion = false;

        int j=0;
        while(!solucion && j<tripulaciones.get(etapa).getCaminos().size()){
            combValida[etapa]= tripulaciones.get(etapa).getCaminos().get(j);
            costoParcial+=tripulaciones.get(etapa).getCaminos().get(j).getCosto();

            if(combinacionValida(combValida,etapa,costo,costoParcial)){
                if (etapa==tripulaciones.size()-1){
                    solucion=true;
                    costo=costoParcial;
                }else{
                    solucion = CombinarCaminosTripulacion(tripulaciones, etapa+1, combValida,costo, costoParcial);
                }
            }
        }
        return solucion;

    }

    public static boolean combinacionValida(Camino comb[],int etapa,int costo, int costoParcial){
        if(costoParcial<costo){
            ConjuntoTDA<Vuelo> vuelosUsados = new Conjunto<Vuelo>();
            vuelosUsados.inicializarConjunto();

            for(int i=0; i<etapa; i++){
                for(Vuelo vuelo: comb[i].getCaminoDeVuelos()){
                    if(vuelosUsados.pertenece(vuelo)){
                        return false;
                    }else{
                        vuelosUsados.agregar(vuelo);
                    }
                }
            }
        }else{
            return false;
        }

        return true;
    }

    public static int costoEntreDosVuelos(Vuelo vuelo1, Vuelo vuelo2){
        int costo=0;
        System.out.println(vuelo1.getCodigo()+" "+vuelo2.getCodigo());
        long horas = ChronoUnit.HOURS.between(vuelo2.getFecha_aterrizaje(),vuelo1.getFecha_despegue());
        if(horas>2){
            System.out.println("horas entre vuelos: "+horas);
            costo=(int) ((horas-2)*100);
            System.out.println("costo entre vuelos: "+costo);
        }

        return costo;
    }



}
