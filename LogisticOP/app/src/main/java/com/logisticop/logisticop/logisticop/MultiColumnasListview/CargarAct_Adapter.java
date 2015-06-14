package com.logisticop.logisticop.logisticop.MultiColumnasListview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logisticop.logisticop.logisticop.R;

import java.util.List;

import clases.CargarActividadesRegistradas;

/**
 * Created by Programador on 28/04/2015.
 */
public class CargarAct_Adapter extends BaseAdapter {

        private Context context;
        private List<CargarActividadesRegistradas> items;

        public CargarAct_Adapter(Context context, List<CargarActividadesRegistradas> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        @Override
        public Object getItem(int position) {
            return this.items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (convertView == null) {
                // Create a new view into the list.
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.row, parent, false);
            }

            // Set data into the view.
            //ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
            TextView txtnombreemp = (TextView) rowView.findViewById(R.id.txtnombreemp);
            TextView txtact = (TextView) rowView.findViewById(R.id.txtnomactividad);
            TextView txtfecha = (TextView) rowView.findViewById(R.id.txtfecha);
            TextView txthoras = (TextView) rowView.findViewById(R.id.txtHoras);

            CargarActividadesRegistradas item = this.items.get(position);
            txtnombreemp.setText(item.getNombreEmpleado());
            txtact.setText(item.getNombreActividad());
            txtfecha.setText(item.getFecha());
            txthoras.setText(item.getHoras() );
            //ivItem.setImageResource(item.getImage());

            return rowView;
        }

    }
