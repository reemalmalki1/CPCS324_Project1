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
        prim.printResult();

    }
}
