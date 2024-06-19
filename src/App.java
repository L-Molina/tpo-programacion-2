import impl.MapaCiudades;

public class App {
	public static void main(String[] args) throws Exception {
		MapaCiudades mapa = new MapaCiudades();
        mapa.inicializarMapa();

        // Caso de prueba 1: Listar provincias y ciudades
        System.out.println("Provincias y sus ciudades:");
        mapa.listarProvincias();

        // Caso de prueba 2: Agregar y eliminar ciudades
        System.out.println("\nAgregar ciudad 'Bahía Blanca' a Buenos Aires:");
        mapa.agregarCiudad("Buenos Aires", "Bahía Blanca");
        mapa.listarProvincias();

        System.out.println("\nEliminar ciudad 'Bahía Blanca' de Buenos Aires:");
        mapa.eliminarCiudad("Buenos Aires", "Bahía Blanca");
        mapa.listarProvincias();

        // Caso de prueba 3: Listar ciudades vecinas a una ciudad
        System.out.println("\nCiudades vecinas a 'CABA':");
        mapa.ciudadesVecinas("CABA");

        // Caso de prueba 4: Listar ciudades puente entre dos ciudades
        System.out.println("\nCiudades puente entre 'CABA' y 'Ciudad de Córdoba':");
        mapa.ciudadesPuente("CABA", "Ciudad de Córdoba");

        // Caso de prueba 5: Listar ciudades predecesoras a una ciudad
        System.out.println("\nCiudades predecesoras a 'Ciudad de Córdoba':");
        mapa.ciudadesPredecesoras("Ciudad de Córdoba");

        // Caso de prueba 6: Listar ciudades extremo
        System.out.println("\nCiudades extremo:");
        mapa.ciudadesExtremo();

        // Caso de prueba 7: Listar ciudades fuertemente conectadas
        System.out.println("\nCiudades fuertemente conectadas:");
        mapa.ciudadesFuertementeConectadas();

        // Caso de prueba 8: Calcular el camino más corto entre dos ciudades
        System.out.println("\nCamino más corto entre 'CABA' y 'Puerto Madryn':");
        mapa.caminoMasCorto("CABA", "Puerto Madryn");
    }
}
