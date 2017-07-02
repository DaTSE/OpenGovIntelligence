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
 * Created by Admin on 2/7/2017.
 */

public class ValueAdapter extends ArrayAdapter<Value> {

    public ValueAdapter(Context context, ArrayList<Value> Listrowdata) {
        super(context, 0, Listrowdata);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Value value = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_adapter_layout, parent, false);
        }

        TextView label = (TextView) convertView.findViewById(R.id.item_label);
        label.setText(value.getLabel());

        return convertView;
    }
}
