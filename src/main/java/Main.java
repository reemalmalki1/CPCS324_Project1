
import java.util.Scanner;

//CPCS324 Project - Part 1
//This single class version contains everything in one file for simplicity
public class Main {

    public static void main(String[] args) {
        int userOption; // Variable to store user's menu choice
        int JCount;     // vertex count
        int PCount;     // edge count
        Graph g = new Graph(); // Create new graph

        Scanner input = new Scanner(System.in);
        
        // Project Title Display
        System.out.println("*************************************");
        System.out.println(" Algorithms and Data Structures (II)");
        System.out.println("       Group Project - Part I");
        
        // Main program loop (keeps running until user exits)
        do {
            System.out.println("*************************************");
            // Display the main menu options to the user
            System.out.print("1. Load a Graph from an input file and find MST \n"
                    + "2. Comparison of Kruskal's and Min-Heap Prim's Algorithms "
                    + "\n   Using a Random Graph \n"
                    + "3. Find minimum construction cost for Water Distribution Network"
                    + "\n4. Exit \n Select one option: ");
            userOption = input.nextInt();
            System.out.println("--------------------------------------");
            switch (userOption) {
                
                // Option 1: Load graph from file and find MST using(Prim + Kruskal)
                case 1: {                
                    g.readGraphFromFile("graph.txt"); // Read from file
                    System.out.println("✅ Graph edges loaded successfully:");
                    g.printGraph(); // Print to check
                    
                    // Apply both algorithms and display MST results
                    MST_PrimAlgorithm(g, true);
                    MST_kruskalAlgorithm(g, true);
                    break;
                }
                // Option 2: Compare Prim and Kruskal on a random graph
                case 2: {
                    // Ask user for number of vertices and edges
                    do {
                        System.out.print("Enter Junction(Vertexs) Count: ");
                        JCount = input.nextInt();
                        System.out.print("Enter pipe(edges) Count: ");
                        PCount = input.nextInt();
                    // input validation : at least 2 vertices with 1 edge
                    } while (JCount < 2 || PCount < 1); 

                    // Generate random graph
                    RandomGraph RandomGraphObj = new RandomGraph();
                    g = RandomGraphObj.make_graph(JCount, PCount);
                     
                    // Uncomment the line below to print the generated graph.
                    //g.printGraph();
                    // Note: It is disabled by default because the graph may contain a large number of vertices,
                    // which would produce a very long output.
                    
                    long start, end, primAlgorithmDuration, kruskalAlgorithmDuration;

                    // Measure execution time for Prim's Algorithm
                    start = System.nanoTime();  
                    // Use 'true' instead of 'false' to display the MST edges and total cost.
                    MST_PrimAlgorithm(g, false);
                    end = System.nanoTime(); 
                    primAlgorithmDuration = (end - start) / 1000000;
                    System.out.println("Execution time for Prim Algorithm : " + primAlgorithmDuration + " ms");

                    // Measure execution time for Kruskal's Algorithm
                    start = System.nanoTime();  
                    // Use 'true' instead of 'false' to display the MST edges and total cost.
                    MST_kruskalAlgorithm(g, false);
                    end = System.nanoTime(); 
                    kruskalAlgorithmDuration = (end - start) / 1000000;
                    System.out.println("Execution time for Kruskal Algorithm : " + kruskalAlgorithmDuration + " ms");

                    break;
                }
                 // Option 3: Manually build a Water Distribution Network
                case 3: { 
                    Graph network = new Graph();
                    
                    // Ask user for number of junctions and pipes
                    do {
                        System.out.print("Enter Junction(Vertexs) Count: ");
                        JCount = input.nextInt();
                        System.out.print("Enter pipe(edges) Count: ");
                        PCount = input.nextInt();
                    // input validation : at least 2 vertices with 1 edge
                    } while (JCount < 2 || PCount < 1);
                    
                    // Collect information about each Junction
                    for (int i = 1; i <= JCount; i++) {
                        Junction node = new Junction("J" + i);
                        System.out.print("Enter Junction " + i + " zone: ");
                        node.setZone(input.next());
                        network.addVertex(node);
                    }
                    // Display all junctions added
                    System.out.print("\nJunction: ");
                    for (int i = 0; i < JCount; i++) {
                        System.out.print(network.vertices.get(i).label + " ");
                    }
                    // Collect details about each Pipe
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
                        
                        // Create Pipe object connecting the selected junctions
                        Pipe p1 = new Pipe((Junction) network.vertices.get(source - 1),
                                (Junction) network.vertices.get(destination - 1), cost,
                                material, maxPressure);
                        network.addEdge(p1);
                    }

                    // Display the constructed graph
                    System.out.println("=======================================");
                    System.out.println("   Water Distribution Network (Graph)   ");
                    System.out.println("=======================================");
                    network.printGraph();
                    
                    // Allow user to choose which MST algorithm to apply
                    int choice  ;
                    do {
                        System.out.println("Choose an algorithm to find MST:");
                        System.out.println("1. Prim's Algorithm");
                        System.out.println("2. Kruskal's Algorithm");
                        System.out.print("Enter your choice (1 or 2): ");
                        choice  = input.nextInt();
                    // input validation : the user must choose either 1 or 2
                    } while (choice != 1 && choice != 2);
                    
                    // Execute selected algorithm
                    if(choice == 1){
                    MST_PrimAlgorithm(network, true);
                    } else {
                    MST_kruskalAlgorithm(network, true);
                    }
                }
                // Exit program
                default: {
                    System.out.println("Exiting program");
                    System.exit(0);
                }
            }
        } while (true); // Loop until user selects "Exit"
    }

    // Method to execute and print Prim's Algorithm results
    public static void MST_PrimAlgorithm(Graph graph, boolean printMST) {
        // Create Prim algorithm object
        PrimAlg prim = new PrimAlg();

        // Compute MST using Prim’s approach
        prim.findMST(graph);

        // Print result if requested
        if (printMST) {
            System.out.println("MST (Prim):");
            prim.printResult();
        }
    }

    // Method to execute and print Kruskal's Algorithm results
    public static void MST_kruskalAlgorithm(Graph graph, boolean printMST) {

        // Create kruskal algorithm object
        MSTAlgorithm kruskal = new KruskalAlg();

        // Compute MST using kruskal algorithm
        // with Disjoint Subsets and Union-Find Algorithms
        kruskal.findMST(graph);
        
        // Print result if requested
        if (printMST) {
            System.out.println("MST (Kruskal):");
            kruskal.printResult();
        }
    }

}
