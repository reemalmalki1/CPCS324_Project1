//CPCS324 Project - Part 1
//This single class version contains everything in one file for simplicity
public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();                     // Create new graph
        g.readGraphFromFile("graph.txt");          // Read from file
        System.out.println("✅ Graph edges loaded successfully:\n");
        g.printGraph();
        // Print to check
        // Create Prim algorithm object
        PrimAlg prim = new PrimAlg(); 
        // Compute MST
        prim.findMST(g);
        // Print the result using the abstract class print method
        System.out.println("MST (Prim):");
        prim.printResult();
        
        // Create kruskal algorithm object
        MSTAlgorithm kruskal = new KruskalAlg();
        
        // Compute MST using kruskal algorithm
        // with Disjoint Subsets and Union-Find Algorithms
        kruskal.findMST(g);
        
        // Print the result using the abstract class print method
        System.out.println("MST (Kruskal):");
        kruskal.printResult();

    }
}
