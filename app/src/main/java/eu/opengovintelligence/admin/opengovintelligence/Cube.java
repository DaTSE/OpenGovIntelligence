package eu.opengovintelligence.admin.opengovintelligence;

/**
 * Created by Admin on 28/6/2017.
 */

public class Cube {
    String id;
    String label;

    public Cube(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}