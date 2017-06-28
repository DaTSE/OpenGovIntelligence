package eu.opengovintelligence.admin.opengovintelligence;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Admin on 28/6/2017.
 */

public class MainFragment extends Fragment {

    EditText cube_text ;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,null);

        cube_text = (EditText) v.findViewById(R.id.cube_text);

        String response = CallHolder.getCubes();
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



        cube_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(MainFragment.this.getActivity());

                View dialogView = inflater.inflate(R.layout.search_cube_dialog,null);

                CubeAdapter myAdapter = new CubeAdapter(MainFragment.this.getActivity(), CallHolder.getCubeArrayList() );

                final ListView listview = (ListView) dialogView.findViewById(R.id.listview);
                listview.setAdapter(myAdapter);

                builder.setView(dialogView);



                final EditText label = (EditText) dialogView.findViewById(R.id.search_cube_box);
                label.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        label.setText("");
                    }
                });
                label.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ArrayList<Cube> FilterList = new ArrayList<Cube>();
                        for(int xy=0;xy<CallHolder.getCubeArrayList().size();xy++){
                            if(CallHolder.getCubeArrayList().get(xy).getLabel().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                FilterList.add(CallHolder.getCubeArrayList().get(xy));
                            }
                        }
                        CubeAdapter FilterAdapter = new CubeAdapter(MainFragment.this.getActivity(), FilterList );
                        listview.setAdapter(FilterAdapter);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                final AlertDialog dialog = builder.show();

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        CallHolder.setSelectedCube((Cube)listview.getItemAtPosition(i));
                        cube_text.setText(CallHolder.getSelectedCube().getLabel());
                        dialog.dismiss();
                    }
                });




            }
        });





        return v;
    }

    public void CallJSON(){

    }
}
