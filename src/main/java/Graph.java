/**
 *
 * @author reem
 */
import java.io.File;
import java.util.*;
public class Graph {
    List<Vertex> vertices; // List of all vertices
    List<Edge> edges;      // List of all edges
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }
    // Read graph data from external file
    public void readGraphFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String v1Label = sc.next();
                String v2Label = sc.next();
                int weight = sc.nextInt();
                addEdge(v1Label, v2Label, weight);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    // Add edge between two vertex labels
    private void addEdge(String label1, String label2, int weight) {
        Vertex v1 = getOrCreateVertex(label1);
        Vertex v2 = getOrCreateVertex(label2);
        Edge edge = new Edge(v1, v2, weight);
        edges.add(edge);
    }
    // Add edge 
    public void addEdge(Edge edge) {
        if(!edges.contains(edge)){
            edges.add(edge);
        }
    }
     // Add Vertex 
    public void addVertex(Vertex v) {
        if(!vertices.contains(v)){
            vertices.add(v);
        }
    }
    // Find or create a vertex
    private Vertex getOrCreateVertex(String label) {
        for (Vertex v : vertices) {
            if (v.label.equals(label))
                return v;
        }
        Vertex newV = new Vertex(label);
        vertices.add(newV);
        return newV;
    }
    // Print all edges
    public void printGraph() {
        for (Edge e : edges) {
            System.out.println(e.source.label + " -- " + e.destination.label + " : " + e.weight);
        }
    }
}
