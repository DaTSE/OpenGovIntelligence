package eu.opengovintelligence.admin.opengovintelligence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Dimension;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.DimensionAdapter;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Measure;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.Value;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.ValueAdapter;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.Cube;
import eu.opengovintelligence.admin.opengovintelligence.explorecubes.CubeAdapter;
import eu.opengovintelligence.admin.opengovintelligence.cubemetadata.MeasureAdapter;

/**
 * Created by Admin on 30/6/2017.
 */

public class ParametersFragment extends Fragment {
    EditText cube_text ;
    EditText measure_text;
    EditText free_dimension;
    LinearLayout dimensions_layout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.parameters_layout,null);

        dimensions_layout = (LinearLayout) v.findViewById(R.id.dimensions_layout);

        cube_text = (EditText) v.findViewById(R.id.cube_text);
        measure_text = (EditText) v.findViewById(R.id.measure_text);
        free_dimension = (EditText) v.findViewById(R.id.free_dimension);

        if(CallHolder.getSelectedCube()!= null)
            cube_text.setText(CallHolder.getSelectedCube().getLabel());
        if(CallHolder.getSelectedMeasure()!= null)
            measure_text.setText(CallHolder.getSelectedMeasure().getLabel());
        if(CallHolder.getSelectedFreeDimension()!= null) {
            free_dimension.setText(CallHolder.getSelectedFreeDimension().getLabel());
            if(CallHolder.getSelected_dimension_values().size()!=0)
                showRestDimensions(true);
            else
                showRestDimensions(false);
        }

        cube_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallHolder.setSelectedMeasure(null);
                CallHolder.setSelectedFreeDimension(null);
                dimensions_layout.removeAllViews();
                free_dimension.setText("");
                measure_text.setText("");

                final AlertDialog.Builder builder = new AlertDialog.Builder(ParametersFragment.this.getActivity());

                View dialogView = inflater.inflate(R.layout.search_cube_dialog,null);

                CubeAdapter myAdapter = new CubeAdapter(ParametersFragment.this.getActivity(), CallHolder.getCubeArrayList() );

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
                        CubeAdapter FilterAdapter = new CubeAdapter(ParametersFragment.this.getActivity(), FilterList );
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
                        CallHolder.MakeDimensionsCall(getActivity());
                    }
                });

            }
        });

        measure_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ParametersFragment.this.getActivity());

                View dialogView = inflater.inflate(R.layout.search_cube_dialog,null);

                MeasureAdapter myAdapter = new MeasureAdapter(ParametersFragment.this.getActivity(), CallHolder.getMeasureArrayList() );

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
                        MeasureAdapter FilterAdapter = new MeasureAdapter(ParametersFragment.this.getActivity(), FilterList );
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

        free_dimension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ParametersFragment.this.getActivity());

                View dialogView = inflater.inflate(R.layout.search_cube_dialog,null);

                DimensionAdapter myAdapter = new DimensionAdapter(ParametersFragment.this.getActivity(), CallHolder.getDimensionArrayList() );

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
                        ArrayList<Dimension> FilterList = new ArrayList<Dimension>();
                        for(int xy=0;xy<CallHolder.getDimensionArrayList().size();xy++){
                            if(CallHolder.getDimensionArrayList().get(xy).getLabel().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                FilterList.add(CallHolder.getDimensionArrayList().get(xy));
                            }
                        }
                        DimensionAdapter FilterAdapter = new DimensionAdapter(ParametersFragment.this.getActivity(), FilterList );
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
                        CallHolder.getSelected_dimension_values().clear();
                        CallHolder.setSelectedFreeDimension((Dimension)listview.getItemAtPosition(i));
                        free_dimension.setText(CallHolder.getSelectedFreeDimension().getLabel());
                        dialog.dismiss();

                        showRestDimensions(false);
                    }
                });

            }
        });

        return v;
    }

    public void showRestDimensions(boolean start_values){

        CallHolder.MakeDimensionValuesCall(getActivity());
        dimensions_layout.removeAllViews();
        for(int position=0;position<CallHolder.getDimensionArrayList().size();position++){
            if(CallHolder.getSelectedFreeDimension()!=CallHolder.getDimensionArrayList().get(position)) {
                TextInputLayout textInputLayout = new TextInputLayout(getActivity());
                final EditText edit_text = new EditText(getActivity());
                if(start_values && CallHolder.getSelected_dimension_values().size()>position)
                    edit_text.setText(CallHolder.getSelected_dimension_values().get(position).getLabel());
                else
                    edit_text.setText("All");
                edit_text.setHint(CallHolder.getDimensionArrayList().get(position).getLabel());
                edit_text.setFocusableInTouchMode(false);
                textInputLayout.addView(edit_text);
                dimensions_layout.addView(textInputLayout);

                final int finalPosition = position;
                edit_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(ParametersFragment.this.getActivity());

                        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.search_cube_dialog,null);

                        ValueAdapter myAdapter = new ValueAdapter(ParametersFragment.this.getActivity(), CallHolder.getDimension_values_list().get(finalPosition) );

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
                                ArrayList<Value> FilterList = new ArrayList<Value>();
                                for(int xy=0;xy<CallHolder.getDimension_values_list().get(finalPosition).size();xy++){
                                    if(CallHolder.getDimension_values_list().get(finalPosition).get(xy).getLabel().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                        FilterList.add(CallHolder.getDimension_values_list().get(finalPosition).get(xy));
                                    }
                                }
                                ValueAdapter FilterAdapter = new ValueAdapter(ParametersFragment.this.getActivity(), FilterList );
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
                                CallHolder.getSelected_dimension_values().add((Value)listview.getItemAtPosition(i));
                                edit_text.setText(((Value) listview.getItemAtPosition(i)).getLabel());
                                dialog.dismiss();

                            }
                        });
                    }
                });
            }
        }
    }
}
