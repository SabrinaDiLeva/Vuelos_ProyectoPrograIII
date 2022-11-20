package tda.impl;
import clases.*;

public class Arista<E> implements Comparable<Arista<E>> {
    private E desde;
    private E hacia;
    private Vuelo vuelo;

    public Arista(E desde, E hacia, Vuelo vuelo) {
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

    public Vuelo getVuelo() {
        return vuelo;
    }

    @Override
    public int compareTo(Arista<E> o) {
        return Integer.compare(o.getVuelo().getCodigo(), this.vuelo.getCodigo());
    }
}
