package eu.opengovintelligence.admin.opengovintelligence;

/**
 * Created by Admin on 29/6/2017.
 */

public class BasicVariables {
    String id;
    String label;

    public BasicVariables(String id, String label) {
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
