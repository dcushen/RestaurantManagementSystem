package com.mdm.restaurantmanagementsystem.model;

import java.io.Serializable;

/**
 * An {@link Shift} object contains information related to a single roster event.
 */
public class Shift implements Serializable
{
    /** ID of the shift */
    private String mId;

    /** shift Id */
    private String mShiftId;

    /** Start Time */
    private String mStartTime;

    /** End Time */
    private String mEndTime;

    /** Employee Id */
    private String mEmployeeId;

    /**
     * Constructs a new {@link Shift} object.
     *
     * @param Id is the id of the menu item.
     * @param shiftId is the name of the menu item.
     * @param startTime is the price of the menu item.
     * @param endTime is the price of the menu item.
     * @param employeeId is the price of the menu item.
     */
    public Shift(String Id, String shiftId, String startTime, String endTime, String employeeId)
    {
        mId = Id;
        mShiftId = shiftId;
        mStartTime = startTime;
        mEndTime = endTime;
        mEmployeeId = employeeId;
    }

    /**
     * Returns the id of the shift.
     */
    public String getId()
    {
        return mId;
    }

    /**
     * Returns the shift id
     */
    public String getShiftId()
    {
        return mShiftId;
    }

    /**
     * Returns the start time
     */
    public String getStartTime()
    {
        return mStartTime;
    }

    /**
     * Returns the end time.
     */
    public String getEndTime()
    {
        return mEndTime;
    }

    /**
     * Returns the employee id.
     */
    public String getEmployeeId()
    {
        return mEmployeeId;
    }
}