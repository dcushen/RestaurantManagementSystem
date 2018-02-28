package com.mdm.restaurantmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mdm.restaurantmanagementsystem.R;
import com.mdm.restaurantmanagementsystem.model.ResItem;

import java.util.ArrayList;

public class ReservationAdapter extends ArrayAdapter<ResItem>
{
    public ReservationAdapter(Activity context, ArrayList<ResItem> items)
    {
        super(context,0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_item,parent,false);
        }

        ResItem currentItem = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.tvName);
        name.setText(currentItem.getName());

        TextView phone = (TextView) listItemView.findViewById(R.id.tvPhone);
        phone.setText(currentItem.getPhone());

        TextView date = (TextView) listItemView.findViewById(R.id.tvDate);
        date.setText(currentItem.getDate());

        return listItemView;
    }
}