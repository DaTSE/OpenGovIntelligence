package eu.opengovintelligence.admin.opengovintelligence;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.explorecubes.Cube;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Measure;

/**
 * Created by Admin on 28/6/2017.
 */

public class CallHolder {
    private static String cubes;
    private static ArrayList<Cube> cubeArrayList = new ArrayList<Cube>();
    private static Cube selectedCube;

    private static String measures;
    private static ArrayList<Measure> measureArrayList = new ArrayList<Measure>();
    private static Measure selectedMeasure;

    public static String getMeasures() {
        return measures;
    }

    public static void setMeasures(String measures) {
        CallHolder.measures = measures;
    }

    public static ArrayList<Measure> getMeasureArrayList() {
        return measureArrayList;
    }

    public static void setMeasureArrayList(ArrayList<Measure> measureArrayList) {
        CallHolder.measureArrayList = measureArrayList;
    }

    public static Measure getSelectedMeasure() {
        return selectedMeasure;
    }

    public static void setSelectedMeasure(Measure selectedMeasure) {
        CallHolder.selectedMeasure = selectedMeasure;
    }

    public static void MakeCubeCall(final Context context){
        //Cubes call
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
            }

            protected String doInBackground(Void... urls) {
                try {

                    String link = context.getString(R.string.url)+context.getString(R.string.cubes);

                    System.out.println(link);

                    URL url = new URL(link);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();

                        return stringBuilder.toString();
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    System.out.println("ERROR : doInBackground");
                    //loadingDialog.dismiss();
                    return null;
                }
            }
            protected void onPostExecute(final String response) {
                CallHolder.getCubeArrayList().clear();
                CallHolder.setCubes(response);
                //System.out.println(response);
                if(response!=null){
                    try {
                        JSONObject jsonResult = new JSONObject(response);
                        JSONArray jsonArray = (JSONArray) jsonResult.get("cubes");

                        for(int i=0; i< jsonArray.length(); i++){
                            JSONObject indicator = jsonArray.getJSONObject(i);
                            Cube item = new Cube(indicator.getString("@id"),indicator.getString("label"));
                            CallHolder.getCubeArrayList().add(item);

                        }
                        System.out.println(CallHolder.getCubeArrayList());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                System.out.println("----------------------------------------------------------------/ End : "+System.currentTimeMillis());
            }
        }.execute();
    }

    public static void MakeMeasuresCall(final Context context){
        //Cubes call
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
            }

            protected String doInBackground(Void... urls) {
                try {

                    String link = context.getString(R.string.url)+context.getString(R.string.measures)+"?dataset="+CallHolder.getSelectedCube().getId();

                    System.out.println(link);

                    URL url = new URL(link);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();

                        return stringBuilder.toString();
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    System.out.println("ERROR : doInBackground");
                    //loadingDialog.dismiss();
                    return null;
                }
            }
            protected void onPostExecute(final String response) {
                CallHolder.getMeasureArrayList().clear();
                CallHolder.setMeasures(response);
                if(response!=null){
                    try {
                        JSONObject jsonResult = new JSONObject(response);
                        JSONArray jsonArray = (JSONArray) jsonResult.get("measures");

                        for(int i=0; i< jsonArray.length(); i++){
                            JSONObject indicator = jsonArray.getJSONObject(i);
                            Measure item = new Measure(indicator.getString("@id"),indicator.getString("label"));
                            CallHolder.getMeasureArrayList().add(item);

                        }
                        System.out.println(CallHolder.getCubeArrayList());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                System.out.println("----------------------------------------------------------------/ End : "+System.currentTimeMillis());
            }
        }.execute();
    }

    public static Cube getSelectedCube() {
        return selectedCube;
    }

    public static void setSelectedCube(Cube selectedCube) {
        CallHolder.selectedCube = selectedCube;
    }

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
