package assign07;

import java.util.*;

/**
 * A basic implementation of an undirected graph using adjacency lists.
 * Vertices store unique values of generic type T.
 *
 * @author Maxwe// and Ashley Pedersen
 * @version March 3, 2025
 */
public class GraphUtility {
    /**
     * Finds whether two vertices in a graph are connected.
     * @param sources list of sources
     * @param destinations list of destinations
     * @param srcData the data in the source (start) vertex
     * @param dstData the data in the destination vertex
     * @return True if a path exists, false if no path exists
     * @param <T> the type of the values inside vertexes
     * @throws IllegalArgumentException if the sources and destination lists are different lengths or if the graph doesn't
     * contain the source or destination.
     */
    public static <T> boolean areConnected(List<T> sources, List<T> destinations, T srcData, T dstData) {
        if (sources.size() != destinations.size()) throw new IllegalArgumentException();

        if (!sources.contains(srcData) && !destinations.contains(srcData)) throw new IllegalArgumentException();
        if (!sources.contains(dstData) && !destinations.contains(dstData)) throw new IllegalArgumentException();

        Graph<T> g = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            g.addVertex(sources.get(i));
            g.addVertex(destinations.get(i));
            g.addEdge(sources.get(i), destinations.get(i));
        }

        return g.depthFirstSearch(srcData, dstData);
    }

    /**
     * Finds the shortest path between two vertices in a graph.
     * @param sources list of sources
     * @param destinations list of destinations
     * @param srcData the data in the source (start) vertex
     * @param dstData the data in the destination vertex
     * @return a list of the data in each vertex in the shortest path
     * @param <T> the type of the values inside vertexes
     * @throws IllegalArgumentException if the sources and destination lists are different lengths, if the graph doesn't
     * contain the source or destination, or if there is no path between the source and destination.
     */
    public static <T> List<T> shortestPath(List<T> sources, List<T> destinations, T srcData, T dstData) {
        if (sources.size() != destinations.size()) throw new IllegalArgumentException();

        if (!sources.contains(srcData) && !destinations.contains(srcData)) throw new IllegalArgumentException();
        if (!sources.contains(dstData) && !destinations.contains(dstData)) throw new IllegalArgumentException();

        Graph<T> g = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            g.addVertex(sources.get(i));
            g.addVertex(destinations.get(i));
            g.addEdge(sources.get(i), destinations.get(i));
        }

        return g.breadthFirstSearch(srcData, dstData);
    }

    /**
     * Sorts a graph using topological sort.
     * @param sources list of sources
     * @param destinations list of destinations
     * @return list of vertexes in sorted order
     * @param <T> the type of the values inside vertexes
     * @throws IllegalArgumentException if the graph contains a cycle, or if sources & destinations lists are different lengths
     */
    public static <T> List<T> sort(List<T> sources, List<T> destinations) {
        if (sources.size() != destinations.size()) throw new IllegalArgumentException();

        Graph<T> g = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            g.addVertex(sources.get(i));
            g.addVertex(destinations.get(i));
            g.addEdge(sources.get(i), destinations.get(i));
        }

        List<T> sorted = g.topoSort();
        if (sorted.size() != g.size()) throw new IllegalArgumentException();
        return sorted;
    }
}
