/**
 * Represents a water pipe (or edge) connecting two junctions in the network.
 * This class extends the generic Edge class and includes additional
 * physical properties relevant to water distribution, such as pipe material
 * and maximum pressure capacity.
 *
 * Each Pipe object defines a connection between two Junction objects
 * with an associated cost (weight), material type, and pressure limit.
 */
public class Pipe extends Edge {
    
    // The material of the pipe (e.g., PVC, Steel)
    private String material;
    
    // The maximum pressure the pipe can withstand (in bar)
    private double maxPressure;
    
    // Constructor to create a pipe between two junctions with a specified cost.
    public Pipe(Junction source, Junction destination, int weight) {
        super(source, destination, weight);
    }

    // Constructor to create a pipe with full properties.
    public Pipe(Junction source, Junction destination, int weight , String material, double maxPressure) {
        super(source, destination, weight);
        this.material = material;
        this.maxPressure = maxPressure;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(double maxPressure) {
        this.maxPressure = maxPressure;
    }

    @Override
    public String toString() {
        return "Pipe{" + "source=" + source + ", destination=" + destination 
                +", weight=" + weight + "material=" + material + ", maxPressure="
                + maxPressure + '}';
    }
    
}

