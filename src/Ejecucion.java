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

    public static  GrafoDirigidoTDA<Object> cargarGrafo(int cont) {
        GrafoDirigidoTDA<Object> mapa= new GrafoDirigido<Object>();
        mapa.inicializarGrafo();
        ConjuntoTDA<String> conjt= new Conjunto<String>();
        conjt.inicializarConjunto();
        try {
            File aeropuertos = new File("C:\\Users\\Fernando\\IdeaProjects\\TPF\\Vuelos_ProyectoPrograIII\\src\\Aeropuertos.csv");
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
                    //System.out.println(data);
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
            File archivoVuelos = new File("C:\\Users\\Fernando\\IdeaProjects\\TPF\\Vuelos_ProyectoPrograIII\\src\\Vuelossss.csv");
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
                    //System.out.println(data);
                    String[]fecha1= datos[3].split("[ /:]");
                   // System.out.println(fecha1[2]+" "+fecha1[1]+" "+fecha1[0]+" "+fecha1[3]+" "+fecha1[4]);
                    String[]fecha2=datos[4].split("[ /:]");
                    String[] cod = datos[0].split(" ");
                   // System.out.println("xd"+cod[1]);
                    Vuelo vuel = new Vuelo(Integer.parseInt(cod[1]), datos[1], datos[2], LocalDateTime.of(Integer.parseInt(fecha1[2]),Integer.parseInt(fecha1[1]),Integer.parseInt(fecha1[0]),Integer.parseInt(fecha1[3]),Integer.parseInt(fecha1[4])), LocalDateTime.of(Integer.parseInt(fecha2[2]),Integer.parseInt(fecha2[1]),Integer.parseInt(fecha2[0]),Integer.parseInt(fecha2[3]),Integer.parseInt(fecha2[4])));
                    //System.out.println("vuwlo   "+vuel.toString()+"  "+vuel.getCodigo()+""+vuel.getOrigen()+""+vuel.getDestino()+""+vuel.getFecha_despegue()+""+vuel.getFecha_aterrizaje());
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
        /*ArrayList<Tripulacion> tripulaciones= cargarTripulaciones();
        int cantvuelos=0;
        GrafoDirigidoTDA<Object> mapa= cargarGrafo(cantvuelos);

        Main.AsignarPrimerVuelo(tripulaciones,mapa,cantvuelos);
        */

        Tripulacion t1 = new Tripulacion("T1", "Aeroparque");
        Tripulacion t2 = new Tripulacion("T2", "Aeroparque");
        ArrayList<Tripulacion> tripulaciones= new ArrayList<>();
        tripulaciones.add(t1);
        tripulaciones.add(t2);

        Object Aeroparque= new Object();
        Object Mendoza= new Object();
        Object Calafate= new Object();
        Object Usuahia= new Object();
        Object Jujuy= new Object();
        GrafoDirigidoTDA mapa = new GrafoDirigido();
        mapa.agregarVertice(Aeroparque);
        mapa.agregarVertice(Mendoza);
        mapa.agregarVertice(Calafate);
        mapa.agregarVertice(Usuahia);
        mapa.agregarVertice(Jujuy);

        Vuelo v1 = new Vuelo(1001, Aeroparque, Mendoza, LocalDateTime.of(2022,11,22,12,00), LocalDateTime.of(2022,11,22,15,30));
        Vuelo v2 = new Vuelo(1002, Mendoza, Calafate, LocalDateTime.of(2022,11,22,18,00), LocalDateTime.of(2022,11,22,19,30));
        Vuelo v3 = new Vuelo(1002, Calafate, Aeroparque, LocalDateTime.of(2022,11,22,22,00), LocalDateTime.of(2022,11,22,23,30));
        Vuelo v4 = new Vuelo(1002, Aeroparque, Jujuy, LocalDateTime.of(2022,11,22,10,00), LocalDateTime.of(2022,11,22,11,30));
        Vuelo v5 = new Vuelo(1002, Jujuy, Mendoza, LocalDateTime.of(2022,11,22,14,00), LocalDateTime.of(2022,11,22,15,00));
        Vuelo v6 = new Vuelo(1002, Mendoza, Aeroparque, LocalDateTime.of(2022,11,22,17,30), LocalDateTime.of(2022,11,22,20,00));
        
        mapa.agregarArista(Aeroparque, Mendoza, v1);
        mapa.agregarArista(Mendoza, Calafate, v2);
        mapa.agregarArista(Calafate, Aeroparque, v3);
        mapa.agregarArista(Aeroparque, Jujuy, v4);
        mapa.agregarArista(Jujuy, Mendoza, v5);
        mapa.agregarArista(Mendoza, Aeroparque, v6);
        
        Main.AsignarPrimerVuelo(tripulaciones,mapa,6);

    }
}
