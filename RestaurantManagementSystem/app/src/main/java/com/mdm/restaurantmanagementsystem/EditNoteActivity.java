package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.noteRequest;
import com.mdm.restaurantmanagementsystem.request.updateNoteRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditNoteActivity extends AppCompatActivity
{
    private ProgressDialog loading;
    String title, content, employeeId, noteId, dateWritten;
    int level;
    EditText etNoteTitle, etNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = preferences.getString("employeeId", ""); //retrieve employee id
        level = preferences.getInt("level",9); //9 is a default value that will be replaced by the real level

        Intent intent = getIntent();
        noteId = intent.getStringExtra("noteId");
        retriveNote(noteId);

        etNoteTitle = (EditText) findViewById(R.id.etNoteTitle);
        etNoteContent = (EditText) findViewById(R.id.etNoteContent);
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
                    Intent homeIntent = new Intent(EditNoteActivity.this,KitchenDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==0) //Management
                {
                    Intent homeIntent = new Intent(EditNoteActivity.this,ManagementDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==1) //Host
                {
                    Intent homeIntent = new Intent(EditNoteActivity.this,HostDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else //Wait staff
                {
                    Intent homeIntent = new Intent(EditNoteActivity.this,WaitersDashboardActivity.class);
                    startActivity(homeIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void retriveNote(String noteId)
    {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);
                    loading.dismiss();
                    showJSON(response);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        noteRequest getNoteDetails = new noteRequest(noteId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditNoteActivity.this);
        queue.add(getNoteDetails);
    }

    private void showJSON(String response)
    {
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
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        etNoteTitle.setText(title);
        etNoteContent.setText(content);
    }

    public void updateNote(View view)
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
                        Toast.makeText(EditNoteActivity.this,"Note has been updated",Toast.LENGTH_LONG).show();
                        openNotes();
                    }
                    else
                    {
                        Toast.makeText(EditNoteActivity.this,"Failed, try again.",Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        //Very important that the order of content in () matches the request file, otherwise it wont work.
        updateNoteRequest updateNote = new updateNoteRequest(title, content, noteId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditNoteActivity.this);
        queue.add(updateNote);
    }

    public void openNotes()
    {
        //After note is saved send user back to NotesActivity
        Intent notesIntent = new Intent(EditNoteActivity.this,NotesActivity.class);
        startActivity(notesIntent);
    }
}