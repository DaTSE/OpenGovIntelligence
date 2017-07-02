package eu.opengovintelligence.admin.opengovintelligence;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Dimension;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.Cube;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Measure;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Value;

/**
 * Created by Admin on 28/6/2017.
 */

public class CallHolder {
    private static Fragment childFragment = null;


    private static String cubes;
    private static ArrayList<Cube> cubeArrayList = new ArrayList<Cube>();
    private static Cube selectedCube;

    private static String measures;
    private static ArrayList<Measure> measureArrayList = new ArrayList<Measure>();
    private static Measure selectedMeasure;

    private static String dimensions;
    private static ArrayList<Dimension> dimensionArrayList = new ArrayList<Dimension>();
    private static Dimension selectedFreeDimension;
    private static int selectedFreeDimensionPos;



    private static ArrayList<String> dimensions_values = new ArrayList<>();
    private static ArrayList<ArrayList<Value>> dimension_values_list = new ArrayList<>();
    private static ArrayList<Value> selected_dimension_values = new ArrayList<>();

    public static ArrayList<String> getDimensions_values() {
        return dimensions_values;
    }

    public static void setDimensions_values(ArrayList<String> dimensions_values) {
        CallHolder.dimensions_values = dimensions_values;
    }

    public static ArrayList<ArrayList<Value>> getDimension_values_list() {
        return dimension_values_list;
    }

    public static void setDimension_values_list(ArrayList<Value> dimension_values_list) {
        CallHolder.dimension_values_list.add(dimension_values_list);
    }

    public static int getSelectedFreeDimensionPos() {
        return selectedFreeDimensionPos;
    }

    public static void setSelectedFreeDimensionPos(int selectedFreeDimensionPos) {
        CallHolder.selectedFreeDimensionPos = selectedFreeDimensionPos;
    }

    public static ArrayList<Value> getSelected_dimension_values() {
        return selected_dimension_values;
    }

    public static void setSelected_dimension_values(ArrayList<Value> selected_dimension_values) {
        CallHolder.selected_dimension_values = selected_dimension_values;
    }

    public static String getDimensions() {
        return dimensions;
    }

    public static void setDimensions(String dimensions) {
        CallHolder.dimensions = dimensions;
    }

    public static ArrayList<Dimension> getDimensionArrayList() {
        return dimensionArrayList;
    }

    public static void setDimensionArrayList(ArrayList<Dimension> dimensionArrayList) {
        CallHolder.dimensionArrayList = dimensionArrayList;
    }

    public static Dimension getSelectedFreeDimension() {
        return selectedFreeDimension;
    }

    public static void setSelectedFreeDimension(Dimension selectedFreeDimension) {
        CallHolder.selectedFreeDimension = selectedFreeDimension;
    }

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
                        System.out.println(CallHolder.getMeasureArrayList());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                System.out.println("----------------------------------------------------------------/ End : "+System.currentTimeMillis());
            }
        }.execute();
    }

    public static void MakeDimensionsCall(final Context context){
        //Cubes call
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
            }

            protected String doInBackground(Void... urls) {
                try {

                    String link = context.getString(R.string.url)+context.getString(R.string.dimensions)+"?dataset="+CallHolder.getSelectedCube().getId();

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
                CallHolder.getDimensionArrayList().clear();
                CallHolder.setDimensions(response);
                if(response!=null){
                    try {
                        JSONObject jsonResult = new JSONObject(response);
                        JSONArray jsonArray = (JSONArray) jsonResult.get("dimensions");

                        for(int i=0; i< jsonArray.length(); i++){
                            JSONObject indicator = jsonArray.getJSONObject(i);
                            Dimension item = new Dimension(indicator.getString("@id"),indicator.getString("label"));
                            CallHolder.getDimensionArrayList().add(item);

                        }
                        System.out.println(CallHolder.getDimensionArrayList());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    CallHolder.MakeDimensionValuesCall(context);

                }

                System.out.println("----------------------------------------------------------------/ End : "+System.currentTimeMillis());
            }
        }.execute();
    }

    public static void MakeDimensionValuesCall(final Context context){
        CallHolder.getDimensions_values().clear();
        CallHolder.getDimension_values_list().clear();

        final ProgressDialog loadingDialog = new ProgressDialog(context);
        loadingDialog.setTitle("Please wait");
        loadingDialog.setMessage("Loading dimensions");
        loadingDialog.setCancelable(false);
        loadingDialog.show();
        for(int i=0;i<getDimensionArrayList().size();i++){
            final ArrayList<Value> values = new ArrayList<>();
            final int j = i;
            new AsyncTask<Void, Void, String>() {

                    @Override
                    protected void onPreExecute() {
                    }

                    protected String doInBackground(Void... urls) {
                        try {
                            String link = context.getString(R.string.url)+context.getString(R.string.dimension_values)+"?dataset="+CallHolder.getSelectedCube().getId()+"&dimension="+CallHolder.getDimensionArrayList().get(j).getId();

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
                        CallHolder.getDimensions_values().add(response);
                        if(response!=null){
                            try {
                                JSONObject jsonResult = new JSONObject(response);
                                JSONArray jsonArray = (JSONArray) jsonResult.get("values");

                                for(int i=0; i< jsonArray.length(); i++){
                                    JSONObject indicator = jsonArray.getJSONObject(i);
                                    Value item = new Value(indicator.getString("@id"),indicator.getString("label"));
                                    values.add(item);

                                }
                                CallHolder.setDimension_values_list(values);
                                System.out.println(CallHolder.getDimension_values_list().get(j));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        System.out.println("----------------------------------------------------------------/ End : "+System.currentTimeMillis());
                        if(j==getDimensionArrayList().size()-1){
                            loadingDialog.dismiss();

                        }

                    }
                }.execute();

        }
        //Cubes call

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

    public static Fragment getChildFragment() {
        return childFragment;
    }

    public static void setChildFragment(Fragment childFragment) {
        CallHolder.childFragment = childFragment;
    }


}
