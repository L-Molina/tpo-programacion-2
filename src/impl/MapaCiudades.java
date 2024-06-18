package impl;

import api.MapaCiudadesTDA;
import java.util.ArrayList;

class NodoCiudad {
    String ciudad;
    NodoCiudad hijoIzq;
    NodoCiudad hijoDer;
    NodoRuta rutas;

    NodoCiudad(String ciudad) {
        this.ciudad = ciudad;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.rutas = null;
    }
}

class NodoProvincia {
    String provincia;
    NodoProvincia hijoIzq;
    NodoProvincia hijoDer;
    NodoCiudad ciudades;

    NodoProvincia(String provincia) {
        this.provincia = provincia;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.ciudades = null;
    }
}

class NodoRuta {
    String destino;
    int distancia;
    NodoRuta siguiente;

    NodoRuta(String destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
        this.siguiente = null;
    }
}

public class MapaCiudades implements MapaCiudadesTDA {
    public NodoProvincia raizProvincias;

    public void inicializarMapa() {
        raizProvincias = null;
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales() {
        agregarProvincia("Buenos Aires");
        agregarCiudad("Buenos Aires", "La Plata");
        agregarCiudad("Buenos Aires", "Mar del Plata");
        agregarCiudad("Buenos Aires", "CABA");
        agregarCiudad("Buenos Aires", "Tandil");

        agregarProvincia("Córdoba");
        agregarCiudad("Córdoba", "Ciudad de Córdoba");
        agregarCiudad("Córdoba", "Río Cuarto");
        agregarCiudad("Córdoba", "Villa Carlos Paz");

        agregarProvincia("Salta");
        agregarCiudad("Salta", "Cafayate");

        agregarProvincia("Chubut");
        agregarCiudad("Chubut", "Rawson");
        agregarCiudad("Chubut", "Trelew");
        agregarCiudad("Chubut", "Puerto Madryn");

        agregarRuta("CABA", "Mar del Plata", 400);
        agregarRuta("CABA", "La Plata", 60);
        agregarRuta("CABA", "Tandil", 350);
        agregarRuta("CABA", "Ciudad de Córdoba", 1300);
        agregarRuta("Mar del Plata", "CABA", 500);
        agregarRuta("Mar del Plata", "Ciudad de Córdoba", 1800);
        agregarRuta("La Plata", "Ciudad de Córdoba", 1500);
        agregarRuta("La Plata", "Rawson", 2700);
        agregarRuta("Tandil", "CABA", 480);
        agregarRuta("Ciudad de Córdoba", "Rawson", 2800);
        agregarRuta("Ciudad de Córdoba", "Río Cuarto", 200);
        agregarRuta("Río Cuarto", "Puerto Madryn", 1150);
        agregarRuta("Villa Carlos Paz", "Ciudad de Córdoba", 40);
        agregarRuta("Villa Carlos Paz", "Río Cuarto", 250);
        agregarRuta("Villa Carlos Paz", "Trelew", 1400);
        agregarRuta("Rawson", "Villa Carlos Paz", 1200);
        agregarRuta("Rawson", "Cafayate", 2200);
        agregarRuta("Rawson", "Trelew", 20);
    }

    // Métodos para manejar provincias
    public void agregarProvincia(String provincia) {
        raizProvincias = agregarProvinciaRec(raizProvincias, provincia);
    }

    public NodoProvincia agregarProvinciaRec(NodoProvincia nodo, String provincia) {
        if (nodo == null) {
            return new NodoProvincia(provincia);
        } else if (provincia.compareTo(nodo.provincia) < 0) {
            nodo.hijoIzq = agregarProvinciaRec(nodo.hijoIzq, provincia);
        } else if (provincia.compareTo(nodo.provincia) > 0) {
            nodo.hijoDer = agregarProvinciaRec(nodo.hijoDer, provincia);
        }
        return nodo;
    }

    public void eliminarProvincia(String provincia) {
        raizProvincias = eliminarProvinciaRec(raizProvincias, provincia);
    }

    public NodoProvincia eliminarProvinciaRec(NodoProvincia nodo, String provincia) {
        if (nodo == null) return null;

        if (provincia.compareTo(nodo.provincia) < 0) {
            nodo.hijoIzq = eliminarProvinciaRec(nodo.hijoIzq, provincia);
        } else if (provincia.compareTo(nodo.provincia) > 0) {
            nodo.hijoDer = eliminarProvinciaRec(nodo.hijoDer, provincia);
        } else {
            if (nodo.hijoIzq == null) return nodo.hijoDer;
            if (nodo.hijoDer == null) return nodo.hijoIzq;

            NodoProvincia temp = buscarMin(nodo.hijoDer);
            nodo.provincia = temp.provincia;
            nodo.ciudades = temp.ciudades;
            nodo.hijoDer = eliminarProvinciaRec(nodo.hijoDer, temp.provincia);
        }
        return nodo;
    }

    public NodoProvincia buscarMin(NodoProvincia nodo) {
        while (nodo.hijoIzq != null) nodo = nodo.hijoIzq;
        return nodo;
    }

    public void agregarCiudad(String provincia, String ciudad) {
        NodoProvincia nodoProvincia = buscarProvincia(raizProvincias, provincia);
        if (nodoProvincia != null) {
            nodoProvincia.ciudades = agregarCiudadRec(nodoProvincia.ciudades, ciudad);
        }
    }

    public NodoCiudad agregarCiudadRec(NodoCiudad nodo, String ciudad) {
        if (nodo == null) {
            return new NodoCiudad(ciudad);
        } else if (ciudad.compareTo(nodo.ciudad) < 0) {
            nodo.hijoIzq = agregarCiudadRec(nodo.hijoIzq, ciudad);
        } else if (ciudad.compareTo(nodo.ciudad) > 0) {
            nodo.hijoDer = agregarCiudadRec(nodo.hijoDer, ciudad);
        }
        return nodo;
    }

    public void eliminarCiudad(String provincia, String ciudad) {
        NodoProvincia nodoProvincia = buscarProvincia(raizProvincias, provincia);
        if (nodoProvincia != null) {
            nodoProvincia.ciudades = eliminarCiudadRec(nodoProvincia.ciudades, ciudad);
        }
    }

    public NodoCiudad eliminarCiudadRec(NodoCiudad nodo, String ciudad) {
        if (nodo == null) return null;

        if (ciudad.compareTo(nodo.ciudad) < 0) {
            nodo.hijoIzq = eliminarCiudadRec(nodo.hijoIzq, ciudad);
        } else if (ciudad.compareTo(nodo.ciudad) > 0) {
            nodo.hijoDer = eliminarCiudadRec(nodo.hijoDer, ciudad);
        } else {
            if (nodo.hijoIzq == null) return nodo.hijoDer;
            if (nodo.hijoDer == null) return nodo.hijoIzq;

            NodoCiudad temp = buscarMinCiudad(nodo.hijoDer);
            nodo.ciudad = temp.ciudad;
            nodo.hijoIzq = temp.hijoIzq;
            nodo.hijoDer = eliminarCiudadRec(nodo.hijoDer, temp.ciudad);
        }
        return nodo;
    }

    public NodoCiudad buscarMinCiudad(NodoCiudad nodo) {
        while (nodo.hijoIzq != null) nodo = nodo.hijoIzq;
        return nodo;
    }

    public String[] listarProvincias() {
        ArrayList<String> provinciasList = new ArrayList<>();
        listarProvinciasRec(raizProvincias, provinciasList);
        return provinciasList.toArray(new String[0]);
    }

    public void listarProvinciasRec(NodoProvincia nodo, ArrayList<String> provinciasList) {
        if (nodo != null) {
            listarProvinciasRec(nodo.hijoIzq, provinciasList);
            provinciasList.add(nodo.provincia);
            listarProvinciasRec(nodo.hijoDer, provinciasList);
        }
    }

    public String[] listarCiudades(String provincia) {
        NodoProvincia nodoProvincia = buscarProvincia(raizProvincias, provincia);
        if (nodoProvincia != null) {
            ArrayList<String> ciudadesList = new ArrayList<>();
            listarCiudadesRec(nodoProvincia.ciudades, ciudadesList);
            return ciudadesList.toArray(new String[0]);
        }
        return new String[0];
    }

    public void listarCiudadesRec(NodoCiudad nodo, ArrayList<String> ciudadesList) {
        if (nodo != null) {
            listarCiudadesRec(nodo.hijoIzq, ciudadesList);
            ciudadesList.add(nodo.ciudad);
            listarCiudadesRec(nodo.hijoDer, ciudadesList);
        }
    }

    public NodoProvincia buscarProvincia(NodoProvincia nodo, String provincia) {
        if (nodo == null) return null;
        if (provincia.equals(nodo.provincia)) return nodo;
        if (provincia.compareTo(nodo.provincia) < 0) return buscarProvincia(nodo.hijoIzq, provincia);
        return buscarProvincia(nodo.hijoDer, provincia);
    }

    // Métodos para manejar rutas
    public void agregarRuta(String origen, String destino, int distancia) {
        NodoCiudad nodoOrigen = buscarCiudad(origen);
        if (nodoOrigen != null) {
            nodoOrigen.rutas = agregarRutaRec(nodoOrigen.rutas, destino, distancia);
        }
    }

    public NodoRuta agregarRutaRec(NodoRuta nodo, String destino, int distancia) {
        if (nodo == null) {
            return new NodoRuta(destino, distancia);
        } else if (destino.compareTo(nodo.destino) < 0) {
            nodo.siguiente = agregarRutaRec(nodo.siguiente, destino, distancia);
        }
        return nodo;
    }

    public void eliminarRuta(String origen, String destino) {
        NodoCiudad nodoOrigen = buscarCiudad(origen);
        if (nodoOrigen != null) {
            nodoOrigen.rutas = eliminarRutaRec(nodoOrigen.rutas, destino);
        }
    }

    public NodoRuta eliminarRutaRec(NodoRuta nodo, String destino) {
        if (nodo == null) return null;

        if (destino.equals(nodo.destino)) {
            return nodo.siguiente;
        } else {
            nodo.siguiente = eliminarRutaRec(nodo.siguiente, destino);
            return nodo;
        }
    }

    public NodoCiudad buscarCiudad(String ciudad) {
        NodoCiudad nodoCiudad = null;
        buscarCiudadRec(raizProvincias, ciudad, nodoCiudad);
        return nodoCiudad;
    }

    public void buscarCiudadRec(NodoProvincia nodoProvincia, String ciudad, NodoCiudad nodoCiudad) {
        if (nodoProvincia != null) {
            nodoCiudad = buscarCiudadEnProvincia(nodoProvincia.ciudades, ciudad);
            if (nodoCiudad == null) {
                buscarCiudadRec(nodoProvincia.hijoIzq, ciudad, nodoCiudad);
                buscarCiudadRec(nodoProvincia.hijoDer, ciudad, nodoCiudad);
            }
        }
    }

    public NodoCiudad buscarCiudadEnProvincia(NodoCiudad nodo, String ciudad) {
        if (nodo == null) return null;
        if (ciudad.equals(nodo.ciudad)) return nodo;
        if (ciudad.compareTo(nodo.ciudad) < 0) return buscarCiudadEnProvincia(nodo.hijoIzq, ciudad);
        return buscarCiudadEnProvincia(nodo.hijoDer, ciudad);
    }

    public String[] ciudadesVecinas(String ciudad) {
        NodoCiudad nodoCiudad = buscarCiudad(ciudad);
        if (nodoCiudad != null) {
            ArrayList<String> vecinasList = new ArrayList<>();
            NodoRuta ruta = nodoCiudad.rutas;
            while (ruta != null) {
                vecinasList.add(ruta.destino);
                ruta = ruta.siguiente;
            }
            return vecinasList.toArray(new String[0]);
        }
        return new String[0];
    }

    public String[] ciudadesPuente(String ciudadA, String ciudadB) {
        NodoCiudad nodoCiudadA = buscarCiudad(ciudadA);
        NodoCiudad nodoCiudadB = buscarCiudad(ciudadB);
        ArrayList<String> puentes = new ArrayList<>();
        if (nodoCiudadA != null && nodoCiudadB != null) {
            NodoRuta rutaA = nodoCiudadA.rutas;
            while (rutaA != null) {
                NodoCiudad nodoIntermedio = buscarCiudad(rutaA.destino);
                if (nodoIntermedio != null && buscarRuta(nodoIntermedio.rutas, ciudadB) != null) {
                    puentes.add(rutaA.destino);
                }
                rutaA = rutaA.siguiente;
            }
        }
        return puentes.toArray(new String[0]);
    }

    public NodoRuta buscarRuta(NodoRuta nodo, String destino) {
        while (nodo != null) {
            if (nodo.destino.equals(destino)) {
                return nodo;
            }
            nodo = nodo.siguiente;
        }
        return null;
    }

    public String[] ciudadesPredecesoras(String ciudad) {
        ArrayList<String> predecesorasList = new ArrayList<>();
        buscarPredecesorasRec(raizProvincias, ciudad, predecesorasList);
        return predecesorasList.toArray(new String[0]);
    }

    public void buscarPredecesorasRec(NodoProvincia nodoProvincia, String ciudad, ArrayList<String> predecesorasList) {
        if (nodoProvincia != null) {
            buscarPredecesorasEnCiudad(nodoProvincia.ciudades, ciudad, predecesorasList);
            buscarPredecesorasRec(nodoProvincia.hijoIzq, ciudad, predecesorasList);
            buscarPredecesorasRec(nodoProvincia.hijoDer, ciudad, predecesorasList);
        }
    }

    public void buscarPredecesorasEnCiudad(NodoCiudad nodo, String ciudad, ArrayList<String> predecesorasList) {
        if (nodo != null) {
            NodoRuta ruta = nodo.rutas;
            while (ruta != null) {
                if (ruta.destino.equals(ciudad)) {
                    predecesorasList.add(nodo.ciudad);
                }
                ruta = ruta.siguiente;
            }
            buscarPredecesorasEnCiudad(nodo.hijoIzq, ciudad, predecesorasList);
            buscarPredecesorasEnCiudad(nodo.hijoDer, ciudad, predecesorasList);
        }
    }

    public String[] ciudadesExtremo() {
        ArrayList<String> extremosList = new ArrayList<>();
        buscarExtremosRec(raizProvincias, extremosList);
        return extremosList.toArray(new String[0]);
    }

    public void buscarExtremosRec(NodoProvincia nodoProvincia, ArrayList<String> extremosList) {
        if (nodoProvincia != null) {
            buscarExtremosEnCiudad(nodoProvincia.ciudades, extremosList);
            buscarExtremosRec(nodoProvincia.hijoIzq, extremosList);
            buscarExtremosRec(nodoProvincia.hijoDer, extremosList);
        }
    }

    public void buscarExtremosEnCiudad(NodoCiudad nodo, ArrayList<String> extremosList) {
        if (nodo != null) {
            if (nodo.rutas == null) {
                extremosList.add(nodo.ciudad);
            }
            buscarExtremosEnCiudad(nodo.hijoIzq, extremosList);
            buscarExtremosEnCiudad(nodo.hijoDer, extremosList);
        }
    }

    public String[] ciudadesFuertementeConectadas() {
        ArrayList<String> conectadasList = new ArrayList<>();
        buscarConectadasRec(raizProvincias, conectadasList);
        return conectadasList.toArray(new String[0]);
    }

    public void buscarConectadasRec(NodoProvincia nodoProvincia, ArrayList<String> conectadasList) {
        if (nodoProvincia != null) {
            buscarConectadasEnCiudad(nodoProvincia.ciudades, conectadasList);
            buscarConectadasRec(nodoProvincia.hijoIzq, conectadasList);
            buscarConectadasRec(nodoProvincia.hijoDer, conectadasList);
        }
    }

    public void buscarConectadasEnCiudad(NodoCiudad nodo, ArrayList<String> conectadasList) {
        if (nodo != null) {
            NodoRuta ruta = nodo.rutas;
            while (ruta != null) {
                NodoCiudad nodoDestino = buscarCiudad(ruta.destino);
                if (nodoDestino != null && buscarRuta(nodoDestino.rutas, nodo.ciudad) != null) {
                    conectadasList.add(nodo.ciudad + " <-> " + ruta.destino);
                }
                ruta = ruta.siguiente;
            }
            buscarConectadasEnCiudad(nodo.hijoIzq, conectadasList);
            buscarConectadasEnCiudad(nodo.hijoDer, conectadasList);
        }
    }

    public Ruta caminoMasCorto(String ciudadA, String ciudadB) {
        // Implementación de búsqueda del camino más corto utilizando un algoritmo de búsqueda.
        // Se puede usar una variación de Dijkstra para encontrar el camino más corto.
        return null; // Para simplificar, esta implementación está fuera del alcance de este ejemplo.
    }
}
