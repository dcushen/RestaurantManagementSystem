package com.mdm.restaurantmanagementsystem.model;

import java.io.Serializable;

/**
 * An {@link Note} object contains information related to a single note.
 */
public class Note implements Serializable
{
    /** ID of the note */
    private String mNoteId;

    /** Name of the note */
    private String mTitle;

    /** Content of the note */
    private String mContent;

    /** The date the note was written */
    private String mDateWritten;

    /** The id of the employee who created the note */
    private String mEmployeeId;


    /**
     * Constructs a new {@link Note} object.
     *
     * @param title is the title of the note.
     * @param content is the message of the note.
     * @param employeeId is the id of the employee who created the note.
     * @param noteId is the id of the note.
     * @param dateWritten is the date the note was written.
     */
    public Note(String title, String content, String employeeId, String noteId, String dateWritten)
    {
        mTitle = title;
        mContent = content;
        mEmployeeId = employeeId;
        mNoteId = noteId;
        mDateWritten = dateWritten;
    }

    /**
     * Returns the id of the note.
     */
    public String getNoteId()
    {
        return mNoteId;
    }

    /**
     * Returns the title of the note.
     */
    public String getTitle()
    {
        return mTitle;
    }

    /**
     * Returns the content of the note
     */
    public String getContent()
    {
        return mContent;
    }

    /**
     * Returns the employee id of the creator of the note.
     */
    public String getEmployeeId()
    {
        return mEmployeeId;
    }

    /**
     * @return the date the note was created
     */
    public String getDateWritten()
    {
        return mDateWritten;
    }
}