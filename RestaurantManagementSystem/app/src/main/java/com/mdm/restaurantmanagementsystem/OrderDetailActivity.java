package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.OrderDetailAdapter;
import com.mdm.restaurantmanagementsystem.model.Item;
import com.mdm.restaurantmanagementsystem.request.orderDetailRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity
{
    private ProgressDialog loading;

    static String itemID = "";
    static String Name = "";
    static String Price = "";
    static String Status = "";
    String orderId ="";
    String Quantity = "";
    String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_list);

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
                Intent homeIntent = new Intent(this,WaitersDashboardActivity.class);
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
        RequestQueue queue = Volley.newRequestQueue(OrderDetailActivity.this);
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
                Status = jsonData.getString(Config.KEY_Status);
                Quantity =jsonData.getString(Config.KEY_Quantity);

                orderItems.add(new Item(itemID,Name,Price,Status,Quantity));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        OrderDetailAdapter adapter = new OrderDetailAdapter(this, orderItems);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}