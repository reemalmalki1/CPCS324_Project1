
import java.util.Scanner;

//CPCS324 Project - Part 1
//This single class version contains everything in one file for simplicity
public class Main {

    public static void main(String[] args) {
        int userOption;
        int JCount; // vertex count
        int PCount; // edge count
        Graph g = new Graph(); // Create new graph
        // Create Prim algorithm object
        PrimAlg prim = new PrimAlg();
        // Create kruskal algorithm object
        MSTAlgorithm kruskal = new KruskalAlg();
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("\nSelect one option:\n"
                    + "1. Load  a Graph from an input file \n"
                    + "2. Generate a Random Graph \n"
                    + "3. Enter junctions and pipelines (manually) \n"
                    + "4. Exit \n your option:");
            userOption = input.nextInt();
            switch (userOption) {
                case 1: {
                    g.readGraphFromFile("graph.txt");          // Read from file
                    System.out.println("✅ Graph edges loaded successfully:\n");
                    g.printGraph(); // Print to check

                    // Compute MST
                    prim.findMST(g);
                    // Print the result using the abstract class print method
                    System.out.println("MST (Prim):");
                    prim.printResult();

                    // Compute MST using kruskal algorithm
                    // with Disjoint Subsets and Union-Find Algorithms
                    kruskal.findMST(g);

                    // Print the result using the abstract class print method
                    System.out.println("MST (Kruskal):");
                    kruskal.printResult();
                    break;
                }
                case 2: {
                    do {
                        System.out.print("\nEnter Junction(Vertexs) Count:");
                        JCount = input.nextInt();
                        System.out.print("Enter pipe(edges) Count:");
                        PCount = input.nextInt();
                    } while (JCount < 2 || PCount < 1);
                    RandomGraph RandomGraphObj = new RandomGraph();
                    g = RandomGraphObj.make_graph(JCount, PCount);
                    g.printGraph();

                    // Compute MST
                    prim.findMST(g);
                    // Print the result using the abstract class print method
                    System.out.println("MST (Prim):");
                    prim.printResult();

                    kruskal.findMST(g);
                    // Print the result using the abstract class print method
                    System.out.println("MST (Kruskal) network:");
                    kruskal.printResult();

                    break;
                }
                case 3: {
                    Graph network = new Graph();
                    do {
                        System.out.print("\nEnter Junction(Vertexs) Count:");
                        JCount = input.nextInt();
                        System.out.print("Enter pipe(edges) Count:");
                        PCount = input.nextInt();
                    } while (JCount < 2 || PCount < 1);
                    Junction j1 = new Junction("J1");
                    Junction j2 = new Junction("J2");
                    Junction j3 = new Junction("J3");
                    Junction j4 = new Junction("J4");
                    Junction j5 = new Junction("J5");
                    Pipe p1 = new Pipe(j1, j3, 5);
                    Pipe p2 = new Pipe(j1, j4, 13);
                    Pipe p3 = new Pipe(j1, j5, 8);
                    Pipe p4 = new Pipe(j2, j4, 4);
                    Pipe p5 = new Pipe(j2, j5, 15);
                    Pipe p6 = new Pipe(j3, j4, 7);
                    Pipe p7 = new Pipe(j4, j5, 9);
                    Pipe p8 = new Pipe(j1, j2, 2);
                    network.addVertex(j1);
                    network.addVertex(j2);
                    network.addVertex(j3);
                    network.addVertex(j4);
                    network.addVertex(j5);
                    network.addEdge(p1);
                    network.addEdge(p2);
                    network.addEdge(p3);
                    network.addEdge(p4);
                    network.addEdge(p5);
                    network.addEdge(p6);
                    network.addEdge(p7);
                    network.addEdge(p8);

                    // Compute MST
                    prim.findMST(network);
                    // Print the result using the abstract class print method
                    System.out.println("MST (Prim):");
                    prim.printResult();

                    kruskal.findMST(network);
                    // Print the result using the abstract class print method
                    System.out.println("MST (Kruskal) network:");
                    kruskal.printResult();
                }
                case 4: {
                    System.exit(0);
                }
            }
        } while (true);
    }
}
