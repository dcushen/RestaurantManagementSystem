package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.OrdersAdapter;
import com.mdm.restaurantmanagementsystem.model.Order;
import com.mdm.restaurantmanagementsystem.request.ordersRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdersListActivity extends AppCompatActivity
{
    private ProgressDialog loading;
    String orderId = "";
    String employeeId = "";
    String employeeID = "";
    String tableId = "";
    String datePlaced = "";
    String totalPrice = "";
    String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeID = preferences.getString("employeeId", "");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);

        getData(employeeID);
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
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent homeIntent = new Intent(this, WaitersDashboardActivity.class);
                startActivity(homeIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData(String employeeID) //Request information from server
    {
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    loading.dismiss();
                    showJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ordersRequest retriveOrders = new ordersRequest(employeeID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(OrdersListActivity.this);
        queue.add(retriveOrders);
    }

    private void showJSON(String response) //Display menu from JSON response
    {
        final ArrayList<Order> ordersList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++) {
                JSONObject jsonData = result.getJSONObject(x);

                orderId = jsonData.getString(Config.KEY_OrderId);
                employeeId = jsonData.getString(Config.KEY_EmployeeId);
                tableId = jsonData.getString(Config.KEY_TableId);
                datePlaced = jsonData.getString(Config.KEY_DatePlaced);
                totalPrice = jsonData.getString(Config.KEY_TotalPrice);
                status = jsonData.getString(Config.KEY_Status);

                ordersList.add(new Order(orderId, employeeId, tableId, datePlaced, totalPrice, status));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OrdersAdapter adapter = new OrdersAdapter(this, ordersList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set a click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the {@link Item} object at the given position the user clicked on
                Order order = ordersList.get(position);
                orderId = order.getOrderId();
                totalPrice = order.getTotalPrice();
                Log.v("OrdersListActivity", "Order selected: " + orderId);

                Intent intent = new Intent(OrdersListActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", orderId);
                intent.putExtra("totalPrice", totalPrice);
                OrdersListActivity.this.startActivity(intent);
            }
        });
    }
}