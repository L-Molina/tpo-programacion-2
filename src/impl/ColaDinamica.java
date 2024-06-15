package impl;

import api.ColaTDA;

public class ColaDinamica implements ColaTDA {
    Nodo primero;
    Nodo ultimo;

    public void InicializarCola(){
        primero = null;
        ultimo = null;
    }

    public void Acolar(int x) {
        Nodo aux = new Nodo();
        aux.info = x;
        aux.sig = null;
        if (ultimo != null) {
            primero = ultimo;
        }
    }

    public void Desacolar() {
        primero = primero.sig;
        if (primero == null){
            ultimo = null;
        }
    }

    public boolean ColaVacia() {
        return (ultimo == null);
    }

    public int Primero() {
        return primero.info;
    }
}
