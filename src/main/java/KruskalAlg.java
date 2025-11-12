
import java.util.*;

/**
 *
 * @author Rawan Munshi
 */

class Node {
    String data;
    Node next;
    SetNode set; // the sebset which the node belongs

    Node(String data) {
        this.data = data;
    }
}

// subset structure
class SetNode {
    Node head;
    Node tail;
}

// Linked List used to implemente each subset, where:
// header contains the pointers to the first and last elements 
// of the list along with the number of elements in the list.

class LinkedListSet {
    
    // Create a subset contain one vertex
    public static SetNode makeSet(String data) {
        Node node = new Node(data);
        SetNode set = new SetNode();
        set.head = node;
        set.tail = node;
        node.set = set;
        return set;
    }

    // Find the set (list header)
    public static Node find(Node node) {
        return node.set.head;
    }

    // union set A and set B
    public static void union(Node a, Node b) {
        SetNode setA = a.set;
        SetNode setB = b.set;

        // if they are in the same set , exit
        if (setA == setB) return; 

        //  Link the tail of set A to the head of set B.
        setA.tail.next = setB.head;
        setA.tail = setB.tail;

        // update the set for each node in set B.
        Node temp = setB.head;
        while (temp != null) {
            temp.set = setA;
            temp = temp.next;
        }
    }
}

public class KruskalAlg extends MSTAlgorithm {

    @Override
    public List<Edge> findMST (Graph g){
        
        // initialize result list 
        result =  new ArrayList<>();

        int vertexCount = g.vertices.size();
        
        // Sort the edge weights (smallest to largest) 
        Collections.sort(g.edges);

        // Use a HashMap to store the subset for each vertex (keyed by its String label)
        Map<String, Node> vertexMap = new HashMap<>();

        // create a subset for each vertex and add it to HashMap
        for (Vertex v : g.vertices) {
            vertexMap.putIfAbsent(v.label, LinkedListSet.makeSet(v.label).head);
        }
        
        int ecounter = 0 ;
        int k = 0 ;
        
        // The loop continues until ecounter < vertexCount−1
        // since a MST for V vertices contains ∣V∣−1 edges
        while (ecounter < vertexCount-1 ) {             
            Edge e = g.edges.get(k);
            
            Node u = vertexMap.get(e.source.label);
            Node v = vertexMap.get(e.destination.label);
            
            // Check if the vertices are in different subsets (no cylce) 
            // If no cycle : add the edge; else skip it
            if (LinkedListSet.find(u) != LinkedListSet.find(v)) {
                result.add(e);
                ecounter++;
                // merge the two subsets into a single subset
                LinkedListSet.union(u, v);
            }
            
            // move to the next edge 
            k++;
        }
        
        return result;
    }    
}
