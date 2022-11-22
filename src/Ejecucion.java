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
    }

    public static void main(String[] args) {
        ArrayList<Tripulacion> tripulaciones= cargarTripulaciones();
        int cantvuelos=0;
        GrafoDirigidoTDA<Object> mapa= cargarGrafo(cantvuelos);

        Main.AsignarPrimerVuelo(tripulaciones,mapa,cantvuelos);



    }
}
