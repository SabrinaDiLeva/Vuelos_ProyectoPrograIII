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


    public static void AsignarPrimerVuelo(ArrayList<Tripulacion> tripulaciones, GrafoDirigidoTDA mapa,int cantvuelos){ //los primeros vuelos son de los que va a salir cada tripulacicion
        LocalDateTime horain=LocalDateTime.of(1999,12,12,12,12); //van a ser todos adyacentes
        int cont=0;
        ArrayList<Camino> caminos=new ArrayList<Camino>();
        Object origen=tripulaciones.get(0).getOrigen();
        ConjuntoTDA<Vuelo> conjunto= mapa.adyacentes(origen,horain); //vuelos q salen de origen
        Vuelo solucionP[]= new Vuelo[1000];
        CaminosPosibles(caminos, origen,mapa ,0,conjunto,solucionP, 0,0,0,cantvuelos);
        while (cont<tripulaciones.size() && !conjunto.conjuntoVacio()){ //puede pasar q hay menos tripulaciones q vuelos en el origen o alrevez
            tripulaciones.get(cont).setCaminos(caminos);
            conjunto.sacar(conjunto.elegir(0));
            cont++;
        }
       /* for (Camino cam: caminos){
            System.out.println("camino "+ cam.getCosto());
            for (Vuelo vuelo: cam.getCaminoDeVuelos()){
                System.out.println(vuelo.getCodigo());
            }
        }
        */
        System.out.println("SOLUCION");
        Camino combValida[] = new Camino[tripulaciones.size()];
        boolean HaySol=CombinarCaminosTripulacion(tripulaciones, 0, combValida,0,0,cantvuelos,cont);
        int conta=0;
        int total=0;
        if (HaySol){
            for(Camino camino : combValida){
                total+=camino.getCosto();
                System.out.println("Camino Tripulacion "+ tripulaciones.get(conta).getCodigo() +": ");
                conta++;
                for(Vuelo vuelo : camino.getCaminoDeVuelos()){
                    System.out.println("Vuelo " +vuelo.getCodigo());
                }
            }
            System.out.println();
            System.out.println("Costo Total "+total);
        }else {
            System.out.println("No hay Solucion");
        }

    }

    public static void CaminosPosibles(ArrayList<Camino> caminos,Object origen, GrafoDirigidoTDA mapa, int etapa,ConjuntoTDA<Vuelo> ady,Vuelo solucion[],int j,int costo,int ultimaEtapa, int cantvuelos){
        if(j< ady.capacidad()){
            Vuelo vueloaux= ady.elegir(j);
            for (int i = 0; i <= 1; i++) {
                if (i == 1) { //representa el si, cambia a los adyacentes del prox nodo
                    solucion[etapa]=vueloaux;
                    if (ultimaEtapa!=0){
                        costo+=costoEntreDosVuelos(solucion[etapa], solucion[ultimaEtapa]);
                    }
                    ultimaEtapa =etapa;
                    ConjuntoTDA adyacentes = mapa.adyacentes(vueloaux.getDestino(), vueloaux.getFecha_aterrizaje());
                    ady = adyacentes;
                    j=-1;
                }
                else {
                    solucion[etapa]=null;
                }
                if (etapa+1==cantvuelos ||ady.conjuntoVacio()) {
                    if (solucion[ultimaEtapa].getDestino().equals(origen)){
                        Camino cam = new Camino();
                        int d=0;
                        while (d<solucion.length&& d<=etapa){
                            Vuelo x = solucion[d];
                            if (x!=null) {
                                cam.Agregar(x);
                            }
                            d++;
                        }

                        caminos.add(cam);
                        cam.setCosto(costo);
                        costo=0;

                    }
                } else {
                    CaminosPosibles(caminos,origen, mapa, etapa + 1, ady, solucion,j+1,costo,ultimaEtapa,cantvuelos);
                }
            }
        }
    }

    public static boolean CombinarCaminosTripulacion(ArrayList<Tripulacion> tripulaciones,int etapa,Camino combValida[],int costo, int costoParcial,int cantVuelos,int largoSol){
        boolean solucion = false;
        int j=0;
        while(!solucion && j<tripulaciones.get(etapa).getCaminos().size()){
            combValida[etapa]= tripulaciones.get(etapa).getCaminos().get(j);
            costoParcial+=tripulaciones.get(etapa).getCaminos().get(j).getCosto();
            if(combinacionValida(combValida,etapa,costo,costoParcial)){
                int cont=0;
                for (int i=0; i<=etapa;i++){
                    cont+=combValida[i].getCaminoDeVuelos().size();
                }

                if (etapa==largoSol-1){
                    if ( cont==cantVuelos){
                        solucion=true;
                        costo=costoParcial;
                    }

                }else{
                    solucion = CombinarCaminosTripulacion(tripulaciones, etapa+1, combValida,costo, costoParcial,cantVuelos,largoSol);
                }
            }
            j++;
        }
        return solucion;

    }

    public static boolean combinacionValida(Camino comb[],int etapa,int costo, int costoParcial){
        if(costoParcial<costo || costo==0){
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
        }else{
            return false;
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