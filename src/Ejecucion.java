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
        ArrayList<Tripulacion> tripulacionesA = new ArrayList<>();
        try {
            File tripulaciones = new File("Tripulaciones.csv");
            Scanner trip = new Scanner(tripulaciones);
            while (trip.hasNextLine()) {
                String data = trip.nextLine();
                String[] datos = null;
                datos = data.split(",");
                Tripulacion tripulacion = new Tripulacion(datos[0], datos[1]);
                tripulacionesA.add(tripulacion);
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
        return tripulacionesA;
    }
    public static GrafoDirigidoTDA<Object>  cargarGrafo() {
        GrafoDirigidoTDA<Object> mapa= new GrafoDirigido<Object>();
        mapa.inicializarGrafo();
        ConjuntoTDA<String> conjt= new Conjunto<String>();
        conjt.inicializarConjunto();
        try {
            File aeropuertos = new File("Aeropuertos.csv");
            Scanner trip = new Scanner(aeropuertos);
            while (trip.hasNextLine()) {
                String data = trip.nextLine();
                String[] datos = null;
                datos = data.split(",");
                conjt.agregar(datos[0]);
                conjt.agregar(datos[1]);
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
        while(!conjt.conjuntoVacio()){
            String aero=conjt.elegir(0);
            mapa.agregarVertice(aero);
            conjt.sacar(aero);
        }
        try {
            File vuelos = new File("Vuelo.csv");
            Scanner trip = new Scanner(vuelos);
            while (trip.hasNextLine()) {
                String data = trip.nextLine();
                String[] datos = null;
                datos = data.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String[] cod=datos[0].split(" ");
                Vuelo vuel= new Vuelo(Integer.valueOf(cod[1]),datos[1],datos[2],LocalDateTime.parse(datos[3], formatter),LocalDateTime.parse(datos[4], formatter));
                mapa.agregarArista(datos[1],datos[2],vuel);
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado.");
            e.printStackTrace();
        }
        return mapa;
    }
    public static void main(String[] args) {
        ArrayList<Tripulacion> tripulaciones= cargarTripulaciones();
        GrafoDirigidoTDA mapa= cargarGrafo();
        Main.AsignarPrimerVuelo(tripulaciones,mapa);

    }
}

