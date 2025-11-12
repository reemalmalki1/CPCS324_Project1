/**
 * Represents a water junction (or node) in the distribution network.
 * This class extends the generic Vertex class to include additional
 * information specific to a water system such as the zone or region
 * where the junction is located.
 */
public class Junction extends Vertex{
    
    // The zone or area that this junction serves
    private String zone ;
    
    
    // Constructor that creates a junction with a given label (ID).
    public Junction(String label) {
        super(label);
    }

    // Constructor that creates a junction with both a label and a zone name.
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

