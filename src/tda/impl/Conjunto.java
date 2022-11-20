package tda.impl;

import tda.ConjuntoTDA;


import java.util.*;

public class Conjunto<E> implements ConjuntoTDA<E> {
    private Set<E> conjunto;

    @Override
    public void agregar(E elemento) {
        this.conjunto.add(elemento);
    }

    @Override
    public boolean conjuntoVacio() {
        return this.conjunto.isEmpty();
    }

    @Override
    public E elegir(int x) {
        ArrayList<E> AR= new ArrayList<E>();
        AR.addAll(this.conjunto);
        return AR.get(x);
    }

    @Override
    public void inicializarConjunto() {
        this.conjunto = new HashSet<>();
    }

    @Override
    public boolean pertenece(E elemento) {
        return this.conjunto.contains(elemento);
    }

    @Override
    public void sacar(E elemento) {
        this.conjunto.remove(elemento);
    }

    @Override
    public int capacidad() {
        return this.conjunto.size();
    }


}
