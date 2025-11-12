
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
        Collections.shuffle(nodes, rand);

        for (int i = 1; i < nodes.size(); i++) {
            Vertex u = nodes.get(i);
            Vertex v = nodes.get(rand.nextInt(i)); // connect to one of previous nodes => tree
            int w = rand.nextInt(100) + 1; // weight 1..100

            
            seen.add(u + "#" + v);
            edges.add(new Edge(u, v, w));
        }

        // 2) Add random edges until we have m edges
        while (edges.size() < m) {
            Vertex  u = nodes.get(rand.nextInt(n-1) + 1);
            Vertex  v = nodes.get(rand.nextInt(n-1) + 1);
            if (u == v) continue;

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

}
