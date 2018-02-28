package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.ItemAdapter;
import com.mdm.restaurantmanagementsystem.model.Item;
import com.mdm.restaurantmanagementsystem.request.addItemRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.util.UUID.randomUUID;

public class WaitersMenuActivity extends AppCompatActivity
{
    private ProgressDialog loading;
    final ArrayList<Item> orderList = new ArrayList<Item>();

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
    String OrderId = "";
    Double totalPrice=0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiters_menu);

        getData();

        Button tvOrder = (Button) findViewById(R.id.tvOrder);

        tvOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent orderIntent = new Intent(WaitersMenuActivity.this, OrderActivity.class);
                orderIntent.putExtra("data", new DataWrapper(orderList));
                orderIntent.putExtra("OrderId", OrderId);
                orderIntent.putExtra("totalPrice",totalPrice);
                WaitersMenuActivity.this.startActivity(orderIntent);
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
                Intent homeIntent = new Intent(this,WaitersDashboardActivity.class);
                startActivity(homeIntent);
                return true;
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
                        Toast.makeText(WaitersMenuActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        OrderId = randomUUID().toString().substring(0, 8);
        final ArrayList<Item> itemsList = new ArrayList<Item>();

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

        ItemAdapter adapter = new ItemAdapter(this, itemsList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set a click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Get the {@link Item} object at the given position the user clicked on
                Item item = itemsList.get(position);
                Log.v("Menu Activity", "Item selected: " + item);

                //Calculate price
                //Change to long
                totalPrice += Double.parseDouble(item.getPrice());
                Log.v("WaitersMenuActivity","Total = " + totalPrice);
                insertOrderItem(item,OrderId);
            }
        });
    }

    public void insertOrderItem(Item item,String OrderId) //Insert item into orderList array
    {
        Toast.makeText(WaitersMenuActivity.this,"order id: " + OrderId,Toast.LENGTH_SHORT);

        itemID = item.getItemId();
        Name = item.getName();
        Price = item.getPrice();

        orderList.add(new Item(itemID,Name,Price));

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
                        Toast.makeText(WaitersMenuActivity.this,"Item added to order",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(WaitersMenuActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        addItemRequest addItem = new addItemRequest(itemID,Name,Price,OrderId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(WaitersMenuActivity.this);
        queue.add(addItem);
    }
}