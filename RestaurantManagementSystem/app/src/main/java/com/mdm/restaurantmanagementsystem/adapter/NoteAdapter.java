package com.mdm.restaurantmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mdm.restaurantmanagementsystem.R;
import com.mdm.restaurantmanagementsystem.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note>
{
    public NoteAdapter(Activity context, ArrayList<Note> notes)
    {
        super(context,0,notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.note,parent,false);
        }

        Note currentNote = getItem(position);

        TextView id = (TextView) listItemView.findViewById(R.id.tvNoteId);
        id.setText(currentNote.getNoteId());

        TextView title = (TextView) listItemView.findViewById(R.id.tvTitle);
        title.setText(currentNote.getTitle());

        TextView dateWritten = (TextView) listItemView.findViewById(R.id.tvDate);
        dateWritten.setText(currentNote.getDateWritten());

        return listItemView;
    }
}