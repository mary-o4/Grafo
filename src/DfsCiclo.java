import java.util.*;

public class DfsCiclo<T> {

    private GrafoDirigido<T> grafo;
    private boolean tieneCiclo;

    public DfsCiclo(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
        this.tieneCiclo = false;
    }

    public boolean realizarDFS(int verticeInicial) {
        Set<Integer> visitados = new HashSet<>();
        Set<Integer> enProceso = new HashSet<>();
        tieneCiclo = false;
        realizarDfsRecursivo(verticeInicial, visitados, enProceso);

        if (tieneCiclo) {
            System.out.println("Se ha encontrado un ciclo en el grafo.");
        } else {
            System.out.println("No se ha encontrado ningún ciclo en el grafo.");
        }

        return tieneCiclo;
    }

    private void realizarDfsRecursivo(int vertice, Set<Integer> visitados, Set<Integer> enProceso) {
        visitados.add(vertice);
        enProceso.add(vertice);

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        while (adyacentes.hasNext()) {
            int vecino = adyacentes.next();
            if (!visitados.contains(vecino)) {
                realizarDfsRecursivo(vecino, visitados, enProceso);
            } else if (enProceso.contains(vecino)) {// por q si ya esta agregado es q tiene un ciclo
                tieneCiclo = true;
                return;
            }
        }
        enProceso.remove(vertice);// si ya se visitaron todos los adyacentes al inicial lo remueve
    }

}