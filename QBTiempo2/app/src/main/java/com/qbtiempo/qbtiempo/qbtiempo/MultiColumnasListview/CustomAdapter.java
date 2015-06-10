package com.qbtiempo.qbtiempo.qbtiempo.MultiColumnasListview;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import static com.qbtiempo.qbtiempo.qbtiempo.MultiColumnasListview.Constants.LOG_TAG;
import com.qbtiempo.qbtiempo.qbtiempo.R;

/**
 * Created by Programador on 22/04/2015.
 */
public class CustomAdapter extends ArrayAdapter<Model>{
        Model[] modelItems = null;
        Context context;

public CustomAdapter(Context context, Model[] resource) {
        super(context,R.layout.row,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;

        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.txtnombreemp);

     /*   CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        name.setText(modelItems[position].getName());
        if(modelItems[position].getValue() == 1)
        {
                cb.setChecked(true);

        }
        else
        {
                cb.setChecked(false);

        }*/
        return convertView;


        }
        }