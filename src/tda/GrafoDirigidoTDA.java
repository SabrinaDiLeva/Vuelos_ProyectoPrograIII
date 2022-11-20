package tda;

import clases.*;

public interface GrafoDirigidoTDA<E> {
    ConjuntoTDA<E> adyacentes(E vertice);

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