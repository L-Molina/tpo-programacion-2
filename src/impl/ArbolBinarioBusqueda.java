package impl;

import api.ABBTDA;
import api.ConjuntoTDA;

class NodoABB {
    int info;
    ABBTDA hijoIzq;
    ABBTDA hijoDer;
}

public class ArbolBinarioBusqueda implements ABBTDA {
    NodoABB raiz;

    public int Raiz() {
        return raiz.info;
    }

    public boolean ArbolVacio() {
        return (raiz == null);
    }

    public void InicializarArbol() {
        raiz = null;
    }

    public ABBTDA HijoDer() {
        return raiz.hijoDer;
    }

    public ABBTDA HijoIzq() {
        return raiz.hijoIzq;
    }

    public void AgregarElem(int x) {
        if (raiz == null) {
            raiz = new NodoABB();
            raiz.info = x;
            raiz.hijoIzq = new ArbolBinarioBusqueda();
            raiz.hijoIzq.InicializarArbol();
            raiz.hijoDer = new ArbolBinarioBusqueda();
            raiz.hijoDer.InicializarArbol();
        }
        else if (raiz.info > x) 
            raiz.hijoIzq.AgregarElem(x);
        else if (raiz.info < x)
            raiz.hijoDer.AgregarElem(x);
    }

    public void EliminarElem(int x) {
        if (raiz != null) {
            if (raiz.info == x && raiz.hijoIzq.ArbolVacio() && raiz.hijoDer.ArbolVacio()) {
                raiz = null;
            }
        }
        else if (raiz.info == x && !raiz.hijoIzq.ArbolVacio()) {
            raiz.info = this.mayor(raiz.hijoIzq);
            raiz.hijoIzq.EliminarElem(raiz.info);
        }
        else if (raiz.info == x && raiz.hijoIzq.ArbolVacio()) {
            raiz.info = this.menor(raiz.hijoDer);
            raiz.hijoDer.EliminarElem(raiz.info);
        }
        else if (raiz.info < x) {
            raiz.hijoDer.EliminarElem(x);
        }
        else {
            raiz.hijoIzq.EliminarElem(x);
        }
    }

    private int mayor(ABBTDA a) {
        if (a.HijoDer().ArbolVacio())
            return a.Raiz();
        else
            return mayor(a.HijoDer());
    }

    private int menor(ABBTDA a) {
        if (a.HijoIzq().ArbolVacio())
            return a.Raiz();
        else
            return menor(a.HijoIzq());
    }

    public void preOrder(ABBTDA a) {
        if (!a.ArbolVacio()) {
            System.out.println(a.Raiz());
            preOrder(a.HijoIzq());
            preOrder(a.HijoDer());
        }
    }

    public void inOrder(ABBTDA a) {
        if (!a.ArbolVacio()) {
            inOrder(a.HijoIzq());
            System.out.println(a.Raiz());
            inOrder(a.HijoDer());
        }
    }

    public void postOrder(ABBTDA a) {
        if (!a.ArbolVacio()) {
            postOrder(a.HijoIzq());
            postOrder(a.HijoDer());
            System.out.println(a.Raiz());
        }
    }

    public int Contar(ABBTDA a) {
        if (a.ArbolVacio()) {
            return 0;
        }
        else {
            return (1 + Contar(a.HijoIzq())) + Contar(a.HijoDer());
        }
    }

    public int calcularProfundidad(ABBTDA t, int x) {
        if (t.ArbolVacio()) {
            return 0;
        }
        else if (t.Raiz() == x) {
            return 0;
        }
        else if (t.Raiz() > x) {
            return (1 + this.calcularProfundidad(t.HijoIzq(), x));
        }
        else {
            return (1 + this.calcularProfundidad(t.HijoDer(), x));
        }
    }

    public boolean existeElementoEnABB(ABBTDA t, int x) {
        if (t.ArbolVacio()) {
            return false;
        }
        else if (t.Raiz() == x) {
            return true;
        }
        else if (t.Raiz() > x) {
            return this.existeElementoEnABB(t.HijoIzq(), x);
        }
        else {
            return this.existeElementoEnABB(t.HijoDer(), x);
        }
    }

    public ConjuntoTDA nodosPares(ABBTDA a) {
        ConjuntoTDA r = new Conjunto();
        r.InicializarConjunto();

        if (!a.ArbolVacio()) {
            if (a.Raiz() % 2 == 0) {
                r.Agregar(a.Raiz());
            }
            ConjuntoTDA rI = nodosPares(a.HijoIzq());
            ConjuntoTDA rD = nodosPares(a.HijoDer());
            while (!rI.ConjuntoVacio()) {
                int x = rI.Elegir();
                r.Agregar(x);
                rI.Sacar(x);
            }
            while (!rD.ConjuntoVacio()) {
                int x = rD.Elegir();
                r.Agregar(x);
                rD.Sacar(x);
            }
        }
        return r;
    }

    public int Contar(ArbolBinarioBusqueda a) {
        if (a.ArbolVacio()) {
            return 0;
        }
        else {
            return (1 + Contar(a.HijoIzq()) + Contar(a.HijoDer()));
        }
    }

    public int calcularProfundidad(ArbolBinarioBusqueda t, int x) {
        if (t.ArbolVacio()) {
            return 0;
        }
        else if (t.Raiz() == x) {
            return 0;
        }
        else if (t.Raiz() > x) {
            return (1 + this.calcularProfundidad(t.HijoIzq(), x));
        }
        else {
            return (1 + this.calcularProfundidad(t.HijoDer(), x));
        }
    }
}