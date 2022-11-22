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
    /*public static ArrayList<Tripulacion> cargarTripulaciones() {
        ArrayList<Tripulacion> tripulaciones = new ArrayList<>();
        try {
            Scanner trip = new Scanner(new File("C:\\Users\\Fernando\\IdeaProjects\\TPF\\Vuelos_ProyectoPrograIII\\src\\Tripulaciones.csv"));
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
    // Archivo Vuelos y Aeropuertos
    public static  GrafoDirigidoTDA<Object> cargarGrafo(int cont) {
        GrafoDirigidoTDA<Object> mapa= new GrafoDirigido<Object>();
        mapa.inicializarGrafo();
        ConjuntoTDA<String> conjt= new Conjunto<String>();
        conjt.inicializarConjunto();
        try {
            File aeropuertos = new File("C:\\Users\\Fernando\\IdeaProjects\\TPF\\Vuelos_ProyectoPrograIII\\src\\Aeropuertos.csv"); //Camino de Archivo
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
                    System.out.println(datos[0]+" "+datos[1]);
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
            File archivoVuelos = new File("C:\\Users\\Fernando\\IdeaProjects\\TPF\\Vuelos_ProyectoPrograIII\\src\\Vuelosss.csv"); //Camino de Archivo
            Scanner trip = new Scanner(archivoVuelos);
            boolean a=true;

            while (trip.hasNextLine()) {
                if(a){
                    trip.nextLine();
                }else {
                    cont++;
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
    }*/

    public static void main(String[] args) {
        //Carga de listas de Archivos y Vuelos
        ArrayList<Tripulacion> tripulaciones = new ArrayList<>();
        List<Vuelo> vuelos = new ArrayList<>();

        //Carga Aeropuertos

        Object Aeroparque=new Object();
        Object Mendoza=new Object();
        Object  Jujuy=new Object();
        Object Calafate=new Object();
        Object Usuahia=new Object();

        //Carga Vuelos

        Vuelo vuelo1 = new Vuelo(21, Aeroparque,Mendoza, LocalDateTime.of(2023,12,19,12,30), LocalDateTime.of(2023,12,19,12,30));
        Vuelo vuelo5 = new Vuelo(45, Aeroparque,Calafate,LocalDateTime.of(2023,12,19,12,30), LocalDateTime.of(2023,12,19,12,30));
        Vuelo vuelo2 = new Vuelo(26, Mendoza,Jujuy,LocalDateTime.of(2024,12,19,12,30), LocalDateTime.of(2024,12,19,12,30));
        Vuelo vuelo3 = new Vuelo(83, Jujuy,Aeroparque,LocalDateTime.of(2025,12,19,12,30), LocalDateTime.of(2025,12,19,12,30));
        Vuelo vuelo4 = new Vuelo(90, Calafate,Usuahia,LocalDateTime.of(2024,12,19,12,30), LocalDateTime.of(2024,12,19,12,30));
        Vuelo vuelo6 = new Vuelo(63, Usuahia,Aeroparque,LocalDateTime.of(2025,12,19,12,30), LocalDateTime.of(2025,12,19,12,30));
        
        vuelos.add(vuelo1);
        vuelos.add(vuelo5);
        vuelos.add(vuelo2);
        vuelos.add(vuelo3);
        vuelos.add(vuelo4);
        vuelos.add(vuelo6);


        //Carga Tripulaciones

        Tripulacion tripulacion1= new Tripulacion("T1",Aeroparque);
        Tripulacion tripulacion2= new Tripulacion("T2",Aeroparque);
        tripulaciones.add(tripulacion1);
        tripulaciones.add(tripulacion2);
        //Inicializamos mapa de Aeropuertos
        GrafoDirigidoTDA mapa=new GrafoDirigido();
        mapa.inicializarGrafo();
        mapa.agregarVertice(Aeroparque);
        mapa.agregarVertice(Mendoza);
        mapa.agregarVertice(Jujuy);
        mapa.agregarVertice(Calafate);
        mapa.agregarVertice(Usuahia);
        mapa.agregarArista(Aeroparque,Mendoza,vuelo1);
        mapa.agregarArista(Aeroparque,Calafate,vuelo5);
        mapa.agregarArista(Mendoza,Jujuy,vuelo2);
        mapa.agregarArista(Jujuy,Aeroparque,vuelo3);
        mapa.agregarArista(Calafate, Usuahia,vuelo4);
        mapa.agregarArista(Usuahia,Aeroparque,vuelo6);

        //Carga con Archivos
        //ArrayList<Tripulacion> tripulaciones= cargarTripulaciones();
//      GrafoDirigidoTDA mapa= cargarGrafo(cantvuelos);

        int cantvuelos=6;
        Main.AsignarPrimerVuelo(tripulaciones,mapa,cantvuelos);



    }
}
