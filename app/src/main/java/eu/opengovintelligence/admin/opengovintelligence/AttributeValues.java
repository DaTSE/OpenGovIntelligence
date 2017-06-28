package eu.opengovintelligence.admin.opengovintelligence;

import java.util.ArrayList;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameters:

 dataset (required)
 attribute (required)
 Description: returns all the values of an attribute that appear at a specific cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/attribute-values?dataset=http://id.mkm.ee/statistics/def/cube/crashes&attribute=http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure
 */

public class AttributeValues {
    ArrayList<Value> valueArrayList = new ArrayList<Value>();
    Dimension dimension;

    public AttributeValues(ArrayList<Value> valueArrayList, Dimension dimension) {
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
