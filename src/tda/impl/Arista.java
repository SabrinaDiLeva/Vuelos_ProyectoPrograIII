package tda.impl;

import martu.Vuelo;

public class Arista<E> implements Comparable<Arista<E>> {
    private E desde;
    private E hacia;
    private Vuelo vuelo;

    public Arista(E desde, E hacia,Vuelo vuelo) {
        this.desde = desde;
        this.hacia = hacia;
        this.vuelo = vuelo;
    }

    public E getDesde() {
        return desde;
    }

    public E getHacia() {
        return hacia;
    }

    public Vuelo getPeso() {
        return vuelo;
    }


    @Override
    public int compareTo(Arista<E> o) {
        return 0;
    }
}
