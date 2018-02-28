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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.KitchenOrderDetailsAdapter;
import com.mdm.restaurantmanagementsystem.model.Item;
import com.mdm.restaurantmanagementsystem.request.orderDetailRequest;
import com.mdm.restaurantmanagementsystem.request.updateItemStatusRequest;
import com.mdm.restaurantmanagementsystem.request.updateOrderStatusRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KitchenOrderDetailsActivity extends AppCompatActivity
{
    ListView listView;
    private ProgressDialog loading;

    static String itemID = "";
    static String Name = "";
    static String Price = "";
    static String Status = "";
    String Quantity = "";

    String orderId = "";
    String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_order_details);

        DecimalFormat df = new DecimalFormat("##.##");

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        totalPrice = intent.getStringExtra("totalPrice");

        retriveOrder(orderId);

        TextView tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText(df.format(Double.parseDouble(totalPrice)));
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
                Intent homeIntent = new Intent(this,KitchenDashboardActivity.class);
                startActivity(homeIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void retriveOrder(String orderId)
    {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

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

        orderDetailRequest getOrderDetails = new orderDetailRequest(orderId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(KitchenOrderDetailsActivity.this);
        queue.add(getOrderDetails);
    }

    private void showJSON(String response)
    {
        final ArrayList<Item> orderItems = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                itemID = jsonData.getString(Config.KEY_ItemId);
                Name = jsonData.getString(Config.KEY_Name);
                Price = jsonData.getString(Config.KEY_Price);
                Status = jsonData.getString(Config.KEY_Status);
                Quantity =jsonData.getString(Config.KEY_Quantity);

                orderItems.add(new Item(itemID,Name,Price,Status,Quantity));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        KitchenOrderDetailsAdapter adapter = new KitchenOrderDetailsAdapter(this, orderItems);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set a click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Get the {@link Item} object at the given position the user clicked on
                Item item = orderItems.get(position);
                itemID = item.getItemId();
                Log.v("Order Details Activity", "Item selected: " + item);
                changeStatus(itemID,orderId);
            }
        });
    }

    public void changeStatus(String itemID, String orderId)
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
                        Toast.makeText(KitchenOrderDetailsActivity.this,"Item status has been updated",Toast.LENGTH_SHORT).show();

                        finish(); //Refreshes the activity pt.1
                        startActivity(getIntent()); //Refreshes the activity pt.2
                    }
                    else
                    {
                        Toast.makeText(KitchenOrderDetailsActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        updateItemStatusRequest updateItemStatus = new updateItemStatusRequest(itemID,orderId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(KitchenOrderDetailsActivity.this);
        queue.add(updateItemStatus);
    }

    //Executed after user selects order completed button.
    public void orderCompleted(View view)
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
                        Toast.makeText(KitchenOrderDetailsActivity.this,"Order has been completed",Toast.LENGTH_SHORT).show();

                        openKitchenOrdersActivity();
                    }
                    else
                    {
                        Toast.makeText(KitchenOrderDetailsActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        updateOrderStatusRequest updateOrderStatus = new updateOrderStatusRequest(orderId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(KitchenOrderDetailsActivity.this);
        queue.add(updateOrderStatus);
    }

    public void openKitchenOrdersActivity()
    {
        Intent ordersIntent = new Intent(this,KitchenOrdersActivity.class);
        startActivity(ordersIntent);
    }
}