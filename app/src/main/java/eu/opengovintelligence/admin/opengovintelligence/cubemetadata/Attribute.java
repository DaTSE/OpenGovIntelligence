package eu.opengovintelligence.admin.opengovintelligence.cubemetadata;

import eu.opengovintelligence.admin.opengovintelligence.BasicVariables;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameter: dataset (required)

 Description: returns all the attributes of a cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/attributes?dataset=http://id.mkm.ee/statistics/def/cube/crashes
 */

public class Attribute extends BasicVariables {
    public Attribute(String id, String label) {
        super(id, label);
    }
}
