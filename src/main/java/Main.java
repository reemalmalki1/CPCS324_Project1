
import java.util.Scanner;

//CPCS324 Project - Part 1
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
                    + "2. Comparison of Kruskal's and Min-Heap Prim's Algorithms "
                    + "\n   Using a Random Graph \n"
                    + "3. Find minimum construction cost for Water Distribution Network"
                    + "\n4. Exit \n Select one option: ");
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
                        System.out.print("Enter Junction(Vertexs) Count: ");
                        JCount = input.nextInt();
                        System.out.print("Enter pipe(edges) Count: ");
                        PCount = input.nextInt();
                    } while (JCount < 2 || PCount < 1);
                    for (int i = 1; i <= JCount; i++) {
                        Junction node = new Junction("J" + i);
                        System.out.print("Enter Junction " + i + " zone: ");
                        node.setZone(input.next());
                        network.addVertex(node);
                    }
                    System.out.print("\nJunction: ");
                    for (int i = 0; i < JCount; i++) {
                        System.out.print(network.vertices.get(i).label + " ");
                    }
                    for (int i = 1; i <= PCount; i++) {
                        System.out.println("\nEnter pipe " + i + " information:");
                        System.out.print("source (number): ");
                        int source = input.nextInt();
                        System.out.print("destination (number): ");
                        int destination = input.nextInt();
                        System.out.print("cost: ");
                        int cost = input.nextInt();
                        System.out.print("material: ");
                        String material = input.next();
                        System.out.print("maxPressure: ");
                        double maxPressure = input.nextInt();
                        Pipe p1 = new Pipe((Junction) network.vertices.get(source - 1),
                                (Junction) network.vertices.get(destination - 1), cost,
                                material, maxPressure);
                        network.addEdge(p1);
                    }
                    System.out.println("=======================================");
                    System.out.println("   Water Distribution Network (Graph)   ");
                    System.out.println("=======================================");
                    network.printGraph();
                    int choice  ;
                    do {
                        System.out.println("Choose an algorithm to find MST:");
                        System.out.println("1. Prim's Algorithm");
                        System.out.println("2. Kruskal's Algorithm");
                        System.out.print("Enter your choice (1 or 2): ");
                        choice  = input.nextInt();
                    } while (choice != 1 && choice != 2);
                    
                    if(choice == 1){
                    MST_PrimAlgorithm(network, true);
                    } else {
                    MST_kruskalAlgorithm(network, true);
                    }
                    break;
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
