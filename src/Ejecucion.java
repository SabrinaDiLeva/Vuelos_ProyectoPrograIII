import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import clases.Vuelo;
import tda.*;
import clases.*;
import tda.impl.Conjunto;
import tda.impl.GrafoDirigido;

public class Ejecucion {
    public static ArrayList<Tripulacion> cargarTripulaciones() {
        ArrayList<Tripulacion> tripulaciones = new ArrayList<>();
        try {
            Scanner trip = new Scanner(new File("C:\\Users\\Fernando\\IdeaProjects\\Pruevas\\src\\Tripulaciones.csv"));
            boolean a=true;
            while (trip.hasNextLine()) {
                if(a){
                    trip.nextLine();
                }else {
                    String data = trip.nextLine();
                    String[] datos = null;
                    datos = data.split(",");
                    Tripulacion tripulacion = new Tripulacion(datos[0], datos[1]);
                    tripulaciones.add(tripulacion);
                }
                a=false;
            }
            trip.close();
        }catch (Exception e) {
            System.out.println("Error inesperado 1.");
            e.printStackTrace();
        }
        return tripulaciones;
    }
    /*public static GrafoDirigidoTDA<Object> cargarGrafo(List<Vuelo> vueloss) {
        GrafoDirigidoTDA<Object> mapa= new GrafoDirigido<Object>();
        mapa.inicializarGrafo();
        ConjuntoTDA<String> conjt= new Conjunto<String>();
        conjt.inicializarConjunto();
        try {
            File aeropuertos = new File("C:\\Users\\Fernando\\IdeaProjects\\Pruevas\\src\\Aeropuertos.csv");
            Scanner trip = new Scanner(aeropuertos);
            boolean a=true;
            while (trip.hasNextLine()) {
                if(a) {
                    trip.nextLine();
                }else {
                    String data = trip.nextLine();
                    String[] datos = null;
                    datos = data.split(",");
                    conjt.agregar(datos[0]);
                    conjt.agregar(datos[1]);
                    System.out.println(data);
                }
                a=false;
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado1.");
            e.printStackTrace();
        }
        while(!conjt.conjuntoVacio()){
            String aero=conjt.elegir(0);
            mapa.agregarVertice(aero);
            conjt.sacar(aero);
        }
        try {
            File vuelos = new File("C:\\Users\\Fernando\\IdeaProjects\\Pruevas\\src\\VV.csv");
            Scanner trip = new Scanner(vuelos);
            boolean a=true;
            while (trip.hasNextLine()) {
                if(a){
                    trip.nextLine();
                }else {
                    String data = trip.nextLine();
                    String[] datos = null;
                    datos = data.split(",");
                    System.out.println(data);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    String[] cod = datos[0].split(" ");
                    System.out.println(datos[3]);
                    Vuelo vuel = new Vuelo(Integer.valueOf(cod[1]), datos[1], datos[2], LocalDateTime.parse(datos[3], formatter), LocalDateTime.parse(datos[4], formatter));
                    vueloss.add(vuel);
                    mapa.agregarArista(datos[1], datos[2], vuel);
                }
                a=false;
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
        return mapa;
    }*/

    public static void main(String[] args) {
        ArrayList<Tripulacion> tripulaciones= cargarTripulaciones();
        for(Tripulacion trip:tripulaciones){
            System.out.println(trip.getCodigo());
        }
        /*GrafoDirigidoTDA mapa= cargarGrafo();
        ConjuntoTDA c=mapa.vertices();
        while (!c.conjuntoVacio()){
            System.out.println(c.elegir(0));
            c.sacar(c.elegir(0));
        }

        Main.AsignarPrimerVuelo(tripulaciones,mapa);*/

    }
}

