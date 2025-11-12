/**
 *
 * @author reem
 */
    public class Edge implements Comparable<Edge> {
    Vertex source;
    Vertex destination;
    int weight;
    // Constructor to initialize edge information
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    // Compare edges by weight (used in algorithms like Kruskal's)
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight; // Sort edges by weight
    }
}


