package com.mdm.restaurantmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mdm.restaurantmanagementsystem.R;
import com.mdm.restaurantmanagementsystem.model.Order;

import java.util.ArrayList;

public class OrdersAdapter extends ArrayAdapter<Order>
{
    public OrdersAdapter(Activity context, ArrayList<Order> orders)
    {
        super(context,0,orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_orders,parent,false);
        }

        Order currentOrder = getItem(position);

        TextView tvOrderId = (TextView) listItemView.findViewById(R.id.tvOrderId);
        tvOrderId.setText(currentOrder.getOrderId());

        TextView tvEmployeeId = (TextView) listItemView.findViewById(R.id.tvEmployeeId);
        tvEmployeeId.setText(currentOrder.getEmployeeId());

        TextView tvTableId = (TextView) listItemView.findViewById(R.id.tvTableId);
        tvTableId.setText(currentOrder.getTableId());

        TextView tvDatePlaced = (TextView) listItemView.findViewById(R.id.tvDatePlaced);
        tvDatePlaced.setText(currentOrder.getDatePlaced());

        TextView tvTotalPrice = (TextView) listItemView.findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText(currentOrder.getTotalPrice());

        TextView tvStatus = (TextView) listItemView.findViewById(R.id.tvStatus);
        tvStatus.setText(currentOrder.getStatus());

        return listItemView;
    }
}
