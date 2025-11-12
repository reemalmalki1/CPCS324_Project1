

public class Pipe extends Edge {
    private String material;
    private double maxPressure;
    private boolean isLeaking;
    private String installationDate;
    private boolean isActive;
    
    public Pipe(Junction source, Junction destination, int weight) {
        super(source, destination, weight);
    }

    public Pipe(String material, double maxPressure, boolean isLeaking, boolean isActive, Junction source, Junction destination, int weight) {
        super(source, destination, weight);
        this.material = material;
        this.maxPressure = maxPressure;
        this.isLeaking = isLeaking;
        this.isActive = isActive;
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

    public boolean isIsLeaking() {
        return isLeaking;
    }

    public void setIsLeaking(boolean isLeaking) {
        this.isLeaking = isLeaking;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Pipe{" + "source=" + source + ", destination=" + destination 
                +", weight=" + weight + "material=" + material + ", maxPressure="
                + maxPressure + ", isLeaking=" + isLeaking + ", installationDate=" 
                + installationDate + ", isActive=" + isActive + '}';
    }
    
}
