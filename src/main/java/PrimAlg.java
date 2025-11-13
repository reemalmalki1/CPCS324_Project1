import java.util.*;

public class PrimAlg extends MSTAlgorithm {

    @Override
    public List<Edge> findMST(Graph g) {
        result = new ArrayList<>();

        // If the graph has no vertices, return empty result
        if (g.vertices.isEmpty())
            return result;

        int V = g.vertices.size();

        // Map each vertex to an index (not strictly needed here but useful if needed for arrays)
        Map<Vertex, Integer> vertexIndex = new HashMap<>();
        for (int i = 0; i < V; i++) {
            vertexIndex.put(g.vertices.get(i), i);
        }

        // Min-priority queue to always pick the edge with the smallest weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Set to keep track of vertices already included in the MST
        Set<Vertex> visited = new HashSet<>();
        // Start from the first vertex
        Vertex start = g.vertices.get(0);
        visited.add(start);

        // Add all edges connected to the starting vertex into the priority queue
        pq.addAll(g.getAdjList().get(start));

        // Loop until all vertices are included in the MST
        while (!pq.isEmpty() && visited.size() < V) {
            // Pick the edge with the smallest weight
            Edge edge = pq.poll();

            Vertex next = null;

            // Determine the new vertex that is not yet included in MST
            if (visited.contains(edge.source) && !visited.contains(edge.destination))
                next = edge.destination;
            else if (visited.contains(edge.destination) && !visited.contains(edge.source))
                next = edge.source;
            else
                continue; // Ignore if both vertices are already in MST

            // Add the edge to MST result
            result.add(edge);
            // Mark the new vertex as visited
            visited.add(next);

            // Add all edges from the newly added vertex to the priority queue
            for (Edge e : g.getAdjList().get(next)) {
                Vertex v = e.source.equals(next) ? e.destination : e.source;
                if (!visited.contains(v)) {
                    pq.add(e);
                }
            }
        }

        return result;
    }
}
