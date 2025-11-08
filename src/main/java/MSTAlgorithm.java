import java.util.List;

public abstract class MSTAlgorithm {

    protected List<Edge> result; // This list will store the MST edges

    // Each MST algorithm (Prim / Kruskal) must implement this method
    public abstract List<Edge> findMST(Graph g);

   // Calculate the total weight of the MST
    public int totalWeight() {
        int sum = 0;
        for (Edge e : result) {
            sum += e.weight;
        }
        return sum;
    }

    // Print the MST result edges and total weight
    public void printResult() {
        if (result == null || result.isEmpty()) {
            System.out.println("No MST has been computed yet.");
            return;
        }

        System.out.println("MST Edges:");
        for (Edge e : result) {
            System.out.println(e.source.label + " -- " + e.destination.label + " : " + e.weight);
        }

        System.out.println("Total Weight = " + totalWeight());
    }
}
