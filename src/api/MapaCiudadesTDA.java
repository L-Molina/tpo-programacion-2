package api;

public interface MapaCiudadesTDA {
    void inicializarMapa();
    void agregarProvincia(String provincia);
    void eliminarProvincia(String provincia);
    void agregarCiudad(String provincia, String ciudad);
    void eliminarCiudad(String provincia, String ciudad);
    String[] listarProvincias();
    String[] listarCiudades(String provincia);
    void agregarRuta(String origen, String destino, int distancia);
    void eliminarRuta(String origen, String destino);
    String[] ciudadesVecinas(String ciudad);
    String[] ciudadesPuente(String ciudadA, String ciudadB);
    String[] ciudadesPredecesoras(String ciudad);
    String[] ciudadesExtremo();
    String[] ciudadesFuertementeConectadas();
    Ruta caminoMasCorto(String ciudadA, String ciudadB);
}

class Ruta {
    String[] ciudadesIntermedias;
    int distanciaTotal;
}
