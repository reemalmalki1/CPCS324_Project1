

public class Pipe extends Edge {
    private String material;
    private double maxPressure;
    
    public Pipe(Junction source, Junction destination, int weight) {
        super(source, destination, weight);
    }

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
