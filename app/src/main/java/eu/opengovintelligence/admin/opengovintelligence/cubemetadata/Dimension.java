package eu.opengovintelligence.admin.opengovintelligence.cubemetadata;

import eu.opengovintelligence.admin.opengovintelligence.BasicVariables;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameter: dataset (required)

 Description: returns all the dimensions of a cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/dimensions?dataset=http://id.mkm.ee/statistics/def/cube/buildings
 */

public class Dimension extends BasicVariables {
    private boolean freeDimension;

    public Dimension(String id, String label, boolean freeDimension) {
        super(id, label);
        this.freeDimension = freeDimension;
    }

    public boolean isFreeDimension() {
        return freeDimension;
    }

    public void setFreeDimension(boolean freeDimension) {
        this.freeDimension = freeDimension;
    }

    public Dimension(String id, String label) {
        super(id, label);
    }
}
