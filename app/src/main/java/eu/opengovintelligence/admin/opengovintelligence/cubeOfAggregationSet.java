package eu.opengovintelligence.admin.opengovintelligence;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameters:

 dataset (required)
 1 ore more free dimension as array i.e. dimension[]=DIM1 & dimension[]=DIM2 ...
 Description: returns the cube of an aggregation set that has the specified dimensions

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/cubeOfAggregationSet?dataset=http://id.mkm.ee/statistics/def/cube/buildings&dimension%5B%5D=http://id.mkm.ee/statistics/def/dimension/main_usage&dimension%5B%5D=http://id.mkm.ee/statistics/def/dimension/municipality
 */

public class cubeOfAggregationSet {
}
