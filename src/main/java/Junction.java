
public class Junction extends Vertex{
    
    private String zone ;
    
    public Junction(String label) {
        super(label);
    }

    public Junction(String label, String zone) {
        super(label);
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Junction{" + "label= " + label + "zone= " + zone + " }";
    }

} 
