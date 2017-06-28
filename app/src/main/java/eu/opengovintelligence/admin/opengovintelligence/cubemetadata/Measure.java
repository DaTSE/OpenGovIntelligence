package eu.opengovintelligence.admin.opengovintelligence.cubemetadata;

import eu.opengovintelligence.admin.opengovintelligence.BasicVariables;

/**
 * Created by Admin on 28/6/2017.
 *
 * Parameter: dataset (required)

 Description: returns all the measures of a cube

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/measures?dataset=http://id.mkm.ee/statistics/def/cube/crashes
 */

public class Measure extends BasicVariables {

    public Measure(String id, String label) {
        super(id, label);
    }

}
