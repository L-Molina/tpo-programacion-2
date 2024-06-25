package impl;

import api.MapaCiudadesTDA;
import java.util.*;

public class MapaCiudades implements MapaCiudadesTDA {
    // declaramos las hashmaps para las provinvias y las rutas
    private Map<String, List<String>> provincias = new HashMap<>();
    private Map<String, Map<String, Integer>> rutas = new HashMap<>();

    public void inicializarMapa() {
        // agregamos provincias y ciudades al hashmap
        provincias.put("Buenos Aires", new ArrayList<>(Arrays.asList("La Plata", "Mar Del Plata", "CABA", "Tandil")));
        provincias.put("Córdoba", new ArrayList<>(Arrays.asList("Ciudad de Córdoba", "Río Cuarto", "Villa Carlos Paz")));
        provincias.put("Salta", new ArrayList<>(Arrays.asList("Cafayate")));
        provincias.put("Chubut", new ArrayList<>(Arrays.asList("Rawson", "Trelew", "Puerto Madryn")));

        // agregamos rutas entre ciudades al otro hashmap
        agregarRuta("CABA", "Mar Del Plata", 400);
        agregarRuta("CABA", "La Plata", 60);
        agregarRuta("CABA", "Tandil", 350);
        agregarRuta("CABA", "Ciudad de Córdoba", 1300);
        agregarRuta("Mar Del Plata", "CABA", 500);
        agregarRuta("Mar Del Plata", "Ciudad de Córdoba", 1800);
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
        // agregamos una nueva ruta, verificando si no existe 
        rutas.putIfAbsent(origen, new HashMap<>());
        rutas.get(origen).put(destino, distancia);
    }

    public void listarProvincias() {
        for (Map.Entry<String, List<String>> entry : provincias.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }

    public void agregarCiudad(String provincia, String ciudad) {
        // agregamos una nueva ciudad, verificando si no existe 
        provincias.putIfAbsent(provincia, new ArrayList<>());
        provincias.get(provincia).add(ciudad);
    }

    public void eliminarCiudad(String provincia, String ciudad) {
        // verificamos si una ciudad existe y la eliminamos del hashmap 
        if (provincias.containsKey(provincia)) {
            provincias.get(provincia).remove(ciudad);
        }
    }

    public void ciudadesVecinas(String ciudad) {
        // listamos las ciudades vecinas al argumento ciudad
        if (rutas.containsKey(ciudad)) {
            System.out.println(String.join(", ", rutas.get(ciudad).keySet()));
        }
    }

    public void ciudadesPuente(String ciudadA, String ciudadB) {
        // listamos la o las ciudades puente entre los argumentos ciudadA y ciudadB
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
        // creamos una arrayList para las ciudades predecesoras
        List<String> predecesoras = new ArrayList<>();
        for (String key : rutas.keySet()) {
            if (rutas.get(key).containsKey(ciudad)) {
                predecesoras.add(key);
            }
        }
        System.out.println(String.join(", ", predecesoras));
    }

    public void ciudadesExtremo() {
        // creamos una arrayList para las ciudades extremo
        List<String> extremos = new ArrayList<>();
        for (String ciudad : rutas.keySet()) {
            if (rutas.get(ciudad).isEmpty()) {
                extremos.add(ciudad);
            }
        }
        System.out.println(String.join(", ", extremos));
    }

    public void ciudadesFuertementeConectadas() {
        // creamos una arrayList para las ciudades fuertemente conectadas
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
        // verificamos si las ciudades existen en el mapa
        if (!rutas.containsKey(ciudadA) || !rutas.containsKey(ciudadB)) {
            System.out.println("Una o ambas ciudades no existen en el mapa.");
            return;
        }
    
        // algoritmo de dijkstra
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
            if (distancias.get(actual) == Integer.MAX_VALUE) {
                break;
            }
            visitados.add(actual);
    
            if (rutas.containsKey(actual)) {
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
        }
    
        // creamos una arrayList para el camino mas cortp
        List<String> camino = new ArrayList<>();
        for (String at = ciudadB; at != null; at = predecesores.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);
    
        // verificamos si existe camino entre los argumentos ciudadA y ciudadB. 
        // Si existen, listamos el camino mas corto entre ambas y la distancia total
        if (!distancias.containsKey(ciudadB) || distancias.get(ciudadB) == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre " + ciudadA + " y " + ciudadB);
        } else {
            System.out.println("Camino más corto entre " + ciudadA + " y " + ciudadB + ": " + String.join(" -> ", camino));
            System.out.println("Distancia total: " + distancias.get(ciudadB) + " km");
        }
    }    
}
