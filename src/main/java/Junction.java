
public class Junction extends Vertex{
    
    private String zone ;
    private boolean active ;
    
    public Junction(String label) {
        super(label);
    }

    public Junction(String label, String zone, boolean active) {
        super(label);
        this.zone = zone;
        this.active = active;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Junction{" + "label=" + label + "zone=" + zone + ", active=" + active + '}';
    }

} 
