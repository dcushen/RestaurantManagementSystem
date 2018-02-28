package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.util.UUID.randomUUID;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.newNoteRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class NewNoteActivity extends AppCompatActivity
{
    String title, content, employeeId, noteId;
    int level;
    EditText etNoteTitle, etNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = preferences.getString("employeeId", ""); //retrieve employee id
        level = preferences.getInt("level",9); //9 is a default value that will be replaced by the real level

        etNoteTitle = (EditText) findViewById(R.id.etNoteTitle);
        etNoteContent = (EditText) findViewById(R.id.etNoteContent);

        noteId = randomUUID().toString().substring(0, 8);//Create a random note id
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
                    Intent homeIntent = new Intent(NewNoteActivity.this,KitchenDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==0) //Management
                {
                    Intent homeIntent = new Intent(NewNoteActivity.this,ManagementDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==1) //Host
                {
                    Intent homeIntent = new Intent(NewNoteActivity.this,HostDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else //Wait staff
                {
                    Intent homeIntent = new Intent(NewNoteActivity.this,WaitersDashboardActivity.class);
                    startActivity(homeIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveNote(View view)
    {
        title = etNoteTitle.getText().toString();
        content = etNoteContent.getText().toString();

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
                        Toast.makeText(NewNoteActivity.this,"Note saved",Toast.LENGTH_SHORT).show();
                        openNotes();
                    }
                    else
                    {
                        Toast.makeText(NewNoteActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        newNoteRequest newNote = new newNoteRequest(title,content,noteId,employeeId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NewNoteActivity.this);
        queue.add(newNote);
    }

    public void openNotes()
    {
        //After note is saved send user back to NotesActivity
        Intent notesIntent = new Intent(NewNoteActivity.this,NotesActivity.class);
        startActivity(notesIntent);
    }
}