package algoritmos;

import api.PilaTDA;

public class MetodosPila {
    public static void pasarPila (PilaTDA p1, PilaTDA p2) {
        while (!p1.PilaVacia()) {
            p2.Apilar(p1.Tope());
            p1.Desapilar();
        }
    }
}
