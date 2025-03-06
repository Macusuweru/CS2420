package assign07;

import java.util.*;

/**
 * GraphUtility is a static class which contains several tools to interface with graphs, which are input as generic lists of sources and destinations.
 * @author Maxwell, Ashley Pederson
 * @version March 4, 2025
 */
public class GraphUtility {
    /**
     * Determines if there is a connection between two vertices in the directed graph described by sources and destinations.
     * Uses depth-first search to find a path between the source and destination vertices.
     * 
     * @param <T> the type of data stored in the graph vertices
     * @param sources a list of source vertices for the graph edges
     * @param destinations a list of destination vertices for the graph edges
     * @param srcData the source vertex to start the search from
     * @param dstData the destination vertex to find
     * @return true if there is a path from srcData to dstData, false otherwise
     * @throws IllegalArgumentException if sources and destinations are not the same size, or if srcData or dstData are not in the graph
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
     * Finds the shortest path between two vertices in the directed graph described by sources and destinations.
     * Uses breadth-first search to find the shortest path between the source and destination vertices.
     *
     * @param <T> the type of data stored in the graph vertices
     * @param sources a list of source vertices for the graph edges
     * @param destinations a list of destination vertices for the graph edges
     * @param srcData the source vertex to start the path from
     * @param dstData the destination vertex to reach
     * @return a list containing the vertices in the shortest path from srcData to dstData
     * @throws IllegalArgumentException if sources and destinations are not the same size, if srcData or dstData are not in the graph,
     *                                  or if there is no path from srcData to dstData
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
     * Performs a topological sort on the directed graph described by sources and destinations.
     * A topological sort is a linear ordering of vertices such that for every directed edge (u,v),
     * vertex u comes before vertex v in the ordering.
     *
     * @param <T> the type of data stored in the graph vertices
     * @param sources a list of source vertices for the graph edges
     * @param destinations a list of destination vertices for the graph edges
     * @return a list containing the vertices in topological order
     * @throws IllegalArgumentException if the graph contains a cycle (topological sort is only possible for acyclic graphs)
     */
    public static <T> List<T> sort(List<T> sources, List<T> destinations) {
        if (sources.size() != destinations.size()) throw new IllegalArgumentException();

        Graph<T> g = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            g.addVertex(sources.get(i));
            g.addVertex(destinations.get(i));
            g.addEdge(sources.get(i), destinations.get(i));
        }

        List<T> topo = g.topoSort();
        if (topo.size() != g.size()) throw new IllegalArgumentException("Cyclic graph passed to sort");
        return topo;
    }
}
