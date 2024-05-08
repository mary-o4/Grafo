import java.util.*;

public class Bfs<T> {

    private GrafoDirigido<T> grafo;

    public Bfs(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
    }

    public void realizarBFS(int verticeInicial) {
        Set<Integer> visitados = new HashSet<>();//Se crea un conjunto visitados para almacenar los vértices que se han visitado durante el recorrido
        Queue<Integer> cola = new LinkedList<>();//para almacenar los vértices que se deben visitar en el próximo nivel del recorrido

        visitados.add(verticeInicial);// Se marca el vértice inicial como visitado y se agrega
        cola.offer(verticeInicial);//Se agrega el vértice inicial a la cola para iniciar el recorrido

        while (!cola.isEmpty()) {//mientras la cola no esté vacía, lo que significa que aún quedan vértices por visitar.
            int verticeActual = cola.poll();//Se extrae y remueve el vértice frontal de la cola, que será el próximo vértice a visitar.
            System.out.println("Visitando vértice: " + verticeActual);//Se imprime en la consola el vértice que se está visitando en este momento.

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeActual);//iterador sobre los vértices adyacentes al vértice actual en el grafo
            while (adyacentes.hasNext()) {//mientras haya vertices q visitar
                int vecino = adyacentes.next();//Se obtiene el siguiente vértice adyacente al vértice actual
                if (!visitados.contains(vecino)) {//Se verifica si el vértice adyacente no ha sido visitado previamente
                    visitados.add(vecino);//se agrega al conjunto visitados
                    cola.offer(vecino);//se agrega el vértice adyacente a la cola, para que se visite en el próximo nivel del recorrido
                    //Esto asegura que se visite cada vértice en el orden en que se descubren, manteniendo la propiedad de BFS de visitar primero
                    // todos los vértices a una distancia dada antes de pasar a los vértices a una distancia mayor.
                }
            }
        }
    }
}