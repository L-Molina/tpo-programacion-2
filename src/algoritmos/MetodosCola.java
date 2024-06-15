package algoritmos;

import api.ColaTDA;

public class MetodosCola {
    public void pasarCola(ColaTDA origen, ColaTDA destino) {
        while (!origen.ColaVacia()) {
            destino.Acolar(origen.Primero());
            destino.Desacolar();
        }
    }

    public static ColaTDA invertirCola(ColaTDA c) {
        int aux = c.Primero();
        c.Desacolar();
        if (!c.ColaVacia()) {
            c = invertirCola(c);
        }
        c.Acolar(aux);
        return c;
    }
}
