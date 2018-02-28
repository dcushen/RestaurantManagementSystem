package com.mdm.restaurantmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdm.restaurantmanagementsystem.R;
import com.mdm.restaurantmanagementsystem.model.Item;

import java.util.ArrayList;

public class OrderDetailAdapter extends ArrayAdapter<Item>
{
    public OrderDetailAdapter(Activity context, ArrayList<Item> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_order_detail, parent, false);
        }

        Item currentItem = getItem(position);

        TextView tvItemId = (TextView) listItemView.findViewById(R.id.tvItemId);
        tvItemId.setText(currentItem.getItemId());

        TextView tvName = (TextView) listItemView.findViewById(R.id.tvName);
        tvName.setText(currentItem.getName());

        TextView tvQuantity = (TextView) listItemView.findViewById(R.id.tvQuantity);
        tvQuantity.setText(currentItem.getQuantity());

        TextView tvPrice = (TextView) listItemView.findViewById(R.id.tvPrice);
        tvPrice.setText(currentItem.getPrice());

        TextView tvStatus = (TextView) listItemView.findViewById(R.id.tvStatus);
        tvStatus.setText(currentItem.getStatus());

        ImageView tickImage = (ImageView) listItemView.findViewById(R.id.tickImage);
        if(currentItem.getStatus().equals("Completed"))
        {
            tickImage.setVisibility(View.VISIBLE);
        }

        return listItemView;
    }
}