package eu.opengovintelligence.admin.opengovintelligence.explorecubes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.R;

/**
 * Created by Admin on 28/6/2017.
 */

public class CubeAdapter extends ArrayAdapter<Cube> {

    public CubeAdapter(Context context, ArrayList<Cube> Listrowdata) {
        super(context, 0, Listrowdata);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Cube cube = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_adapter_layout, parent, false);
        }

        TextView label = (TextView) convertView.findViewById(R.id.item_label);
        label.setText(cube.getLabel());

        return convertView;
    }
}
