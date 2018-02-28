package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.ShiftAdapter;
import com.mdm.restaurantmanagementsystem.model.Shift;
import com.mdm.restaurantmanagementsystem.request.rosterRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagementRosterActivity extends AppCompatActivity
{
    private ProgressDialog loading;
    CalendarView calendarView;
    String shiftId,employeeId, Id, startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_roster);

        calendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView

        //sets the listener to be notified upon selected date change.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day)
            {
                shiftId = day + "/" + month + "/" + year;
                getData(shiftId);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);

        // Use a generated id from resources (ids.xml) to ensure that all menu ids are distinct.
        MenuItem NewRoster = menu.add(0, R.id.menu_new_roster, 0, "Roster");
        NewRoster.setIcon(R.drawable.ic_add_circle_black);

        // Need to use MenuItemCompat methods to call any action item related methods
        MenuItemCompat.setShowAsAction(NewRoster, MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_home:
                Intent homeIntent = new Intent(this, ManagementDashboardActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.menu_new_roster:
                Intent newItemIntent = new Intent(this, NewRosterActivity.class);
                startActivity(newItemIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData(String shiftId) //Request information from server
    {
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
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

        rosterRequest retriveShifts = new rosterRequest(shiftId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ManagementRosterActivity.this);
        queue.add(retriveShifts);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        final ArrayList<Shift> rosterList = new ArrayList<Shift>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                Id = jsonData.getString(Config.KEY_Id);
                shiftId = jsonData.getString(Config.KEY_shiftId);
                startTime = jsonData.getString(Config.KEY_startTime);
                endTime = jsonData.getString(Config.KEY_endTime);
                employeeId = jsonData.getString(Config.KEY_employeeId);

                rosterList.add(new Shift(Id,shiftId,startTime,endTime,employeeId));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ShiftAdapter adapter = new ShiftAdapter(this, rosterList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
