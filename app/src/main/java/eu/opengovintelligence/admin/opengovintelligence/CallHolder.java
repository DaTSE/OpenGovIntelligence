package eu.opengovintelligence.admin.opengovintelligence;

import java.util.ArrayList;

/**
 * Created by Admin on 28/6/2017.
 */

public class CallHolder {
    private static String cubes;
    private static ArrayList<Cube> cubeArrayList = new ArrayList<Cube>();



    public static ArrayList<Cube> getCubeArrayList() {
        return cubeArrayList;
    }

    public static void setCubeArrayList(ArrayList<Cube> cubeArrayList) {
        CallHolder.cubeArrayList = cubeArrayList;
    }


    public static String getCubes() {
        return cubes;
    }

    public static void setCubes(String cubes) {
        CallHolder.cubes = cubes;
    }
}
