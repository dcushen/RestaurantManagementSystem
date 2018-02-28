package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.addNewShiftRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewRosterEntryActivity extends AppCompatActivity
{
    private ProgressDialog loading;
    String shiftId,employeeId;
    Spinner nameSpinner, startSpinner,endSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_roster_entry);

        shiftId = getIntent().getStringExtra("shiftId");

        startSpinner = (Spinner) findViewById(R.id.startSpinner);
        ArrayAdapter<CharSequence> startAdapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startSpinner.setAdapter(startAdapter);

        endSpinner = (Spinner) findViewById(R.id.endSpinner);
        ArrayAdapter<CharSequence> endAdapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
        endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endSpinner.setAdapter(endAdapter);

        getData();
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
                Intent homeIntent = new Intent(this,ManagementDashboardActivity.class);
                startActivity(homeIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData() //Request information from server
    {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        StringRequest stringRequest = new StringRequest(Config.Names_URL, new Response.Listener<String>()
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
                        Toast.makeText(NewRosterEntryActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        ArrayList<String> namesList = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                employeeId = jsonData.getString(Config.KEY_employeeId);

                namesList.add(employeeId);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, namesList);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(nameAdapter);
    }

    public void saveShift(View view)
    {
        String name = nameSpinner.getSelectedItem().toString();
        String start = startSpinner.getSelectedItem().toString();
        String end = endSpinner.getSelectedItem().toString();

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
                        Toast.makeText(NewRosterEntryActivity.this,"Shift added",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(NewRosterEntryActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        addNewShiftRequest newMenuItem = new addNewShiftRequest(shiftId,name,start,end, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NewRosterEntryActivity.this);
        queue.add(newMenuItem);
    }
}
