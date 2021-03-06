package com.mdm.restaurantmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mdm.restaurantmanagementsystem.R;
import com.mdm.restaurantmanagementsystem.model.Item;

import java.util.ArrayList;

public class ManagementItemAdapter extends ArrayAdapter<Item>
{
    public ManagementItemAdapter(Activity context, ArrayList<Item> items)
    {
        super(context,0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_management_item,parent,false);
        }

        Item currentItem = getItem(position);

        TextView id = (TextView) listItemView.findViewById(R.id.tvItemId);
        id.setText(currentItem.getItemId());

        TextView name = (TextView) listItemView.findViewById(R.id.tvName);
        name.setText(currentItem.getName());

        TextView price = (TextView) listItemView.findViewById(R.id.tvPrice);
        price.setText(currentItem.getPrice());

        return listItemView;
    }
}