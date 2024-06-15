package algoritmos;

import api.ColaTDA;
import api.PilaTDA;

public class MetodosAux {
    public static void pasarColaAPila (ColaTDA origen, PilaTDA destino) {
        while (!origen.ColaVacia()){
            destino.Apilar(origen.Primero());
            origen.Desacolar();
        }
    }
}
