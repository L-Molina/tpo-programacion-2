package impl;

import api.ConjuntoTDA;
import api.GrafoTDA;

class NodoGrafo {
    int nodo;
    NodoArista arista;
    NodoGrafo sigNodo;
}

class NodoArista {
    int etiqueta;
    NodoGrafo nodoDestino;
    NodoArista sigArista;
}

public class GrafoDinamica implements GrafoTDA {
    NodoGrafo origen;

    public void InicializarGrafo() {
        origen = null;
    }

    public void AgregarVertice (int v) {
        NodoGrafo aux = new NodoGrafo();
        aux.nodo = v;
        aux.arista = null;
        aux.sigNodo = origen;
        origen = aux;
    }

    public void AgregarArista(int v1, int v2, int peso) {
        NodoGrafo n1 = Vert2Nodo(v1);
        NodoGrafo n2 = Vert2Nodo(v2);

        NodoArista aux = new NodoArista();
        aux.etiqueta = peso;
        aux.nodoDestino = n2;
        aux.sigArista = n1.arista;
        n1.arista = aux;
    }

    private NodoGrafo Vert2Nodo(int v) {
        NodoGrafo aux = origen;
        while (aux != null && aux.nodo != v) {
            aux = aux.sigNodo;
        }
        return aux;
    }

    public void EliminarVertice(int v) {
        if (origen.nodo == v) {
            origen = origen.sigNodo;
        }

        NodoGrafo aux = origen;
        while (aux != null) {
            this.EliminarAristaNodo(aux, v);
            if (aux.sigNodo != null && aux.sigNodo.nodo == v) {
                aux.sigNodo = aux.sigNodo.sigNodo;
            }
            aux = aux.sigNodo;
        }
    }

    private void EliminarAristaNodo(NodoGrafo nodo, int v) {
        NodoArista aux = nodo.arista;

        if (aux != null) {
            if (aux.nodoDestino.nodo == v) {
                nodo.arista = aux.sigArista;
            }
            else {
                while (aux.sigArista != null && aux.sigArista.nodoDestino.nodo != v) {
                    aux = aux.sigArista;
                }
                if (aux.sigArista != null) {
                    aux.sigArista = aux.sigArista.sigArista;
                }
            }
        }
    }

    public ConjuntoTDA Vertices() {
        ConjuntoTDA c = new Conjunto();
        c.InicializarConjunto();
        NodoGrafo aux = origen;
        while (aux != null) {
            c.Agregar(aux.nodo);
            aux = aux.sigNodo;
        }
        return c;
    }

    public void EliminarArista(int v1, int v2) {
        NodoGrafo n1 = Vert2Nodo(v1);
        EliminarAristaNodo(n1, v2);
    }

    public boolean ExisteArista(int v1, int v2) {
        NodoGrafo n1 = Vert2Nodo(v1);
        NodoArista aux = n1.arista;
        while (aux != null && aux.nodoDestino.nodo != v2) {
            aux = aux.sigArista;
        }
        return aux != null;
    }

    public int PesoArista(int v1, int v2) {
        NodoGrafo n1 = Vert2Nodo(v1);

        NodoArista aux = n1.arista;
        while (aux.nodoDestino.nodo != v2) {
            aux = aux.sigArista;
        }
        return aux.etiqueta;
    }
}
