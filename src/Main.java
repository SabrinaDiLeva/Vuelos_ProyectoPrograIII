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
    static int x;
    Camino Solucion[];


    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones, GrafoDirigidoTDA mapa,int cantvuelos){ //los primeros vuelos son de los que va a salir cada tripulacicion
        LocalDateTime horain=LocalDateTime.of(1999,12,12,12,12); //van a ser todos adyacentes
        int cont=0;
        ArrayList<Camino> caminos=new ArrayList<Camino>();
        Object origen=tripulaciones.get(0).getOrigen();
        ConjuntoTDA<Vuelo> conjunto= mapa.adyacentes(origen,horain); //vuelos q salen de origen
        Vuelo solucionP[]= new Vuelo[1000];
        CaminosPosibles(caminos, origen,mapa ,0,conjunto,solucionP, 0,0,cantvuelos);

        /*
        for (Camino cam: caminos){
            System.out.println("camino "+ cam.getCosto());
            for (Vuelo vuelo: cam.getCaminoDeVuelos()){
                System.out.println(vuelo.getCodigo());
            }
       }

         */

        tripulaciones.get(0).setCaminos(caminos);
        System.out.println("SOLUCION");
        Camino combValida[] = new Camino[tripulaciones.size()];
        Main sol= new Main();
        sol.Solucion=new Camino[tripulaciones.size()];
        Main trip=new Main();
        Main con=new Main();
        CombinarCaminosTripulacion(tripulaciones, 0, combValida,con,0,cantvuelos,trip,sol);
        int conta=0;
        int total=0;
        for (int i=0;i<= trip.x;i++){
                total+=sol.Solucion[i].getCosto();
                System.out.println("Camino Tripulacion "+ tripulaciones.get(conta).getCodigo() +": ");
                conta++;
                for(Vuelo vuelo : sol.Solucion[i].getCaminoDeVuelos()){
                    System.out.println("Vuelo " +vuelo.getCodigo());
                }
        }
        System.out.println();
        System.out.println("Costo Total "+total);




    }

    public static void CaminosPosibles(ArrayList<Camino> caminos,Object origen, GrafoDirigidoTDA mapa, int etapa,ConjuntoTDA<Vuelo> ady,Vuelo solucion[],int j,int ultimaEtapa, int cantvuelos){
        if(j< ady.capacidad()){
            Vuelo vueloaux= ady.elegir(j);
            for (int i = 0; i <= 1; i++) {
                if (i == 1) { //representa el si, cambia a los adyacentes del prox nodo
                    solucion[etapa]=vueloaux;
                    ultimaEtapa =etapa;
                    ConjuntoTDA adyacentes = mapa.adyacentes(vueloaux.getDestino(), vueloaux.getFecha_aterrizaje());
                    ady = adyacentes;
                    j=-1;
                }
                else {
                    solucion[etapa]=null;
                }

                if (etapa==cantvuelos-1 ||ady.conjuntoVacio() || (i==1 && solucion[ultimaEtapa].getDestino().equals(origen))) {;
                    if (solucion[ultimaEtapa].getDestino().equals(origen)){
                        Camino cam = new Camino();
                        int d=0;
                        int costo=0;
                        Vuelo c=null;
                        while (d<solucion.length&& d<=etapa){
                            Vuelo x = solucion[d];
                            if (x!=null) {
                                cam.Agregar(x);
                                if (c!=null){
                                    costo+=costoEntreDosVuelos(x, c);
                                }
                                c=x;

                            }
                            d++;
                        }

                        caminos.add(cam);
                        cam.setCosto(costo);

                    }
                    if (etapa!=cantvuelos-1){
                        CaminosPosibles(caminos,origen, mapa, etapa + 1, ady, solucion,j+1,ultimaEtapa,cantvuelos);
                    }
                } else {
                    CaminosPosibles(caminos,origen, mapa, etapa + 1, ady, solucion,j+1,ultimaEtapa,cantvuelos);
                }
            }
        }
    }

    public static void CombinarCaminosTripulacion(ArrayList<Tripulacion> tripulaciones,int etapa,Camino combValida[],Main costo, int costoParcial,int cantVuelos,Main trip,Main Solucion ) {
        int j = 0;
        while (j < tripulaciones.get(0).getCaminos().size()) {
            combValida[etapa] = tripulaciones.get(0).getCaminos().get(j);
            if (combinacionValida(combValida, etapa)) {
                int cont = 0;
                costoParcial = 0;
                for (int i = 0; i <= etapa; i++) {
                    cont += combValida[i].getCaminoDeVuelos().size();
                    costoParcial += combValida[i].getCosto();
                }

                if (cont == cantVuelos) { //si se cubren cant vuelos
                    if (costo.x==0 || costoParcial<costo.x ){
                        costo.x = costoParcial;
                        trip.x = etapa;
                        Solucion.Solucion = combValida.clone();
                    }


                } else {
                    if (etapa < tripulaciones.size() - 1) { //combinacion de vuelos igual o menos q cantidad de tripulaciones
                        CombinarCaminosTripulacion(tripulaciones, etapa + 1, combValida, costo, costoParcial, cantVuelos, trip,Solucion);
                    }
                }


            }
            costoParcial -= tripulaciones.get(0).getCaminos().get(j).getCosto();
            j++;
        }


    }

    public static boolean combinacionValida(Camino comb[],int etapa){

            ConjuntoTDA<Vuelo> vuelosUsados = new Conjunto<Vuelo>();
            vuelosUsados.inicializarConjunto();
            for(int i=0; i<=etapa; i++){
                for(Vuelo vuelo: comb[i].getCaminoDeVuelos()){
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
        long horas = ChronoUnit.HOURS.between(vuelo2.getFecha_aterrizaje(),vuelo1.getFecha_despegue());
        if(horas>2){
            costo=(int) ((horas-2)*60);
        }

        return costo;
    }




}