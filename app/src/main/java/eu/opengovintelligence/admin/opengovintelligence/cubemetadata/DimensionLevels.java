package eu.opengovintelligence.admin.opengovintelligence.cubemetadata;

import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.explorecubes.Value;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameters:

 dataset (required)
 dimension (required)
 Description: returns all the levels of dimension values (in case of hierarchical data) that appear at a specific cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/dimension-levels?dataset=http://id.vlaanderen.be/statistieken/dq/kubus-bouwvergunningen%23id&dimension=http://id.vlaanderen.be/statistieken/def%23refArea
 */

public class DimensionLevels {
    ArrayList<Value> valueArrayList = new ArrayList<Value>();
    Dimension dimension;

    public DimensionLevels(ArrayList<Value> valueArrayList, Dimension dimension) {
        this.valueArrayList = valueArrayList;
        this.dimension = dimension;
    }

    public ArrayList<Value> getValueArrayList() {
        return valueArrayList;
    }

    public void setValueArrayList(ArrayList<Value> valueArrayList) {
        this.valueArrayList = valueArrayList;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
