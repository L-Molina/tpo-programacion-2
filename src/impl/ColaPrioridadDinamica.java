package impl;

import api.ColaPrioridadTDA;

public class ColaPrioridadDinamica implements ColaPrioridadTDA {
    NodoPrioridad mayorPrioridad;

    public void InicializarCola() {
        mayorPrioridad = null;
    }

    public void AcolarPrioridad(int x, int prioridad) {
        NodoPrioridad nuevo = new NodoPrioridad();
        nuevo.info = x;
        nuevo.prioridad = prioridad;
        if(mayorPrioridad == null || prioridad > mayorPrioridad.prioridad) {
            nuevo.sig = mayorPrioridad;
            mayorPrioridad = nuevo;
        } else {
            NodoPrioridad aux = mayorPrioridad;
            while(aux.sig != null && aux.sig.prioridad >= prioridad) {
                aux = aux.sig;
            }
            nuevo.sig = aux.sig;
            aux.sig = nuevo;
        }
    }

    public void Desacolar() {
        mayorPrioridad = mayorPrioridad.sig;
    }

    public int Primero() {
        return mayorPrioridad.info;
    }

    public boolean ColaVacia() {
        return (mayorPrioridad == null);
    }

    public int Prioridad() {
        return mayorPrioridad.prioridad;
    }
}
