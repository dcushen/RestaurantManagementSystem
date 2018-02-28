package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class ReservationsActivity extends AppCompatActivity {

    Button createBooking, deleteBooking,viewBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);


        createBooking = (Button)findViewById(R.id.button);
        deleteBooking = (Button)findViewById(R.id.button2);
        viewBooking = (Button)findViewById(R.id.button3);
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
                Intent homeIntent = new Intent(this, HostDashboardActivity.class);
                startActivity(homeIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newBooking(View view)
    {
        Intent newI = new Intent(ReservationsActivity.this,createBooking.class);
        startActivity(newI);
    }
    public void viewBook (View view) {
        Intent newI = new Intent(ReservationsActivity.this, viewBookings.class);
        startActivity(newI);
    }

    public void deleteBook (View view) {
        Intent newI = new Intent(ReservationsActivity.this, deleteBooking.class);
        startActivity(newI);
    }
}