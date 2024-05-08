import java.util.*;

public class Dfs<T> {

    private GrafoDirigido<T> grafo;

    public Dfs(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
    }

    public void realizarDFS(int verticeInicial) {
        Set<Integer> visitados = new HashSet<>();//utilizo un hashset por que no incluye repetidos
        realizarDFSRecursivo(verticeInicial, visitados);//voy con mi hashset y vertice inicial al recursivo
    }

    private void realizarDFSRecursivo(int vertice, Set<Integer> visitados) {
        visitados.add(vertice);
        System.out.println(vertice);// se imprime el valor del vértice actual en la consola. Esto es útil para visualizar el recorrido DFS y ver en qué orden se visitan los vértices.

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);//iterador sobre los vértices adyacentes al vértice actual
        while (adyacentes.hasNext()) {//bucle itera sobre todos los vértices adyacentes al vértice actual.
            int vecino = adyacentes.next();//En cada iteración, se obtiene el siguiente vértice adyacente.
            if (!visitados.contains(vecino)) {//Se verifica si el vértice adyacente no ha sido visitado previamente
                realizarDFSRecursivo(vecino, visitados);//Si el vértice adyacente no ha sido visitado, se llama recursivamente al método
            }
        }
    }
}
