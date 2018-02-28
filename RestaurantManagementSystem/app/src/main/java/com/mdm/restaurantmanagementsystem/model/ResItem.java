package com.mdm.restaurantmanagementsystem.model;

import java.io.Serializable;

public class ResItem implements Serializable
{
    private String mDate;

    private String mName;

    private String mTableNo;

    private String mPhone;

    private String mTime;

    private String mBookID;


    public ResItem(String date, String name, String table, String phone, String time, String bookID)
    {
        mDate = date;
        mName = name;
        mTableNo = table;
        mPhone = phone;
        mTime = time;
        mBookID = bookID;

    }

    public ResItem(String name, String table, String time, String phone, String date)
    {
        mName = name;
        mTableNo = table;
        mPhone = phone;
        mTime = time;
        mDate = date;
    }


    public String getDate()
    {
        return mDate;
    }

    public String getID() { return mBookID; }

    public String getName()
    {
        return mName;
    }


    public String getPhone()
    {
        return mPhone;
    }

    public String getTable()
    {
        return mTableNo;
    }

    public String getTime()
    {
        return mTime;
    }

    @Override
    public String toString()
    {
        return "Item{"+"Name:" + mName + "Table Number: " + mTableNo + '\'' + "Time:" + mTime + '}';
    }
}