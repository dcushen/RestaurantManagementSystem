package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.shiftRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WaitersRosterActivity extends AppCompatActivity
{
    TextView tvSTime, tvETime;
    private ProgressDialog loading;
    CalendarView calendarView;
    String shiftId,employeeId, Id, startTime, endTime;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiters_roster);

        calendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView

        tvSTime = (TextView) findViewById(R.id.tvSTime);
        tvETime = (TextView) findViewById(R.id.tvETime);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = preferences.getString("employeeId", ""); //retrieve employee id
        level = preferences.getInt("level",9); //9 is a default value that will be replaced by the real level

        //sets the listener to be notified upon selected date change.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day)
            {
                shiftId = day + "/" + month + "/" + year;
                getData(shiftId,employeeId);
            }
        });
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
                    Intent homeIntent = new Intent(WaitersRosterActivity.this,KitchenDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==1) //Host
                {
                    Intent homeIntent = new Intent(WaitersRosterActivity.this,HostDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else //Wait staff
                {
                    Intent homeIntent = new Intent(WaitersRosterActivity.this,WaitersDashboardActivity.class);
                    startActivity(homeIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData(String shiftId, String employeeId) //Request information from server
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

        shiftRequest retriveShifts = new shiftRequest(shiftId,employeeId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(WaitersRosterActivity.this);
        queue.add(retriveShifts);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++) {
                JSONObject jsonData = result.getJSONObject(x);

                Id = jsonData.getString(Config.KEY_Id);
                shiftId = jsonData.getString(Config.KEY_shiftId);
                startTime = jsonData.getString(Config.KEY_startTime);
                endTime = jsonData.getString(Config.KEY_endTime);
                employeeId = jsonData.getString(Config.KEY_employeeId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tvSTime.setText(startTime);
        tvETime.setText(endTime);
    }
}
