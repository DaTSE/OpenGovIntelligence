package eu.opengovintelligence.admin.opengovintelligence;

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


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import eu.opengovintelligence.admin.opengovintelligence.explorecubes.Cube;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.CubeAdapter;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Measure;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.MeasureAdapter;


/**
 * Created by Admin on 28/6/2017.
 */

public class MainFragment extends Fragment {

    EditText cube_text ;
    EditText measure_text;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,null);

        cube_text = (EditText) v.findViewById(R.id.cube_text);
        measure_text = (EditText) v.findViewById(R.id.measure_text);

        // in this example, a LineChart is initialized from xml
        BarChart chart = (BarChart) v.findViewById(R.id.chart);

        chart.setDrawBarShadow(true);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);

        //IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        String[] dataObjects = {"George","Sofia","Anna"};
        List<BarEntry> entries = new ArrayList<BarEntry>();

        entries.add(new BarEntry(0,10));
        entries.add(new BarEntry(1,20));
        entries.add(new BarEntry(2,5));

        Legend l = chart.getLegend();
        l.setEnabled(true);

        l.setCustom(new LegendEntry[] {} );

        BarDataSet dataSet = new BarDataSet(entries,"Fuel Type");
        BarData barData = new BarData(dataSet);
        chart.setData(barData);


        // set custom labels and colors

        chart.invalidate();













        cube_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallHolder.setSelectedMeasure(null);
                measure_text.setText("");

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
                        CallHolder.MakeMeasuresCall(getActivity());
                    }
                });

            }
        });

        measure_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainFragment.this.getActivity());

                View dialogView = inflater.inflate(R.layout.search_cube_dialog,null);

                MeasureAdapter myAdapter = new MeasureAdapter(MainFragment.this.getActivity(), CallHolder.getMeasureArrayList() );

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
                        ArrayList<Measure> FilterList = new ArrayList<Measure>();
                        for(int xy=0;xy<CallHolder.getMeasureArrayList().size();xy++){
                            if(CallHolder.getMeasureArrayList().get(xy).getLabel().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                FilterList.add(CallHolder.getMeasureArrayList().get(xy));
                            }
                        }
                        MeasureAdapter FilterAdapter = new MeasureAdapter(MainFragment.this.getActivity(), FilterList );
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
                        CallHolder.setSelectedMeasure((Measure)listview.getItemAtPosition(i));
                        measure_text.setText(CallHolder.getSelectedMeasure().getLabel());
                        dialog.dismiss();
                    }
                });

            }
        });





        return v;
    }

}
