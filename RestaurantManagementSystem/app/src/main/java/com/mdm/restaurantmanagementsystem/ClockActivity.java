package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.insertEndTimeRequest;
import com.mdm.restaurantmanagementsystem.request.insertStartTimeRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClockActivity extends AppCompatActivity
{
    String employeeId, formattedTime;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        TextView tvWaiterName = (TextView) findViewById(R.id.tvWaiterName);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = preferences.getString("employeeId", ""); //retrieve employee id
        level = preferences.getInt("level",9); //9 is a default value that will be replaced by the real level
        tvWaiterName.append(employeeId);

        TextView tvTimer = (TextView) findViewById(R.id.tvTimer);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        formattedTime = df.format(c.getTime());
        tvTimer.setText(formattedTime);
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
                    Intent homeIntent = new Intent(ClockActivity.this,KitchenDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==0) //Management
                {
                    Intent homeIntent = new Intent(ClockActivity.this,ManagementDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==1) //Host
                {
                    Intent homeIntent = new Intent(ClockActivity.this,HostDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else //Wait staff
                {
                    Intent homeIntent = new Intent(ClockActivity.this,WaitersDashboardActivity.class);
                    startActivity(homeIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insertStartTime(View view)
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
                        Toast.makeText(ClockActivity.this,"Shift started.",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ClockActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        insertStartTimeRequest start = new insertStartTimeRequest(employeeId,formattedTime, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ClockActivity.this);
        queue.add(start);
    }

    public void insertEndTime(View view)
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
                        Toast.makeText(ClockActivity.this,"Shift ended.",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ClockActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        insertEndTimeRequest start = new insertEndTimeRequest(employeeId,formattedTime, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ClockActivity.this);
        queue.add(start);
    }
}