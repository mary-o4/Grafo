import java.util.*;

public class LongestPathInDag<T> {

    private GrafoDirigido<T> grafo;

    public LongestPathInDag(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> encontrarCaminoMasLargo(int inicio, int destino) {
        List<Integer> mejorCamino = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        dfs(inicio, destino, visitados, caminoActual, mejorCamino);

        return mejorCamino;
    }

    private void dfs(int actual, int destino, Set<Integer> visitados, List<Integer> caminoActual, List<Integer> mejorCamino) {
        visitados.add(actual);
        caminoActual.add(actual);

        //Se comprueba si el vértice actual es el destino. Si lo es, se verifica si el camino actual es más largo que el mejor camino
        // encontrado hasta el momento. Si es así, se actualiza el mejor camino. Si no es el destino, se continúa explorando los vértices
        // adyacentes.
        if (actual == destino) {
            if (caminoActual.size() > mejorCamino.size()) {
                mejorCamino.clear();
                mejorCamino.addAll(caminoActual);
            }
        } else {
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);//se obtienen los adyacentes del actual
            while (adyacentes.hasNext()) {
                int vecino = adyacentes.next();//se toma el siguiente adyacente
                if (!visitados.contains(vecino)) {//Se verifica si el vecino no ha sido visitado.
                    // Si es así, se realiza una llamada recursiva al método dfs para explorar este vecino.
                    dfs(vecino, destino, visitados, caminoActual, mejorCamino);
                }
            }
        }
        //Al finalizar la exploración de los vértices adyacentes al vértice actual, se elimina el vértice actual tanto
        // de la lista de vértices visitados como del camino actual para retroceder y explorar otros caminos posibles.
        visitados.remove(actual);
        caminoActual.remove(caminoActual.size() - 1);
    }

    //-------------------------------METODO DEVUELVE VERTICES CON CAMINO HACIA V-------------------------------------
    public List<Integer> encontrarVerticesConCaminoHacia(int destino) {
        List<Integer> verticesConCaminoHacia = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        DFS_desde(destino, verticesConCaminoHacia, visitados);
        return verticesConCaminoHacia;
    }

    private void DFS_desde(int v, List<Integer> verticesConCaminoHacia, Set<Integer> visitados) {
        visitados.add(v);
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);//se obtienen los adyacentes del actual
        while (adyacentes.hasNext()) {
            int vecino = adyacentes.next();//se toma el siguiente adyacente
            if (!visitados.contains(vecino)) {
                DFS_desde(vecino, verticesConCaminoHacia, visitados);
                verticesConCaminoHacia.add(vecino);
            }
        }
    }

}
