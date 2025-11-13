/**
 *
 * @author reem
 */
import java.io.File;
import java.util.*;
public class Graph {
    List<Vertex> vertices; // List of all vertices
    List<Edge> edges;      // List of all edges
    Map<Vertex, List<Edge>> adjList;       // Adjacency list for each vertex

    
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        adjList = new HashMap<>();         // Initialize adjacency list

    }
        // Getter for adjacency list
    public Map<Vertex, List<Edge>> getAdjList() {
        return adjList;
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
        // Update adjacency list
        adjList.get(v1).add(edge);
        adjList.get(v2).add(edge);
    }
    // Add edge 
    public void addEdge(Edge edge) {
        if(!edges.contains(edge)){
            edges.add(edge);
            // Ensure vertices exist in adjacency list
            adjList.putIfAbsent(edge.source, new ArrayList<>());
            adjList.putIfAbsent(edge.destination, new ArrayList<>());

            // Add edge to adjacency list of each vertex
            adjList.get(edge.source).add(edge);
            adjList.get(edge.destination).add(edge);
            
        }
    }
     // Add Vertex 
    public void addVertex(Vertex v) {
        if(!vertices.contains(v)){
            vertices.add(v);
            adjList.put(v, new ArrayList<>()); // Initialize adjacency list for the vertex

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
        adjList.put(newV, new ArrayList<>()); // Initialize adjacency list

        return newV;
    }
    // Print all edges
    public void printGraph() {
        for (Edge e : edges) {
            System.out.println(e.source.label + " -- " + e.destination.label + " : " + e.weight);
        }
    }
}
