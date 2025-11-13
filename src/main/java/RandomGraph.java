import java.util.*;

public class RandomGraph {

    /**
     * Returns a connected, undirected random weighted graph as a list of edges.
     * Nodes are numbered from 1..n.
     * Ensures no self-loops and no duplicate edges (undirected).
     */
    public Graph make_graph(int n, int m) {
        
        Graph randomGraph = new Graph(); // Graph object with vertices, edges, and adjList initialized
        
        if (m < n - 1) {
            throw new IllegalArgumentException("m must be at least n-1 to be connected");
        }
        if (m > (n * (n - 1)) / 2) {
            throw new IllegalArgumentException("m is too large for a simple undirected graph");
        }

        Random rand = new Random();
        HashSet<String> seen = new HashSet<>(); // To avoid duplicate edges (undirected)

        // 1) Create vertices
        List<Vertex> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Vertex v = new Vertex("J" + i);
            nodes.add(v);
            randomGraph.addVertex(v); 
            // addVertex also initializes adjacency list for each vertex
        }

        // 2) Build a random spanning tree first to ensure connectivity
        Collections.shuffle(nodes, rand);
        for (int i = 1; i < nodes.size(); i++) {
            Vertex u = nodes.get(i);
            Vertex v = nodes.get(rand.nextInt(i)); // connect to one of previous nodes
            int w = rand.nextInt(100) + 1;        // random weight 1..100

            Edge e = new Edge(u, v, w);
            randomGraph.addEdge(e); 
            // addEdge automatically updates adjList for both u and v
            seen.add(u.label + "#" + v.label); 
        }

        // 3) Add random edges until we reach m edges
        while (randomGraph.edges.size() < m) {
            Vertex u = nodes.get(rand.nextInt(n));
            Vertex v = nodes.get(rand.nextInt(n));
            if (u.equals(v)) continue;  // avoid self-loop

            String key1 = u.label + "#" + v.label;
            String key2 = v.label + "#" + u.label;
            if (seen.contains(key1) || seen.contains(key2)) continue; // avoid duplicates

            int w = rand.nextInt(100) + 1;
            Edge e = new Edge(u, v, w);
            randomGraph.addEdge(e); 
            // addEdge updates adjacency list automatically
            seen.add(key1);
        }

        return randomGraph;
    }
}
