
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RandomGraph {

    /**
     * Returns a connected, undirected random weighted graph as a list of edges.
     * Nodes are numbered from 1..n.
     * Ensures no self-loops and no duplicate edges (undirected).
     */
    public Graph make_graph(int n, int m) {
        
        Graph randomGraph = new Graph ();
        
        if (m < n - 1) {
            throw new IllegalArgumentException("m must be at least n-1 to be connected");
        }
        if (m > (n * (n - 1)) / 2) {
            throw new IllegalArgumentException("m is too large for a simple undirected graph");
        }

        Random rand = new Random();
        List<Edge> edges = new ArrayList<>(m);
        // Use a set to avoid duplicates: store as "min#max"
        HashSet<String> seen = new HashSet<>();

        // 1) Build a random spanning tree first to ensure connectivity
        List<Vertex> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
        nodes.add(new Vertex("J"+i));
        }
        
        //for (int i = 1; i <= n; i++) nodes.add(i);
        Collections.shuffle(nodes, rand);

        for (int i = 1; i < nodes.size(); i++) {
            Vertex u = nodes.get(i);
            Vertex v = nodes.get(rand.nextInt(i)); // connect to one of previous nodes => tree
            int w = rand.nextInt(100) + 1; // weight 1..100
            // int a = Math.min(u, v), b = Math.max(u, v);

            seen.add(u + "#" + v);
            edges.add(new Edge(u, v, w));
        }

        // 2) Add random edges until we have m edges
        while (edges.size() < m) {
            Vertex  u = nodes.get(rand.nextInt(n-1) + 1);
            Vertex  v = nodes.get(rand.nextInt(n-1) + 1);
            if (u == v) continue;
            //int a = Math.min(u, v), b = Math.max(u, v);
            String key1 = u + "#" + v;
            String key2 = v + "#" + u;
            if (seen.contains(key1) || seen.contains(key2)) continue; // avoid duplicate
            int w = rand.nextInt(100) + 1;
            seen.add(key1);
            edges.add(new Edge(u, v, w));
        }

        randomGraph.vertices = nodes;
        randomGraph.edges = edges ;
        return randomGraph;
    }

    
    /**
     * Save the graph to a text file in the project's required format:
     * First line: n m
     * Following m lines: u v weight
     */
//    public static void saveToFile(List<Edge> edges, int n, String filename) throws IOException {
//        int m = edges.size();
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//            writer.write(n + " " + m);
//            writer.newLine();
//            for (Edge e : edges) {
//                writer.write(e.toString());
//                writer.newLine();
//            }
//        }
//    }
//
//    // Simple test main to generate and save a graph
//    public void main(String[] args) {
//        int n = 10;
//        int m = 20;
//        Graph g = make_graph(n, m);
//        g.printGraph();
//        String filename = "random_graph_1000_10000.txt";
//        try {
//            saveToFile(g, n, filename);
//            System.out.println("Saved graph to " + filename + " (n=" + n + ", m=" + m + ")");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}