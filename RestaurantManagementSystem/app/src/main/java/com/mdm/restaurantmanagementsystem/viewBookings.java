package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.ReservationAdapter;
import com.mdm.restaurantmanagementsystem.model.ResItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class viewBookings extends AppCompatActivity {
    private ProgressDialog loading;
    static String name = "";
    static String table = "";
    static String time = "";
    static String phone = "";
    static String bookID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);


        getData();
    }

    private void getData() //Request information from server
    {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        StringRequest stringRequest = new StringRequest(Config.Reservations_URL, new Response.Listener<String>()
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
                        Toast.makeText(viewBookings.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response) //Display menu from JSON response
    {
        final ArrayList<ResItem> reservations = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                name = jsonData.getString(Config.KEY_NAME);
                table = jsonData.getString(Config.KEY_TABLE);
                time = jsonData.getString(Config.KEY_TIME);
                phone = jsonData.getString(Config.KEY_PHONE);
                bookID = jsonData.getString(Config.KEY_BOOKID);

                reservations.add(new ResItem(name, table, time, phone, bookID));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ReservationAdapter adapter = new ReservationAdapter(this, reservations);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

}