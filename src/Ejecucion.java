import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import tda.GrafoTDA;
import tda.impl.Grafo;

public class Ejecucion {
    public static void main(String[] args) {
        List<Tripulacion> tripulaciones = new ArrayList<>();
        List<Vuelo> vuelos = new ArrayList<>();
        Vuelo vuelo1 = new Vuelo("V1", "Aeroparque","Mendoza",new Date(), new Date());
        Vuelo vuelo5 = new Vuelo("V5", "Aeroparque","Usuahia",new Date(), new Date());
        Vuelo vuelo2 = new Vuelo("V2", "Mendoza","Jujuy",new Date(), new Date());
        Vuelo vuelo3 = new Vuelo("V3", "Jujuy","Calafate",new Date(), new Date());
        Vuelo vuelo4 = new Vuelo("V4", "Calafate","Aeroparque",new Date(), new Date());
        Vuelo vuelo6 = new Vuelo("V6", "Usuahia","Aeroparque",new Date(), new Date());
        vuelos.add(vuelo1);
        vuelos.add(vuelo5);
        vuelos.add(vuelo2);
        vuelos.add(vuelo3);
        vuelos.add(vuelo4);
        vuelos.add(vuelo6);
        Tripulacion tripulacion1= new Tripulacion("T1","Aeroparque","Aeroparque");
        Tripulacion tripulacion2= new Tripulacion("T2","Aeroparque","Aeroparque");
        tripulaciones.add(tripulacion1);
        tripulaciones.add(tripulacion2);
        asignarVuelos(vuelos,tripulaciones);
    }

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
        Vuelo v = vuelos.get(0);
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
}
