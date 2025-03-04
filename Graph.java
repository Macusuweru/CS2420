package assign07;
import java.util.*;

/**
 * A basic implementation of an undirected graph using adjacency lists.
 * Vertices store unique values of generic type T.
 *
 * @author Maxwe//
 * @version March 3, 2025
 * @param <T> the type of values stored in the vertices
 */
public class Graph<T> {
    private Map<T, List<T>> graph;
    
    /**
     * Initializes an empty graph.
     */
    public Graph() {
        graph = new HashMap<>();
    }
    
    /**
     * Adds a vertex with the given value if it doesn't already exist.
     *
     * @param value the value to be stored in the new vertex
     * @return true if the vertex was added, false if it already existed
     */
    public boolean addVertex(T value) {
        if (!graph.containsKey(value)) {
            graph.put(value, new ArrayList<>());
            return true;
        }
        return false;
    }
    
    /**
     * Adds an undirected edge between vertices with values v1 and v2.
     *
     * @param v1 the value of the origin vertex
     * @param v2 the value of the target vertex
     * @return true if the edge was added, false if either vertex doesn't exist
     */
    public boolean addEdge(T v1, T v2) {
        if (!graph.containsKey(v1) || !graph.containsKey(v2))
            return false;
        
        if (!graph.get(v1).contains(v2))
            graph.get(v1).add(v2);
        return true;
    }
    
    /**
     * Removes the edge between vertices with values v1 and v2.
     *
     * @param v1 the value of the origin vertex
     * @param v2 the value of the target vertex
     * @return true if the edge was removed, false if either vertex doesn't exist
     */
    public boolean removeEdge(T v1, T v2) {
        if (graph.containsKey(v1) && graph.containsKey(v2)) {
            graph.get(v1).remove(v2);
            return true;
        }
        return false;
    }
    
    /**
     * Removes a vertex and all its edges from the graph.
     *
     * @param value the value of the vertex to remove
     * @return true if the vertex was removed, false if it didn't exist
     */
    public boolean removeVertex(T value) {
        if (!graph.containsKey(value)) {
            return false;
        }
        
        for (T vertex : graph.keySet()) {
            graph.get(vertex).remove(value);
        }
        
        graph.remove(value);
        return true;
    }
    
    /**
     * Returns all neighbors of a vertex.
     *
     * @param value the value of the vertex
     * @return list of neighbor vertex values, or null if the vertex doesn't exist
     */
    public List<T> getNeighbors(T value) {
        if (graph.containsKey(value)) {
            return new ArrayList<>(graph.get(value));
        }
        return null;
    }

    /**
     * Depth first search implementation
     * 
     * @param source the source vertex
     * @param destination the destination vectex
     * @return boolean if there is a path from source to target
     */
    public boolean depthFirstSearch(T source, T destination) {
        if (source.equals(destination)) return true;
        if (graph.get(source) == null) return false;
        List<T> seen = new ArrayList<>();
        seen.add(source);
        return dfsRecursive(source, destination, seen);
    }
    /**
     * Private driver method for depth first search
     * @param source 
     * @param destination
     * @param seen
     */
    private dfsRecursive(T source, T destination, List<T> seen) {
        List<T> neighbors = getNeighbors(source);
        if (neighbors == null) return false;
        for (T i: neighbors) {
            if (!seen.contains(i)) {
                if (i.equals(destination))
                    return true;
                seen.add(i);
                if (dfsRecursive(i, destination, seen))
                    return true;
            }
        }
        return false;
    }

    /**
     * Breadth first search implementation
     * 
     * @param source the source vertex
     * @param destination the destination vectex
     * @return a list containing all vertex values from source to destination
     * @throws IllegalArgumentException if there is no path between source and destination
     */
    public List<T> breadthFirstSearch(T source, T destination) {
        if (source.equals(destination)) return true;
        if (graph.get(source) == null) return false;

        Map<T,Integer> dist = new HashMap<>();
        Map<T, T> prev = new HashMap<>();
        Queue<T> q = new LinkedList<>();

        for (T t: graph.keySet())
            dist.put(t, -1);
        dist.put(source, 0);
        q.offer(source);

        while (!q.isEmpty()) {
            T t = q.poll();
            for (T adj: getNeighbors(t)) {
                if (!prev.keySet().contains(adj)) {
                    prev.put(adj, t);
                    if (adj.equals(destination))
                        return bfsHelper(source, destination, prev);
                    q.offer(adj);
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Private helper method for running back down the path
     * @param source the source vertex
     * @param destination the destination vertex
     * @param prev a map containing all necessary connections between the destination and the source
     * @return a list containing all vertex values from source to destination
     */
    private List<T> bfsHelper(T source, T destination, Map<T, T> prev) {
        List<T> result = new ArrayList<>();
        while (!current.equals(source)) {
            result.add(destination);
            destination = prev.get(destination);
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Implementation of topological sort
     * @param
     */
    public List<T> topoSort() {
        List<T> result = new ArrayList<>();

        Map<T, Integer> indegree = new HashMap<>();
        for (T t: graph.keySet())
            indegree.put(t, 0);
        for (T t: graph.keySet())
            for (T u: getNeighbors(t))
                indegree.put(u, indegree.get(u) + 1);

        Queue<T> q = new LinkedList<>();
        for (T t: graph.keySet())
            if (indegree.get(t) == 0)
                q.offer(t);

        while (!q.isEmpty()) {
            T current = q.poll();
            result.add(current);
            for (T t: getNeighbors(current)) {
                Integer get = indegree.get(t) - 1;
                indegree.put(t, get);
                if (get == 0)
                    q.offer(current);
            }
        }

        return (result);
    }
}