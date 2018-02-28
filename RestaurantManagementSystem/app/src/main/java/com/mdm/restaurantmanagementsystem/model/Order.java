package com.mdm.restaurantmanagementsystem.model;

public class Order
{
    private String mOrderId;
    private String mEmployeeId;
    private String mTableId;
    private String mDatePlaced;
    private String mTotalPrice;
    private String mStatus;

    /**
     * Constructs a new {@link Order} object.
     *
     * @param orderId is the id of the menu item.
     * @param employeeId is the name of the menu item.
     * @param tableId is the price of the menu item.
     * @param datePlaced are the ingredients of the menu item.
     * @param totalPrice are the ingredients of the menu item.
     * @param status are the ingredients of the menu item.
     */
    public Order(String orderId, String employeeId, String tableId, String datePlaced, String totalPrice, String status)
    {
        mOrderId = orderId;
        mEmployeeId = employeeId;
        mTableId = tableId;
        mDatePlaced = datePlaced;
        mTotalPrice = totalPrice;
        mStatus = status;
    }

    /**
     * Returns the id of the menu item.
     */
    public String getOrderId()
    {
        return mOrderId;
    }

    /**
     * Returns the name of the menu item.
     */
    public String getEmployeeId()
    {
        return mEmployeeId;
    }

    /**
     * Returns the ingredients of the menu item
     */
    public String getTableId()
    {
        return mTableId;
    }

    /**
     * Returns the price of the menu item.
     */
    public String getDatePlaced()
    {
        return mDatePlaced;
    }

    /**
     * Returns the price of the menu item.
     */
    public String getTotalPrice()
    {
        return mTotalPrice;
    }

    /**
     * Returns the price of the menu item.
     */
    public String getStatus()
    {
        return mStatus;
    }

    /**
     * Method is called in the WaitersDashboardActivity
     * @return the item values as string, i.e. itemID, name, price etc
     */
    @Override
    public String toString()
    {
        return "Order{" + "mOrderId='" + mOrderId + '\'' + ", mTotalPrice='" + mTotalPrice + '\'' + '}';
    }
}