package com.mdm.restaurantmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BookingActivity extends AsyncTask<String, Void, String> {

    private Context context;

    public BookingActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        String fullname = arg0[0];
        String date = arg0[1];
        String time = arg0[2];
        String table = arg0[3];
        String phone = arg0[4];
        String bookID = arg0[5];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?name=" + URLEncoder.encode(fullname, "UTF-8");
            data += "&date=" + URLEncoder.encode(date, "UTF-8");
            data += "&time=" + URLEncoder.encode(time, "UTF-8");
            data += "&table=" + URLEncoder.encode(table, "UTF-8");
            data += "&phone=" + URLEncoder.encode(phone, "UTF-8");
            data += "&bookID=" + URLEncoder.encode(bookID, "UTF-8");

            link = "https://mehreenmalik.000webhostapp.com/saveBooking.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "Data inserted successfully. Signup successful.", Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Data could not be inserted. Signup failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Success..", Toast.LENGTH_SHORT).show();
                goToBooking();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
    public void goToBooking() {

    }

}
