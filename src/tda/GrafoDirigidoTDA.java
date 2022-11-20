package tda;

import clases.*;

import java.time.LocalDateTime;

public interface GrafoDirigidoTDA<E> {
    ConjuntoTDA<Vuelo> adyacentes(E vertice, LocalDateTime hora);

    void agregarArista(E vertice1, E vertice2, Vuelo vuelo);

    void agregarVertice(E vertice);

    E elegir();

    void eliminarArista(E vertice1, E vertice2);

    void eliminarVertice(E vertice);

    boolean existeArista(E vertice1, E vertice2);

    void inicializarGrafo();

    Vuelo pesoArista(E vertice1, E vertice2);

    ConjuntoTDA<E> vertices();

}