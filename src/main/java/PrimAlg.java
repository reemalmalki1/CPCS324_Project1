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
        

        // If the graph has no vertices, return an empty result
        if (g.vertices.isEmpty())
            return result;

        // Set to keep track of visited vertices
        Set<Vertex> visited = new HashSet<>();
        // Priority queue to always pick the smallest weighted edge
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(); 

        // Start with the first vertex in the graph
        Vertex start = g.vertices.get(0);
        visited.add(start);


        // Add all edges connected to the starting vertex
        for (Edge e : g.edges) {
            if (e.source == start || e.destination == start) {
                minHeap.add(e);
            }
        }


       // Continue until all vertices are visited
        while (!minHeap.isEmpty() && visited.size() < g.vertices.size()) {

            // Extract the smallest edge available
            Edge edge = minHeap.poll();

            Vertex next = null;

            // Determine which vertex is the new one
            if (visited.contains(edge.source) && !visited.contains(edge.destination)) {
                next = edge.destination;
            } else if (visited.contains(edge.destination) && !visited.contains(edge.source)) {
                next = edge.source;
            } else {
                continue; // Ignore edges that do not lead to a new vertex 
            }

            // Add edge to MST result
            result.add(edge);
            visited.add(next);

            // Add new edges that connect from the newly added vertex
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

