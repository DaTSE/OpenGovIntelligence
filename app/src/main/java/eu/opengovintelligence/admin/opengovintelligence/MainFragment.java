package eu.opengovintelligence.admin.opengovintelligence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,null);


        TabLayout tabs = (TabLayout) v.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Graph"));
        tabs.addTab(tabs.newTab().setText("Parameters"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*Bundle bundle = new Bundle();
                bundle.putString("today_option",(String)tab.getText());
                mFirebaseAnalytics.logEvent("today_tabs",bundle);*/

                if(tab.getPosition()==0){
                    Fragment childFragment = new GraphFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
                    transaction.replace(R.id.mainFrame2, childFragment).commit();
                }else if (tab.getPosition()==1){
                    Fragment childFragment = new ParametersFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
                    transaction.replace(R.id.mainFrame2, childFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return v;
    }

}
