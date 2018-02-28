package com.mdm.restaurantmanagementsystem;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.OrderAdapter;
import com.mdm.restaurantmanagementsystem.model.Item;
import com.mdm.restaurantmanagementsystem.request.addOrderRequest;
import com.mdm.restaurantmanagementsystem.request.removeItemRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity
{
    static String itemID = "";
    static String Name = "";
    static String Ingredients = "";
    static String Price = "";

    String employeeId="";
    String tableId="";
    String OrderId="";
    Double totalPrice=0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);

        TextView tvServerName = (TextView) findViewById(R.id.tvServerName);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = preferences.getString("employeeId", "");
        tvServerName.setText(employeeId);

        final Spinner spinner = (Spinner) findViewById(R.id.tablesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.tables_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);

        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
        final ArrayList<Item> list = dw.getOrderLists();

        // This list order layout contains TextViews, which the adapter will set to
        // display the order item.
        OrderAdapter adapter = new OrderAdapter(this, list);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a ListView called list in the order_list.xml file.
        final ListView listView = (ListView) findViewById(R.id.list);

        // Make the ListView use the {@link ArrayAdapter} we created above, so that the
        // ListView will display list items for each menu item.
        listView.setAdapter(adapter);

        OrderId = getIntent().getStringExtra("OrderId");
        totalPrice = getIntent().getDoubleExtra("totalPrice",totalPrice);

        final DecimalFormat df = new DecimalFormat("##.##");
        final TextView tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText(df.format(totalPrice));

        // Set a click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Get the {@link Item} object at the given position the user clicked on
                Item item = list.get(position);
                list.remove(position);
                totalPrice -= Double.parseDouble(item.getPrice());
                tvTotalPrice.setText(df.format(totalPrice));
                Log.v("OrderActivity", "Item selected to remove: " + item);
                Log.v("OrderActivity","The new total is " + totalPrice);
                removeOrderItem(item);

                if(list.isEmpty())
                {
                    tvTotalPrice.setText(null);
                }
            }
        });

        Button btSubmit = (Button) findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(OrderActivity.this,"Order submitted",Toast.LENGTH_SHORT).show();

                tableId = spinner.getSelectedItem().toString();
                totalPrice = Double.parseDouble(tvTotalPrice.getText().toString());
                Log.v("OrderActivity: ", "The order id is " + OrderId + " and the total is " + totalPrice + " and the table id is " + tableId);

                insertOrdersList(totalPrice,tableId,employeeId);
                openMenu();
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

    public void removeOrderItem(final Item item) //Insert item into orderList array
    {
        itemID = item.getItemId();
        Name = item.getName();
        Ingredients = item.getIngredients();
        Price = item.getPrice();

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
                        Toast.makeText(OrderActivity.this,"Item removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(OrderActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        removeItemRequest removeItem = new removeItemRequest(itemID,Name,Price, responseListener);
        RequestQueue queue = Volley.newRequestQueue(OrderActivity.this);
        queue.add(removeItem);
        //All log messages are printed in the Android Monitor
        Log.v("OrderActivity: " , "Item removed from server: " + item.getName());
    }

    public void insertOrdersList(Double totalPrice, String tableId, String employeeId)
    {
        String stringTotalPrice = totalPrice.toString();
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
                        Toast.makeText(OrderActivity.this,"The Order: " + OrderId + " was inserted.",Toast.LENGTH_SHORT);
                    }
                    else
                    {
                        Toast.makeText(OrderActivity.this,"The Order: " + OrderId +" failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        //	orderId	employeeId	tableNo	datePlaced	totalPrice	status
        addOrderRequest addOrder= new addOrderRequest(OrderId, tableId, stringTotalPrice, employeeId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(OrderActivity.this);
        queue.add(addOrder);
    }

    /**
     * Method is called when user selects Menu TextView which calls an intent to send
     * the user back to the Menu Activity.
     */
    public void openMenu()
    {
        Intent menuIntent = new Intent(this,WaitersMenuActivity.class);
        startActivity(menuIntent);
    }
}