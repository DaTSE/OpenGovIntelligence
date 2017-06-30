package eu.opengovintelligence.admin.opengovintelligence.cubemetadata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eu.opengovintelligence.admin.opengovintelligence.R;

/**
 * Created by Admin on 30/6/2017.
 */

public class DimensionAdapter extends ArrayAdapter<Dimension> {

    public DimensionAdapter(Context context, ArrayList<Dimension> Listrowdata) {
        super(context, 0, Listrowdata);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Dimension dimension = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_adapter_layout, parent, false);
        }

        TextView label = (TextView) convertView.findViewById(R.id.item_label);
        label.setText(dimension.getLabel());

        return convertView;
    }
}
