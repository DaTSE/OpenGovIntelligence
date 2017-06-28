package eu.opengovintelligence.admin.opengovintelligence;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameter: dataset (required)

 Description: returns all the dimensions of a cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/dimensions?dataset=http://id.mkm.ee/statistics/def/cube/buildings
 */

public class Dimension extends BasicVariables {
    public Dimension(String id, String label) {
        super(id, label);
    }
}
