import java.io.File;
import java.util.*;

//CPCS324 Project - Part 1
//This single class version contains everything in one file for simplicity
public class Main {

    // ===== Vertex class =====
    static class Vertex {
        String label; // Vertex name like J1, J2

        public Vertex(String label) {
            this.label = label;
        }
    }

    // ===== Edge class =====
    static class Edge implements Comparable<Edge> {
        Vertex source;
        Vertex destination;
        int weight;

        public Edge(Vertex source, Vertex destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight; // Sort edges by weight
        }
    }

    // ===== Graph class =====
    static class Graph {
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

    // ===== Main method =====
    public static void main(String[] args) {
        Graph g = new Graph();                     // Create new graph
        g.readGraphFromFile("graph.txt");          // Read from file
        System.out.println("✅ Graph edges loaded successfully:\n");
        g.printGraph();                            // Print to check
    }
}

