package com.mdm.restaurantmanagementsystem.model;

import java.io.Serializable;

/**
 * An {@link Item} object contains information related to a single menu item.
 */
public class Item implements Serializable
{
    /** ID of the item */
    private String mItemId;

    /** Name of the item */
    private String mName;

    /** Ingredients of the item */
    private String mIngredients;

    /** Price of the Item */
    private String mPrice;

    private String mStatus;

    private String mQuantity;

    /**
     * Constructs a new {@link Item} object.
     *
     * @param itemId is the id of the menu item.
     * @param name is the name of the menu item.
     * @param price is the price of the menu item.
     */
    public Item(String itemId, String name, String price)
    {
        mItemId = itemId;
        mName = name;
        mPrice = price;
    }

    public Item(String itemId, String name, String price, String status, String quantity)
    {
        mItemId = itemId;
        mName = name;
        mPrice = price;
        mStatus = status;
        mQuantity = quantity;
    }

    /**
     * Returns the id of the menu item.
     */
    public String getItemId()
    {
        return mItemId;
    }

    /**
     * Returns the name of the menu item.
     */
    public String getName()
    {
        return mName;
    }

    /**
     * Returns the ingredients of the menu item
     */
    public String getIngredients()
    {
        return mIngredients;
    }

    /**
     * Returns the price of the menu item.
     */
    public String getPrice()
    {
        return mPrice;
    }

    public String getStatus()
    {
        return mStatus;
    }

    public String getQuantity()
    {
        return mQuantity;
    }

    /**
     * Method is called in the WaitersDashboardActivity
     * @return the item values as string, i.e. itemID, name, price etc
     */
    @Override
    public String toString()
    {
        return "Item{" + "mName='" + mName + '\'' + ", mPrice='" + mPrice + '\'' + '}';
    }
}