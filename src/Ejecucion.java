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
    int cantvuelos=0;


    public static ArrayList<Tripulacion> cargarTripulaciones() {
        ArrayList<Tripulacion> tripulaciones = new ArrayList<>();
        try {
            Scanner trip = new Scanner(new File("/Users/martutoffoletto/Documents/GitHub/Vuelos_ProyectoPrograIII/src/Tripulaciones.csv"));
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

    public static  GrafoDirigidoTDA<Object> cargarGrafo(Ejecucion contador) {
        GrafoDirigidoTDA<Object> mapa= new GrafoDirigido<Object>();
        mapa.inicializarGrafo();
        ConjuntoTDA<String> conjt= new Conjunto<String>();
        conjt.inicializarConjunto();
        try {
            File aeropuertos = new File("/Users/martutoffoletto/Documents/GitHub/Vuelos_ProyectoPrograIII/src/Aeropuertos.csv"); //Camino de Archivo
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
                }
                a=false;
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
            File archivoVuelos = new File("/Users/martutoffoletto/Documents/GitHub/Vuelos_ProyectoPrograIII/src/Vuelos.csv"); //Camino de Archivo
            Scanner trip = new Scanner(archivoVuelos);
            boolean a=true;

            while (trip.hasNextLine()) {
                if(a){
                    trip.nextLine();
                }else {
                    contador.cantvuelos++;
                    String data = trip.nextLine();
                    String[] datos = null;
                    datos = data.split(",");
                    String[]fecha1= datos[3].split("[ /:]");
                    String[]fecha2=datos[4].split("[ /:]");
                    String[] cod = datos[0].split(" ");
                    Vuelo vuel = new Vuelo(Integer.parseInt(cod[1]), datos[1], datos[2], LocalDateTime.of(Integer.parseInt(fecha1[2]),Integer.parseInt(fecha1[1]),Integer.parseInt(fecha1[0]),Integer.parseInt(fecha1[3]),Integer.parseInt(fecha1[4])), LocalDateTime.of(Integer.parseInt(fecha2[2]),Integer.parseInt(fecha2[1]),Integer.parseInt(fecha2[0]),Integer.parseInt(fecha2[3]),Integer.parseInt(fecha2[4])));
                    mapa.agregarArista(datos[1],datos[2], vuel);
                }
                a=false;
            }
            trip.close();
        } catch (Exception e) {
            System.out.println("Error inesperado ");
            e.printStackTrace();
        }
        return mapa;
    }

    public static void main(String[] args) {
        Ejecucion cont= new Ejecucion();
        ArrayList<Tripulacion> tripulaciones= cargarTripulaciones();
        GrafoDirigidoTDA<Object> mapa= cargarGrafo(cont);
        Main.AsignarPrimerVuelo(tripulaciones,mapa,cont.cantvuelos);



    }


}
