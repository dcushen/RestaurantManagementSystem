package com.mdm.restaurantmanagementsystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.adapter.KitchenItemAdapter;
import com.mdm.restaurantmanagementsystem.model.Item;
import com.mdm.restaurantmanagementsystem.request.removeMenuItemRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KitchenMenuActivity extends AppCompatActivity
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_menu);

        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);

        // It is also possible add items here. Use a generated id from
        // resources (ids.xml) to ensure that all menu ids are distinct.
        MenuItem NewMenuItem = menu.add(0, R.id.menu_new_item, 0, R.string.menu_new_item);
        NewMenuItem.setIcon(R.drawable.ic_add_circle_black);

        // Need to use MenuItemCompat methods to call any action item related methods
        MenuItemCompat.setShowAsAction(NewMenuItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_home:
                Intent homeIntent = new Intent(this, KitchenDashboardActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.menu_new_item:
                Intent newItemIntent = new Intent(this, NewMenuItemActivity.class);
                startActivity(newItemIntent);
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
                        Toast.makeText(KitchenMenuActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
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


        KitchenItemAdapter adapter = new KitchenItemAdapter(this, itemsList);
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
                itemID = item.getItemId();
                Log.v("Kitchen Menu Activity", "Item selected: " + item);
                openEditMenuItemActivity(itemID);
            }
        });

        //Ask user if they want to delete the item after a long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Get the {@link Item} object at the given position the user clicked on
                Item item = itemsList.get(position);
                Name = item.getName();
                itemID = item.getItemId();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(KitchenMenuActivity.this);
                alertDialogBuilder.setMessage("Are you sure, you want to delete: " + Name);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        deleteMenuItem(itemID);
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(KitchenMenuActivity.this,"Item wasn't deleted.",Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    public void openEditMenuItemActivity(String itemID)
    {
        Intent editItem = new Intent(this, EditMenuItemActivity.class);
        editItem.putExtra("itemID", itemID);
        startActivity(editItem);
    }

    public void deleteMenuItem(String itemID)
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
                        Toast.makeText(KitchenMenuActivity.this,"Menu item removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(KitchenMenuActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        removeMenuItemRequest removeMenuItem = new removeMenuItemRequest(itemID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(KitchenMenuActivity.this);
        queue.add(removeMenuItem);

        //Refresh activity
        finish();
        startActivity(getIntent());
    }
}