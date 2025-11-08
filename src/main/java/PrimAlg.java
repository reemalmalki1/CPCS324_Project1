/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amjadalghamdy
 */
import java.util.*;

public class PrimAlg extends MSTAlgorithm {

    @Override
    public List<Edge> findMST(Graph g) {

        result = new ArrayList<>();

        if (g.vertices.isEmpty())
            return result;

        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(); // لأن Edge implements Comparable

        
        Vertex start = g.vertices.get(0);
        visited.add(start);


        for (Edge e : g.edges) {
            if (e.source == start || e.destination == start) {
                minHeap.add(e);
            }
        }


        while (!minHeap.isEmpty() && visited.size() < g.vertices.size()) {

            Edge edge = minHeap.poll();

            Vertex next = null;

            if (visited.contains(edge.source) && !visited.contains(edge.destination)) {
                next = edge.destination;
            } else if (visited.contains(edge.destination) && !visited.contains(edge.source)) {
                next = edge.source;
            } else {
                continue; 
            }

            result.add(edge);
            visited.add(next);

            for (Edge e : g.edges) {
                if ((e.source == next && !visited.contains(e.destination)) ||
                    (e.destination == next && !visited.contains(e.source))) {
                    minHeap.add(e);
                }
            }
        }

        return result;
    }
}

