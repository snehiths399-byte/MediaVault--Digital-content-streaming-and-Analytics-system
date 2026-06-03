import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class PowerGridMST {

    static int[] parent;

    static int find(int vertex) {
        if (parent[vertex] == vertex)
            return vertex;
        return parent[vertex] = find(parent[vertex]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }

    public static void main(String[] args) {

        int vertices = 4;

        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 4));

        Collections.sort(edges);

        parent = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        System.out.println("Minimum Spanning Tree Edges:");

        for (Edge edge : edges) {

            int rootSource = find(edge.source);
            int rootDestination = find(edge.destination);

            if (rootSource != rootDestination) {

                union(rootSource, rootDestination);

                System.out.println(
                        "Station " + edge.source +
                        " -> Station " + edge.destination +
                        " Cost = " + edge.weight);

                totalCost += edge.weight;
            }
        }

        System.out.println("\nTotal Optimized Network Cost = " + totalCost);
    }
}