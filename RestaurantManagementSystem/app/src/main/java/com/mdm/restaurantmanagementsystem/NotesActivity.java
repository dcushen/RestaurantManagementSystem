package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.NoteAdapter;
import com.mdm.restaurantmanagementsystem.model.Note;
import com.mdm.restaurantmanagementsystem.request.removeNoteRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity
{
    String title, content, employeeId, noteId, dateWritten;
    int level;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = preferences.getString("employeeId", ""); //retrieve employee id
        level = preferences.getInt("level",9); //9 is a default value that will be replaced by the real level

        getData(); //Retrieve notes+
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_home:
                if(level==4) //Kitchen
                {
                    Intent homeIntent = new Intent(NotesActivity.this,KitchenDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==0) //Management
                {
                    Intent homeIntent = new Intent(NotesActivity.this,ManagementDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==1) //Host
                {
                    Intent homeIntent = new Intent(NotesActivity.this,HostDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else //Wait staff
                {
                    Intent homeIntent = new Intent(NotesActivity.this,WaitersDashboardActivity.class);
                    startActivity(homeIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData() //Request notes information from server
    {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        StringRequest stringRequest = new StringRequest(Config.Notes_URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(NotesActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        final ArrayList<Note> notesList = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                title = jsonData.getString(Config.KEY_Title);
                content = jsonData.getString(Config.KEY_Content);
                employeeId = jsonData.getString(Config.KEY_EmployeeId);
                noteId = jsonData.getString(Config.KEY_NoteId);
                dateWritten = jsonData.getString(Config.KEY_DateWritten);

                notesList.add(new Note(title,content,employeeId,noteId,dateWritten));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        NoteAdapter adapter = new NoteAdapter(this, notesList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set a click listener to edit note
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Get the {@link Item} object at the given position the user clicked on
                Note note = notesList.get(position);
                noteId = note.getNoteId();
                openEditNoteActivity(noteId);
            }
        });

        //Ask user if they want to delete the item after a long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Get the {@link Item} object at the given position the user clicked on
                Note note = notesList.get(position);
                title = note.getTitle();
                noteId = note.getNoteId();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NotesActivity.this);
                alertDialogBuilder.setMessage("Are you sure, you want to delete: " + title);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        deleteNote(noteId);
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(NotesActivity.this,"Note wasn't deleted.",Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    public void openEditNoteActivity(String noteId)
    {
        Intent editNote = new Intent(this, EditNoteActivity.class);
        editNote.putExtra("noteId", noteId);
        startActivity(editNote);
    }

    public void deleteNote(String noteId)
    {
        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success)
                    {
                        Toast.makeText(NotesActivity.this,"Note was deleted",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(NotesActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        removeNoteRequest removeNote = new removeNoteRequest(noteId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NotesActivity.this);
        queue.add(removeNote);

        //Refresh activity
        finish();
        startActivity(getIntent());
    }

    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(this,NewNoteActivity.class);
        startActivity(newNoteIntent);
    }
}
