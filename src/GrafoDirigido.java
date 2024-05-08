

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {

    // Estructura de datos para almacenar los vértices y sus adyacencias
    private List<List<Integer>> adyacencias;
    private LinkedList<Integer> vertices;
    private LinkedList<Arco<T>>[] arcos;
   // private int index=0;
    private int cantArcos=0;
    private int cantVertices=0;

    public GrafoDirigido(int cantidadVertices) {
        this.adyacencias = new LinkedList<>();
        this.vertices = new LinkedList<>();
        this.arcos = (LinkedList<Arco<T>>[]) new LinkedList[cantidadVertices];
        for (int i = 0; i < cantidadVertices; i++) {
            arcos[i] = new LinkedList<>();
        }
    }

    @Override
    public void agregarVertice(int verticeId) {
        // Si el vértice ya existe, no hacemos nada
        if (contieneVertice(verticeId)) {
            return;
        }

        // Agregamos el vértice representado por una nueva lista vacía de adyacencias
        adyacencias.add(new LinkedList<>());
        // Agregamos el vértice a la lista de vértices
        vertices.add(verticeId);
        this.cantVertices+=1;
        System.out.println("se agrego vertice");

    }


    public void borrarVertice2(int verticeId) {
        if (!contieneVertice(verticeId)) {
            System.out.println("no lo encontro");
            return;

        }

        vertices.remove(Integer.valueOf(verticeId));

        // Eliminar todos los arcos que conectan con el verticeId
        for (int i = 0; i < arcos.length; i++) {
            System.out.println("entro");
            arcos[i].remove(Integer.valueOf(verticeId));
            this.cantVertices-=1;
        }

        // Eliminar la lista de arcos del verticeId
        arcos[verticeId] = null;

    }
    @Override
    public void borrarVertice(int verticeId) {
        if (!contieneVertice(verticeId)) {
            System.out.println("no lo encontro");
            return;
        }

        vertices.remove(Integer.valueOf(verticeId));

        // Verificar si verticeId es un índice válido en el array arcos
        if (verticeId >= 0 && verticeId < arcos.length) {
            // Eliminar todos los arcos que conectan con el verticeId
            System.out.println(" lo encontro");
            arcos[verticeId].clear();
            this.cantVertices -= 1;
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
            // Verificar si ambos vértices existen en el grafo
            if (!contieneVertice(verticeId1) || !contieneVertice(verticeId2)) {
                throw new IllegalArgumentException("Al menos uno de los vértices no existe en el grafo.");
            }

            // Crear el arco con la etiqueta especificada
            Arco<T> nuevoArco = new Arco<>(verticeId1, verticeId2, etiqueta);

            // Agregar el arco a la lista de arcos del vérticeId1
            if (arcos[verticeId1] == null) {
                arcos[verticeId1] = new LinkedList<>();
            }
            arcos[verticeId1].add(nuevoArco);
            this.cantArcos+=1;
            System.out.println("se agrego vertice");


    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        // Verificar si el verticeId1 existe en el grafo
        if (!contieneVertice(verticeId1)) {
            return;
        }

        // Obtener la lista de arcos del verticeId1
        LinkedList<Arco<T>> listaArcos = arcos[verticeId1];

        // Buscar y eliminar el arco que conecta verticeId1 con verticeId2
        Iterator<Arco<T>> iterador = listaArcos.iterator();
        while (iterador.hasNext()) {
            Arco<T> arco = iterador.next();
            if (arco.getVerticeDestino() == verticeId2) {
                iterador.remove();
                this.cantArcos-=1;
                break;
            }
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        // Iterar sobre todos los vértices del grafo
        Iterator<Integer> iteradorVertices = obtenerVertices();
        while (iteradorVertices.hasNext()) {
            int verticeActual = iteradorVertices.next();
            // Verificar si el vértice actual es igual al verticeId
            if (verticeActual == verticeId) {
                System.out.println("encontro vertice");
                return true;

            }
        }
        // Si no se encontró el vértice, retornar false
        return false;

         //Iteramos sobre todas las listas de adyacencias de otra forma
        //for (List<Integer> adyacentes : adyacencias) {
           // Si la lista de adyacencias contiene el vértice, retornamos true
          // if (adyacentes.contains(verticeId)) {
            //  return true;
         //   }
       // }
         //Si no encontramos el vértice en ninguna lista de adyacencias, retornamos false
       // return false;
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        // Verificar si el verticeId1 existe en el grafo
        if (!contieneVertice(verticeId1)) {
            return false;
        }

        // Obtener la lista de arcos del verticeId1
        LinkedList<Arco<T>> listaArcos = arcos[verticeId1];

        // Iterar sobre los arcos y verificar si alguno tiene verticeId2 como destino
        for (Arco<T> arco : listaArcos) {
            if (arco.getVerticeDestino() == verticeId2) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        // Verificar si el arco existe
        if (!existeArco(verticeId1, verticeId2)) {
            return null;
        }

        // Obtener la lista de arcos del verticeId1
        LinkedList<Arco<T>> listaArcos = arcos[verticeId1];

        // Iterar sobre los arcos y buscar el arco que tiene verticeId2 como destino
        for (Arco<T> arco : listaArcos) {
            if (arco.getVerticeDestino() == verticeId2) {
                return arco;
            }
        }

        // Este caso no debería ocurrir, pero en caso de falla en la implementación, devolvemos null
        return null;
    }

    @Override
    public int cantidadVertices() {

        return this.getCantVertices();
    }

    @Override
    public int cantidadArcos() {

        return this.getCantArcos();
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        System.out.println("le pasa el iterador");
        return vertices.iterator() ;

    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        // Verificar si el vértice existe en el grafo
        if (!contieneVertice(verticeId)) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }

        // Obtener la lista de adyacencias del vérticeId
        List<Integer> adyacenciasVertice = adyacencias.get(verticeId);

        // Devolver un iterador sobre la lista de adyacencias
        return adyacenciasVertice.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        // Crear una lista para almacenar todos los arcos del grafo
        List<Arco<T>> todosLosArcos = new LinkedList<>();

        // Recorrer todas las listas de arcos
        for (List<Arco<T>> lista : arcos) {
            // Si la lista de arcos no es nula, agregar todos los arcos a la lista de todos los arcos
            if (lista != null) {
                todosLosArcos.addAll(lista);
            }
        }

        // Devolver un iterador sobre la lista de todos los arcos
        return todosLosArcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        // Verificar si el vértice existe en el grafo
        if (!contieneVertice(verticeId)) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }

        // Obtener la lista de arcos del vérticeId
        List<Arco<T>> listaArcos = arcos[verticeId];

        // Devolver un iterador sobre la lista de arcos
        return listaArcos.iterator();
    }

    public int getCantArcos() {
        return cantArcos;
    }

    public int getCantVertices() {
        return cantVertices;
    }
}
