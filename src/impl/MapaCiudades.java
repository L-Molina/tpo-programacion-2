package impl;

import api.MapaCiudadesTDA;
import java.util.*;

public class MapaCiudades implements MapaCiudadesTDA {
    private Map<String, List<String>> provincias = new HashMap<>();
    private Map<String, Map<String, Integer>> rutas = new HashMap<>();

    public void inicializarMapa() {
        // Provincias y ciudades
        provincias.put("Buenos Aires", new ArrayList<>(Arrays.asList("La Plata", "Mar del Plata", "CABA", "Tandil")));
        provincias.put("Córdoba", new ArrayList<>(Arrays.asList("Ciudad de Córdoba", "Río Cuarto", "Villa Carlos Paz")));
        provincias.put("Salta", new ArrayList<>(Arrays.asList("Cafayate")));
        provincias.put("Chubut", new ArrayList<>(Arrays.asList("Rawson", "Trelew", "Puerto Madryn")));

        // Rutas entre ciudades
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

    private void agregarRuta(String origen, String destino, int distancia) {
        rutas.putIfAbsent(origen, new HashMap<>());
        rutas.get(origen).put(destino, distancia);
    }

    public void listarProvincias() {
        for (Map.Entry<String, List<String>> entry : provincias.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }

    public void agregarCiudad(String provincia, String ciudad) {
        provincias.putIfAbsent(provincia, new ArrayList<>());
        provincias.get(provincia).add(ciudad);
    }

    public void eliminarCiudad(String provincia, String ciudad) {
        if (provincias.containsKey(provincia)) {
            provincias.get(provincia).remove(ciudad);
        }
    }

    public void ciudadesVecinas(String ciudad) {
        if (rutas.containsKey(ciudad)) {
            System.out.println(String.join(", ", rutas.get(ciudad).keySet()));
        }
    }

    public void ciudadesPuente(String ciudadA, String ciudadB) {
        List<String> puentes = new ArrayList<>();
        if (rutas.containsKey(ciudadA)) {
            for (String ciudad : rutas.get(ciudadA).keySet()) {
                if (rutas.containsKey(ciudad) && rutas.get(ciudad).containsKey(ciudadB)) {
                    puentes.add(ciudad);
                }
            }
        }
        System.out.println(String.join(", ", puentes));
    }

    public void ciudadesPredecesoras(String ciudad) {
        List<String> predecesoras = new ArrayList<>();
        for (String key : rutas.keySet()) {
            if (rutas.get(key).containsKey(ciudad)) {
                predecesoras.add(key);
            }
        }
        System.out.println(String.join(", ", predecesoras));
    }

    public void ciudadesExtremo() {
        List<String> extremos = new ArrayList<>();
        for (String ciudad : rutas.keySet()) {
            if (rutas.get(ciudad).isEmpty()) {
                extremos.add(ciudad);
            }
        }
        System.out.println(String.join(", ", extremos));
    }

    public void ciudadesFuertementeConectadas() {
        List<String> conectadas = new ArrayList<>();
        for (String origen : rutas.keySet()) {
            for (String destino : rutas.get(origen).keySet()) {
                if (rutas.containsKey(destino) && rutas.get(destino).containsKey(origen)) {
                    conectadas.add(origen + " - " + destino);
                }
            }
        }
        System.out.println(String.join(", ", conectadas));
    }

    public void caminoMasCorto(String ciudadA, String ciudadB) {
        // Implementación del algoritmo de Dijkstra
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        Set<String> visitados = new HashSet<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (String ciudad : rutas.keySet()) {
            if (ciudad.equals(ciudadA)) {
                distancias.put(ciudad, 0);
            } else {
                distancias.put(ciudad, Integer.MAX_VALUE);
            }
            pq.add(new AbstractMap.SimpleEntry<>(ciudad, distancias.get(ciudad)));
        }

        while (!pq.isEmpty()) {
            String actual = pq.poll().getKey();
            visitados.add(actual);

            for (Map.Entry<String, Integer> vecino : rutas.get(actual).entrySet()) {
                if (!visitados.contains(vecino.getKey())) {
                    int nuevaDistancia = distancias.get(actual) + vecino.getValue();
                    if (nuevaDistancia < distancias.get(vecino.getKey())) {
                        distancias.put(vecino.getKey(), nuevaDistancia);
                        predecesores.put(vecino.getKey(), actual);
                        pq.add(new AbstractMap.SimpleEntry<>(vecino.getKey(), nuevaDistancia));
                    }
                }
            }
        }

        // Imprimir el camino más corto
        List<String> camino = new ArrayList<>();
        for (String at = ciudadB; at != null; at = predecesores.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);

        if (distancias.get(ciudadB) == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre " + ciudadA + " y " + ciudadB);
        } else {
            System.out.println("Camino más corto entre " + ciudadA + " y " + ciudadB + ": " + String.join(" -> ", camino));
            System.out.println("Distancia total: " + distancias.get(ciudadB) + " km");
        }
    }
}
