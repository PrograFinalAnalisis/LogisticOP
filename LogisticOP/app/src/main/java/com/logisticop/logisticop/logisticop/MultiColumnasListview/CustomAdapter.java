package com.logisticop.logisticop.logisticop.MultiColumnasListview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.logisticop.logisticop.logisticop.R;

/**
 * Created by Programador on 22/04/2015.
 */
public class CustomAdapter extends ArrayAdapter<Model> {
    Model[] modelItems = null;
    Context context;

    public CustomAdapter(Context context, Model[] resource) {
        super(context, R.layout.row, resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.txtnombreemp);


        return convertView;


    }
}