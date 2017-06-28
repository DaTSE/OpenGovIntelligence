package eu.opengovintelligence.admin.opengovintelligence;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameter: none

 Description: returns only the initial cubes, not including the aggregated cubes created by the Data Cube Aggregator

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/aggregationSetcubes
 */

public class aggregationSetCube extends BasicVariables {
    public aggregationSetCube(String id, String label) {
        super(id, label);
    }
}
