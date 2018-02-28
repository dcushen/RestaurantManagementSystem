package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.ManagementItemAdapter;
import com.mdm.restaurantmanagementsystem.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagementMenuActivity extends AppCompatActivity
{
    private ProgressDialog loading;

    String itemID = "";
    String Category = "";
    String Name = "";
    String Ingredients = "";
    String Allergy = "";
    String Vegan = "";
    String Vegetarian = "";
    String Cal = "";
    String Spicy = "";
    String Servings = "";
    String Price = "";
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_menu);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        level = preferences.getInt("level",9); //9 is a default value that will be replaced by the real level

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
                if(level==0) //Management
                {
                    Intent homeIntent = new Intent(ManagementMenuActivity.this,ManagementDashboardActivity.class);
                    startActivity(homeIntent);
                }
                else if(level==1) //Host
                {
                    Intent homeIntent = new Intent(ManagementMenuActivity.this,HostDashboardActivity.class);
                    startActivity(homeIntent);
                }
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData() //Request information from server
    {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        StringRequest stringRequest = new StringRequest(Config.Menu_URL, new Response.Listener<String>()
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
                        Toast.makeText(ManagementMenuActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        final ArrayList<Item> itemsList = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                itemID = jsonData.getString(Config.KEY_ItemId);
                Category = jsonData.getString(Config.KEY_Category);
                Name = jsonData.getString(Config.KEY_Name);
                Ingredients = jsonData.getString(Config.KEY_Ingredients);
                Allergy = jsonData.getString(Config.KEY_Allergy);
                Vegan = jsonData.getString(Config.KEY_Vegan);
                Vegetarian = jsonData.getString(Config.KEY_Vegetarian);
                Cal = jsonData.getString(Config.KEY_Cal);
                Spicy = jsonData.getString(Config.KEY_Spicy);
                Servings = jsonData.getString(Config.KEY_Servings);
                Price = jsonData.getString(Config.KEY_Price);

                itemsList.add(new Item(itemID,Name,Price));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ManagementItemAdapter adapter = new ManagementItemAdapter(this, itemsList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}