package eu.opengovintelligence.admin.opengovintelligence;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

/**
 * Created by Admin on 30/6/2017.
 */

public class GraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graph_fragment,null);

        //CallHolder.MakeTableCall(getContext());

        ArrayList<BarEntry> entries = new ArrayList<>();




        int[] data_values = CallHolder.getData();
        String[] headers = CallHolder.getHeaders();
        int dim_values_size = CallHolder.getHeaders().length;


        CallHolder.setData(new int[dim_values_size]);
        CallHolder.setHeaders(new String[dim_values_size]);
        for(int i=0;i<dim_values_size;i++){
            CallHolder.getData()[i] = data_values[i%7];
            CallHolder.getHeaders()[i] = headers[i%7];
        }

        for(int i=0 ; i<CallHolder.getHeaders().length; i++){
            entries.add(new BarEntry(i,CallHolder.getData()[i]));
        }



        BarDataSet dataSet = new BarDataSet(entries, "# of "+CallHolder.getSelectedFreeDimension().getLabel());

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        // in this example, a LineChart is initialized from xml
        BarChart chart = (BarChart) v.findViewById(R.id.chart);

        IAxisValueFormatter xAxisFormatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if( Math.ceil(value) == value || Math.floor(value) == value)
                    return CallHolder.getHeaders()[Math.round(value)];
                else
                    return "";

            }
        };



        chart.setPinchZoom(false);
        chart.getDescription().setText("# "+CallHolder.getSelectedMeasure().getLabel());
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
        leftAxis.setLabelCount(7, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        BarData data = new BarData(dataSet);
        data.setHighlightEnabled(true);
        chart.setData(data);
        chart.setHighlightFullBarEnabled(true);
        chart.setDrawMarkers(true);

        // chart.setDescription("# of times Alice called Bob");
        chart.animateY(2000);
        chart.invalidate();

        return v;
    }

}
