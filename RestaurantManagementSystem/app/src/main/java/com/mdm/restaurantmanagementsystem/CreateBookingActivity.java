package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mdm.restaurantmanagementsystem.request.BookingActivityRequest;

import static java.util.UUID.randomUUID;

public class CreateBookingActivity extends AppCompatActivity
{
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);
    }

    public void createBook(View view) {

        String bookID = randomUUID().toString().substring(0, 8);
        EditText nameText = (EditText) findViewById(R.id.nameField);
        EditText phoneText = (EditText) findViewById(R.id.phoneField);
        EditText timeText = (EditText) findViewById(R.id.timeField);
        EditText dateText = (EditText) findViewById(R.id.dateField);
        EditText tableText = (EditText) findViewById(R.id.tableNumber);
        String fullname = nameText.getText().toString();
        String date = dateText.getText().toString();
        String time = timeText.getText().toString();
        String table = tableText.getText().toString();
        String phone = phoneText.getText().toString();
        Toast.makeText(this, "Entering booking..", Toast.LENGTH_SHORT).show();
        new BookingActivityRequest(this).execute(fullname, date, time, table, phone, bookID);

    }
}