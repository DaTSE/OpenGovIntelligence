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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Measure;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.Cube;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.CubeAdapter;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.MeasureAdapter;

/**
 * Created by Admin on 30/6/2017.
 */

public class GraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graph_fragment,null);




        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0,10));
        entries.add(new BarEntry(1,5));
        entries.add(new BarEntry(2,20));
        entries.add(new BarEntry(3,30));
        entries.add(new BarEntry(4,50));
        entries.add(new BarEntry(5,100));

        BarDataSet dataSet = new BarDataSet(entries, "# of Calls");

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        /*ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");*/

        String[] labs = {"January","February","March","April","May","June"};


        // in this example, a LineChart is initialized from xml
        BarChart chart = (BarChart) v.findViewById(R.id.chart);

        IAxisValueFormatter xAxisFormatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value==0)
                    return "January";
                else if(value==1)
                    return "February";
                else if(value==2)
                    return "March";
                else if(value==3)
                    return "April";
                else if(value==4)
                    return "May";
                else if(value==5)
                    return "June";
                return "";
            }
        };


        chart.setPinchZoom(false);
        chart.getDescription().setText("# of times Alice called Bob");
        chart.setDrawGridBackground(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(xAxisFormatter);

        BarData data = new BarData(dataSet);
        data.setHighlightEnabled(true);
        chart.setData(data);
        // chart.setDescription("# of times Alice called Bob");
        chart.animateY(2000);
        chart.invalidate();






        return v;
    }
}
