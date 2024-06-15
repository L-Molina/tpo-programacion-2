package impl;

import api.ColaTDA;
import api.ConjuntoTDA;
import api.PilaTDA;

public class Conjunto implements ConjuntoTDA {
    int[] a;
    int cant;

    public void Agregar(int x) {
        if (!this.Pertenece(x)) {
            a[cant] = x;
            cant++;
        }
    }

    public boolean ConjuntoVacio() {
        return cant == 0;
    }

    public int Elegir() {
        return a[cant - 1];
    }

    public void InicializarConjunto() {
        a = new int[100];
        cant = 0;
    }

    public boolean Pertenece(int x) {
        int i = 0;
        while (i < cant && a[i] != x)
            i++;
        return (i < cant);
    }

    public void Sacar(int x) {
        int i = 0;
        while (i < cant && a[i] != x)
            i++;
        if (i < cant) {
            a[i] = a[cant - 1];
            cant--;
        }
    }

    public boolean esColaCapicua(ColaTDA c) {
        ColaTDA aux1 = new ColaDinamica();
        aux1.InicializarCola();
        int cant = 0;
        PilaTDA p = new PilaDinamica();
        p.InicializarPila();

        while (!c.ColaVacia()) {
            aux1.Acolar(c.Primero());
            cant++;
            c.Desacolar();
        }

        int mitad = cant / 2;

        while (!aux1.ColaVacia() && mitad > 0) {
            p.Apilar(aux1.Primero());
            aux1.Desacolar();
            mitad--;
        }

        if ((cant % 2) != 0) {
            aux1.Desacolar();
        }

        while ((!p.PilaVacia() && !aux1.ColaVacia())) {
            if (p.Tope() != aux1.Primero()) {
                return false;
            }
            p.Desapilar();
            aux1.Desacolar();
        }
        return (p.PilaVacia() && aux1.ColaVacia());
    }

    public void eliminarRepetidos(PilaTDA p) {
        
    }
}