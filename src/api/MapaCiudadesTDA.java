package api;

public interface MapaCiudadesTDA {
    void inicializarMapa();
    void listarProvincias();
    void agregarCiudad(String provincia, String ciudad);
    void eliminarCiudad(String provincia, String ciudad);
    void ciudadesVecinas(String ciudad);
    void ciudadesPuente(String ciudadA, String ciudadB);
    void ciudadesPredecesoras(String ciudad);
    void ciudadesExtremo();
    void ciudadesFuertementeConectadas();
    void caminoMasCorto(String ciudadA, String ciudadB);
}
