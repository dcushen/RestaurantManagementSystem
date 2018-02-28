package com.mdm.restaurantmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mdm.restaurantmanagementsystem.R;
import com.mdm.restaurantmanagementsystem.model.Shift;

import java.util.ArrayList;

public class ShiftAdapter extends ArrayAdapter<Shift>
{
    public ShiftAdapter(Activity context, ArrayList<Shift> Shifts)
    {
        super(context,0,Shifts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_roster_management,parent,false);
        }

        Shift currentShift = getItem(position);

        TextView id = (TextView) listItemView.findViewById(R.id.tvEmployeeId);
        id.setText(currentShift.getEmployeeId());

        TextView startTime = (TextView) listItemView.findViewById(R.id.tvStartTime);
        startTime.setText(currentShift.getStartTime());

        TextView endTime = (TextView) listItemView.findViewById(R.id.tvEndTime);
        endTime.setText(currentShift.getEndTime());

        return listItemView;
    }
}