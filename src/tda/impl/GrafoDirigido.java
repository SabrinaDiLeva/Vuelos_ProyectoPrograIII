package tda.impl;

import clases.*;
import tda.ConjuntoTDA;
import tda.GrafoDirigidoTDA;

import java.time.LocalDateTime;
import java.util.*;

public class GrafoDirigido<E> implements GrafoDirigidoTDA<E> {
    private Map<Vertice<E>, List<Arista<E>>> grafo;

    @Override
    public ConjuntoTDA<Vuelo> adyacentes(E vertice, LocalDateTime horas) { //MODIFICAE PARA
        LocalDateTime horasa=horas.plusHours(2);
        //System.out.println(horas.toString());
        Vertice<E> v = new Vertice<>(vertice);
        ConjuntoTDA<Vuelo> adyacentes = new Conjunto<>();
        adyacentes.inicializarConjunto();
        List<Arista<E>> aristas = this.grafo.get(v);
        for (Arista<E> arista : aristas) { //DEVUELVE ARISTAS POSITIVAS
            //System.out.println("dsag "+arista.getVuelo().getCodigo());
            if (arista.getVuelo().getFecha_despegue().isEqual(horasa) || arista.getVuelo().getFecha_despegue().isAfter(horasa)){ //si supera horas descanso o es mayor
                    //System.out.println("adyacente");
                    adyacentes.agregar(arista.getVuelo());




            }
        }
        return adyacentes;
    }

    
    @Override
    public void agregarArista(E vertice1, E vertice2, Vuelo vuelo) {
        Vertice<E> v1 = new Vertice<>(vertice1);
        Vertice<E> v2 = new Vertice<>(vertice2);
        List<Arista<E>> aristas1 = this.grafo.get(v1);
        List<Arista<E>> aristas2 = this.grafo.get(v2);
        if (aristas1 == null) {
            this.agregarVertice(vertice1);
            aristas1 = this.grafo.get(v1);
        }
        if (aristas2 == null) {
            this.agregarVertice(vertice2);
        }
        aristas1.add(new Arista<>(vertice1, vertice2, vuelo));
    }

    @Override
    public void agregarVertice(E vertice) {
        Vertice<E> v = new Vertice<>(vertice);
        this.grafo.put(v, new ArrayList<>());
    }

    @Override
    public E elegir() {
        Optional<Vertice<E>> vertice = this.grafo.keySet().stream().skip(new Random().nextInt(this.grafo.size())).findFirst();
        return vertice.map(Vertice::getDato).orElse(null);
    }

    @Override
    public void eliminarArista(E vertice1, E vertice2) {
        Vertice<E> v1 = new Vertice<>(vertice1);
        List<Arista<E>> aristas1 = this.grafo.get(v1);
        if (aristas1 != null) {
            Optional<Arista<E>> arista = aristas1.stream().filter(a -> a.getDesde().equals(vertice1) && a.getHacia().equals(vertice2)).findFirst();
            arista.ifPresent(aristas1::remove);
        }
    }

    @Override
    public void eliminarVertice(E vertice) {
        Vertice<E> v = new Vertice<>(vertice);
        this.grafo.remove(v);
    }

    @Override
    public boolean existeArista(E vertice1, E vertice2) {
        Vertice<E> v = new Vertice<>(vertice1);
        List<Arista<E>> aristas1 = this.grafo.get(v);
        if (aristas1 != null) {
            return aristas1.stream().anyMatch(a -> a.getDesde().equals(vertice1) && a.getHacia().equals(vertice2));
        }
        return false;
    }

    @Override
    public void inicializarGrafo() {
        this.grafo = new HashMap<>();
    }

    @Override
    public Vuelo pesoArista(E vertice1, E vertice2) {
        Vertice<E> v = new Vertice<>(vertice1);
        List<Arista<E>> aristas1 = this.grafo.get(v);
        if (aristas1 != null) {
            Optional<Arista<E>> arista = aristas1.stream().filter(a -> a.getDesde().equals(vertice1) && a.getHacia().equals(vertice2)).findFirst();
            if (arista.isPresent()) {
                return arista.get().getVuelo();
            }
        }
        return null;
    }

    @Override
    public ConjuntoTDA<E> vertices() {
        ConjuntoTDA<E> vertices = new Conjunto<>();
        vertices.inicializarConjunto();
        for (Vertice<E> vertice : this.grafo.keySet()) {
            vertices.agregar(vertice.getDato());
        }
        return vertices;
    }
}
