package com.mdm.restaurantmanagementsystem;

import com.mdm.restaurantmanagementsystem.model.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Used to pass selected items from the WaitersMenuActivity to the OrderActivity.
 */
public class DataWrapper implements Serializable
{
    private ArrayList<Item> orderList;

    public DataWrapper(ArrayList<Item> data)
    {
        this.orderList = data;
    }

    public ArrayList<Item> getOrderLists()
    {
        return this.orderList;
    }
}