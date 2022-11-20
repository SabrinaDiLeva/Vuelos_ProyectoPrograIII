import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clases.Vuelo;
import tda.*;
import clases.*;
import tda.impl.GrafoDirigido;

public class Ejecucion {
    public static void main(String[] args) {
        ArrayList<Tripulacion> tripulaciones = new ArrayList<>();
        List<Vuelo> vuelos = new ArrayList<>();

        Object Aeroparque=new Object();
        System.out.println(Aeroparque.toString());
        Object Mendoza=new Object();
        System.out.println(Mendoza.toString());
        Object  Jujuy=new Object();
        System.out.println(Jujuy.toString());
        Object Calafate=new Object();
        System.out.println(Calafate.toString());
        Object Usuahia=new Object();
        System.out.println(Usuahia.toString());

        Vuelo vuelo1 = new Vuelo(21, Aeroparque,Mendoza, LocalDateTime.of(2023,12,19,12,30), LocalDateTime.of(2023,12,19,12,30));
        Vuelo vuelo5 = new Vuelo(45, Aeroparque,Calafate,LocalDateTime.of(2023,12,19,12,30), LocalDateTime.of(2023,12,19,12,30));
        Vuelo vuelo2 = new Vuelo(26, Mendoza,Jujuy,LocalDateTime.of(2024,12,19,12,30), LocalDateTime.of(2024,12,19,12,30));
        Vuelo vuelo3 = new Vuelo(83, Jujuy,Aeroparque,LocalDateTime.of(2025,12,19,12,30), LocalDateTime.of(2025,12,19,12,30));
        Vuelo vuelo4 = new Vuelo(90, Calafate,Usuahia,LocalDateTime.of(2024,12,19,12,30), LocalDateTime.of(2024,12,19,12,30));
        Vuelo vuelo6 = new Vuelo(63, Usuahia,Aeroparque,LocalDateTime.of(2025,12,19,12,30), LocalDateTime.of(2025,12,19,12,30));
        Vuelo vuelo7 = new Vuelo(56, Calafate,Aeroparque,LocalDateTime.of(2026,12,19,12,30), LocalDateTime.of(2026,12,19,12,30));
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
        mapa.agregarArista(Calafate,Aeroparque,vuelo7);
        Main.AsignarPrimerVuelo(tripulaciones,mapa);

    }
}

