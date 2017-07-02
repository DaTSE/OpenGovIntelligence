package eu.opengovintelligence.admin.opengovintelligence.cubemetadata;

import eu.opengovintelligence.admin.opengovintelligence.BasicVariables;

/**
 * Created by Admin on 29/6/2017.
 */

public class Value extends BasicVariables {
    private Dimension dimension;

    public Value(String id, String label, Dimension dimension) {
        super(id, label);
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Value(String id, String label) {
        super(id, label);
    }
}
