package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

public class NewRosterActivity extends AppCompatActivity
{
    CalendarView calendarView;
    String shiftId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_roster);

        calendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView

        //sets the listener to be notified upon selected date change.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day)
            {
                shiftId = day + "/" + month + "/" + year;
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
                Intent homeIntent = new Intent(this,ManagementDashboardActivity.class);
                startActivity(homeIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newEntry(View view)
    {
        Intent intent = new Intent(NewRosterActivity.this, NewRosterEntryActivity.class);
        intent.putExtra("shiftId", shiftId);
        NewRosterActivity.this.startActivity(intent);
    }
}