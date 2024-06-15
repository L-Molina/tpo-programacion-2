package algoritmos;

import api.ConjuntoTDA;

public class MetodosConjunto {
    public boolean SonConjuntosIguales(ConjuntoTDA c1, ConjuntoTDA c2) {
        int elemento;
        boolean sonIguales = true;
        while (!c1.ConjuntoVacio() && !c2.ConjuntoVacio() && sonIguales) {
            elemento = c1.Elegir();
            if (!c2.Pertenece(elemento)) {
                sonIguales = false;
            } else {
                c1.Sacar(elemento);
                c2.Sacar(elemento);
            }
        }
        if (c1.ConjuntoVacio() && c2.ConjuntoVacio()) {
            return true;
        } else {
            return false;
        }
    }
}
