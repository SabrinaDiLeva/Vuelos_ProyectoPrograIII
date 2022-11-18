import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import martu.Vuelo;
import tda.GrafoDirigidoTDA;
import tda.impl.GrafoDirigido;
import  martu.*;

public class Ejecucion {
    public static void main(String[] args) {
        ArrayList<Tripulacion> tripulaciones = new ArrayList<>();
        List<Vuelo> vuelos = new ArrayList<>();



        Object Aeroparque=new Object();
        Object Mendoza=new Object();
        Object  Jujuy=new Object();
        Object Calafate=new Object();
        Object Usuahia=new Object();

        Vuelo vuelo1 = new Vuelo("V1", Aeroparque,Mendoza,new Date(), new Date());
        Vuelo vuelo5 = new Vuelo("V5", Aeroparque,Calafate,new Date(), new Date());
        Vuelo vuelo2 = new Vuelo("V2", Mendoza,Jujuy,new Date(), new Date());
        Vuelo vuelo3 = new Vuelo("V3", Jujuy,Aeroparque,new Date(), new Date());
        Vuelo vuelo4 = new Vuelo("V4", Calafate,Usuahia,new Date(), new Date());
        Vuelo vuelo6 = new Vuelo("V6", Usuahia,Aeroparque,new Date(), new Date());
        Vuelo vuelo7 = new Vuelo("V7", Calafate,Aeroparque,new Date(), new Date());
        vuelos.add(vuelo1);
        vuelos.add(vuelo5);
        vuelos.add(vuelo2);
        vuelos.add(vuelo3);
        vuelos.add(vuelo4);
        vuelos.add(vuelo6);
        vuelos.add(vuelo7);


        Tripulacion tripulacion1= new Tripulacion("T1",Aeroparque);
        Tripulacion tripulacion2= new Tripulacion("T2",Aeroparque);
        tripulaciones.add(tripulacion1);
        tripulaciones.add(tripulacion2);
        //asignarVuelos(vuelos,tripulaciones);
        GrafoDirigidoTDA mapa= new GrafoDirigido();
        mapa.inicializarGrafo();
        mapa.agregarVertice(Aeroparque);
        mapa.agregarVertice(Mendoza);
        mapa.agregarVertice(Jujuy);
        mapa.agregarVertice(Calafate);
        mapa.agregarVertice(Usuahia);
        mapa.agregarArista(Aeroparque,Mendoza,vuelo1);
        mapa.agregarArista(Aeroparque,Usuahia,vuelo2);
        mapa.agregarArista(Mendoza,Jujuy,vuelo3);
        mapa.agregarArista(Jujuy,Aeroparque,vuelo4);
        mapa.agregarArista(Calafate, Usuahia,vuelo5);
        mapa.agregarArista(Usuahia,Aeroparque,vuelo6);
        mapa.agregarArista(Calafate,Aeroparque,vuelo7);
        Main.AsignarPrimerVuelo(tripulaciones,mapa);
        for (Tripulacion trip: tripulaciones){
            for (Camino cam: trip.getCaminos()){
                for (Object city: cam.getCaminoDeVuelos()){
                    System.out.println(city);

                }
            }

        }

    }

    /*
    public static void archivoVuelos(){ //Metodos de lectura para archivo Vuelos
        try {
            File vuelos = new File("Vuelos.csv");
            Scanner vue = new Scanner(vuelos);
            while (vue.hasNextLine()) {
                String data = vue.nextLine();
                String [] datos=null;
                datos=data.split(",");
                SimpleDateFormat formatear=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                Vuelo vuelo=new Vuelo(datos[0],datos[1],datos[2],formatear.parse(datos[3]),formatear.parse(datos[4]));
                System.out.println(data);
            }
            vue.close();
        } catch (Exception e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
    }

    public static void archivoTripulaciones(){//Metodos de lectura para archivo Tripulaciones
        try {
            File tripulaciones = new File("Tripulaciones.csv");
            Scanner trip = new Scanner(tripulaciones);
            while (trip.hasNextLine()) {
                String data = trip.nextLine();
                String [] datos=null;
                datos=data.split(",");
                Tripulacion tripulacion=new Tripulacion(datos[0],datos[1],"");
                System.out.println(data);
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
    }

    public static GrafoTDA<Integer> archivoAeropuertos(){//Metodos de lectura para archivo Aeropuertos
        try {
            File aeropuertos = new File("Aeropuertos.csv");
            Scanner aero = new Scanner(aeropuertos);
            GrafoTDA<Integer> aeropuerto = new Grafo<>();
            aeropuerto.inicializarGrafo();
            while (aero.hasNextLine()) {
                String data = aero.nextLine();
                String[] datos = null;
                datos = data.split(",");
                //aeropuerto.agregarArista(datos[0], datos[1], Integer.parseInt(datos[2]));
                System.out.println(data);
            }
            aero.close();
            return aeropuerto;
        } catch (FileNotFoundException e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
    }

    public static void asignarVuelos(List<Vuelo>vuelos,List<Tripulacion>tripulaciones){
        /*for(Vuelo v : vuelos){
            for(Tripulacion t: tripulaciones){
                //si estan en la misma ciudad y si da bien la hora
                if(v.getOrigen()==t.getCiudadActual()){
                    v.setTripulacion(t);
                    t.setCiudadActual(v.getDestino());
                    System.out.println("Se asigno la tripulacion "+t.getCodigo()+" al vuelo "+v.getCodigo());
                    //vuelos.remove(v);
                    //asignarVuelos(vuelos,tripulaciones);
                    break;
                }
            }
        }*/

        /*Vuelo v = vuelos.get(0);
        //System.out.println("el vuelo"+v.getCodigo()+" tiene origen en "+v.getOrigen()+" y destino en "+v.getDestino());
        for(Tripulacion t: tripulaciones){
            //System.out.println("La tripulacion "+t.getCodigo()+" esta en "+t.getCiudadActual());
            //si estan en la misma ciudad y si da bien la hora
            if(v.getOrigen()==t.getCiudadActual()){
                v.setTripulacion(t);
                t.setCiudadActual(v.getDestino());
                System.out.println("Se asigno la tripulacion "+t.getCodigo()+" al vuelo "+v.getCodigo());
                //System.out.println("la tripulacion "+ t.getCodigo()+" ahora esta en "+t.getCiudadActual());
                if(vuelos.size()>1){
                    vuelos.remove(v);
                    asignarVuelos(vuelos,tripulaciones);
                }else{
                    System.out.println("Ha terminado la asignacion");
                }
                break;
            }
        }
    }



     */
}
