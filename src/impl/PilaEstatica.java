package impl;

import api.PilaTDA;

public class PilaEstatica implements PilaTDA {
    int[] vector;
    int i;

    public void InicializarPila() {
        vector = new int[100];
        i = 0;
    } 

    public void Apilar(int resultado) {
        vector[i] = resultado;
        i++;
    }

    public String Desapilar() {
        i--;
    }

    public boolean PilaVacia() {
        return (i==0);
    }

    public int Tope() {
        return (vector[i-1]);
    }
}
