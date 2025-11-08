import java.util.List;

public abstract class MSTAlgorithm {

    protected List<Edge> result; // MST edges result

    
    public abstract List<Edge> findMST(Graph g);

   
    public int totalWeight() {
        int sum = 0;
        for (Edge e : result) {
            sum += e.weight;
        }
        return sum;
    }

    
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
