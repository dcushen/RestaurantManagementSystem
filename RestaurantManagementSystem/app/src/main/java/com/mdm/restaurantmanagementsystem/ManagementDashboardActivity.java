package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ManagementDashboardActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_dashboard);

        Button signOut = (Button) findViewById(R.id.btSignOut);
        signOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getSharedPreferences("PREFERENCE", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent logInIntent = new Intent(ManagementDashboardActivity.this,LoginActivity.class);
                startActivity(logInIntent);
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

    public void openMenu(View view)
    {
        Intent menuIntent = new Intent(this,ManagementMenuActivity.class);
        startActivity(menuIntent);
    }

    public void openClockIn(View view)
    {
        Intent clockIntent = new Intent(this,ClockActivity.class);
        startActivity(clockIntent);
    }

    public void openNotes(View view)
    {
        Intent notesIntent = new Intent(this,NotesActivity.class);
        startActivity(notesIntent);
    }

    public void openRoster(View view)
    {
        Intent notesIntent = new Intent(this,ManagementRosterActivity.class);
        startActivity(notesIntent);
    }
}