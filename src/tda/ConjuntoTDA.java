package tda;

public interface ConjuntoTDA<E> {
    void agregar(E elemento);

    boolean conjuntoVacio();

    E elegir(int x);

    void inicializarConjunto();

    boolean pertenece(E elemento);

    void sacar(E elemento);

    int capacidad();


}
