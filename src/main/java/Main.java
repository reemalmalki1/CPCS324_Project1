
import java.util.Scanner;

//CPCS324 Project - Part 1
//This single class version contains everything in one file for simplicity
public class Main {

    public static void main(String[] args) {
        int userOption;
        int JCount; // vertex count
        int PCount; // edge count
        Graph g = new Graph(); // Create new graph

        Scanner input = new Scanner(System.in);
        System.out.println("*************************************");
        System.out.println(" Algorithms and Data Structures (II)");
        System.out.println("       Group Project - Part I");
        do {
            System.out.println("*************************************");

            System.out.print("1. Load a Graph from an input file and find MST \n"
                    + "2. Comparison of Kruskal's and Min-Heap Prim's Algorithms\n "
                    + "   Using a Random Graph \n"
                    + "3. Find minimum construction cost for Water Distribution Network\n"
                    + "4. Exit \n Select one option: ");
            userOption = input.nextInt();
            System.out.println("--------------------------------------");
            switch (userOption) {
                case 1: {
                    g.readGraphFromFile("graph.txt");          // Read from file
                    System.out.println("✅ Graph edges loaded successfully:");
                    g.printGraph(); // Print to check
                    MST_PrimAlgorithm(g, true);
                    MST_kruskalAlgorithm(g, true);
                    break;
                }
                case 2: {
                    do {
                        System.out.print("Enter Junction(Vertexs) Count: ");
                        JCount = input.nextInt();
                        System.out.print("Enter pipe(edges) Count: ");
                        PCount = input.nextInt();
                    } while (JCount < 2 || PCount < 1);
                    
                    RandomGraph RandomGraphObj = new RandomGraph();
                    g = RandomGraphObj.make_graph(JCount, PCount);
                    //g.printGraph();

                    long start, end, primAlgorithmDuration, kruskalAlgorithmDuration;

                    start = System.nanoTime();  // get start time
                    MST_PrimAlgorithm(g, false);
                    end = System.nanoTime(); // get end time

                    // calculating execution time in ms.
                    primAlgorithmDuration = (end - start) / 1000000;
                    System.out.println("Execution time for Prim Algorithm : " + primAlgorithmDuration + " ms");

                    start = System.nanoTime();  // get start time
                    MST_kruskalAlgorithm(g, false);
                    end = System.nanoTime(); // get end time

                    // calculating execution time in ms.
                    kruskalAlgorithmDuration = (end - start) / 1000000;
                    System.out.println("Execution time for Kruskal Algorithm : " + kruskalAlgorithmDuration + " ms");

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

                    MST_PrimAlgorithm(network, true);
                    MST_kruskalAlgorithm(network, true);
                }
                case 4: {
                    System.exit(0);
                }
            }
        } while (true);
    }

    public static void MST_PrimAlgorithm(Graph graph, boolean printMST) {
        // Create Prim algorithm object
        PrimAlg prim = new PrimAlg();

        // Compute MST
        prim.findMST(graph);

        if (printMST) {
            // Print the result using the abstract class print method
            System.out.println("MST (Prim):");
            prim.printResult();
        }
    }

    public static void MST_kruskalAlgorithm(Graph graph, boolean printMST) {

        // Create kruskal algorithm object
        MSTAlgorithm kruskal = new KruskalAlg();

        // Compute MST using kruskal algorithm
        // with Disjoint Subsets and Union-Find Algorithms
        kruskal.findMST(graph);
        if (printMST) {
            // Print the result using the abstract class print method
            System.out.println("MST (Kruskal):");
            kruskal.printResult();
        }
    }

}
