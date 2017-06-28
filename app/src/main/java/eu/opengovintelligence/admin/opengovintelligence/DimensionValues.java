package eu.opengovintelligence.admin.opengovintelligence;

import java.util.ArrayList;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameters:

 dataset (required)
 dimension (required)
 Description: returns all the values of a dimension that appear at a specific cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/dimension-values?dataset=http://id.mkm.ee/statistics/def/cube/crashes&dimension=http://id.mkm.ee/statistics/def/dimension/day
 */

public class DimensionValues {
    ArrayList<Value> valueArrayList = new ArrayList<Value>() ;
    Dimension dimension;

    public DimensionValues(ArrayList<Value> valueArrayList, Dimension dimension) {
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
