package graph;

import java.util.*;

/*
    code copied from 'www.geeksforgeeks.org/implementing-generic-graph-in-java/'

    Graph class is implemented using HashMap. Graph Nodes are equivalent to nodes
    and arcs between nodes as edges

    There are generally two ways to represent a graph data structure
    a) Adjacency matrix     -> two dimensial matrix i.e. VxV where Aij = 1
    b) Adjacency List       -> uses an array of linked list. Size of array
                                equivalent to vertices.
 */
public class Graph<T> {
    // use hashmap to store the edges in the graph
    private Map<T, List<T>> map = new HashMap<>();

    // adds a new vertex to the graph
    public void addVertex(T s) {
        map.put(s, new LinkedList<T>());
    }

    // adds the edge between source and the destination
    public void addEdge(T source, T destination, boolean bidirectional) {
        if (!map.containsKey(source))
            addVertex(source);
        if (!map.containsKey(destination))
            addVertex(source);

        map.get(source).add(destination);

        if (bidirectional)
            map.get(destination).add(source);
    }

    // return the count of vertices
    public int getVertexCount() {
        return map.keySet().size();
    }

    // return the count of edges
    public int getEdgesCount(boolean bidirection) {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
        return count;
    }

    // whether vertex is present ort not in the graph
    public boolean hasVertx(T s) {
        return map.containsKey(s);
    }

    public boolean hasEdge(T s, T d) {
        return map.get(s).contains(d);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ":");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
