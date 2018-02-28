package com.mdm.restaurantmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.itemDetailRequest;
import com.mdm.restaurantmanagementsystem.request.updateItemRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditMenuItemActivity extends AppCompatActivity
{
    private ProgressDialog loading;
    EditText item_id, item_name, item_ingredients, item_allergys, item_cal, item_servings, item_price;

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

    Spinner spinner;

    Button levelZero,levelOne, levelTwo, levelThree, levelFour, levelFive;

    private RadioGroup radioVeganGroup;
    private RadioButton radioVeganButton, radioVeganYes, radioVeganNo, radioVegetarianYes, radioVegetarianNo;

    private RadioGroup radioVegetarianGroup;
    private RadioButton radioVegetarianButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_item);

        item_id = (EditText) findViewById(R.id.item_id);
        Intent intent = getIntent();
        itemID = intent.getStringExtra("itemID");
        Log.v("Edit Menu Item","Item Id: " + itemID);retriveItem(itemID);
        

        spinner = (Spinner) findViewById(R.id.categorySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);

        item_name = (EditText) findViewById(R.id.item_name);
        item_ingredients = (EditText) findViewById(R.id.item_ingredients);
        item_allergys = (EditText) findViewById(R.id.item_allergys);
        item_cal = (EditText) findViewById(R.id.item_cal);
        item_servings = (EditText) findViewById(R.id.item_servings);
        item_price = (EditText) findViewById(R.id.item_price);

        levelZero = (Button) findViewById(R.id.levelZero);
        levelZero.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                levelOne.getBackground().setAlpha(0); //Sets background transparency to full ie 0
                levelTwo.getBackground().setAlpha(0);
                levelThree.getBackground().setAlpha(0);
                levelFour.getBackground().setAlpha(0);
                levelFive.getBackground().setAlpha(0);
                Spicy = "0";
                levelZero.setBackgroundColor(Color.GRAY);
            }
        });

        levelOne = (Button) findViewById(R.id.levelOne);
        levelOne.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                levelZero.getBackground().setAlpha(0);
                levelTwo.getBackground().setAlpha(0);
                levelThree.getBackground().setAlpha(0);
                levelFour.getBackground().setAlpha(0);
                levelFive.getBackground().setAlpha(0);
                Spicy = "1";
                levelOne.setBackgroundColor(Color.GRAY);
            }
        });

        levelTwo = (Button) findViewById(R.id.levelTwo);
        levelTwo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                levelZero.getBackground().setAlpha(0);
                levelOne.getBackground().setAlpha(0);
                levelThree.getBackground().setAlpha(0);
                levelFour.getBackground().setAlpha(0);
                levelFive.getBackground().setAlpha(0);
                Spicy = "2";
                levelTwo.setBackgroundColor(Color.GRAY);
            }
        });

        levelThree = (Button) findViewById(R.id.levelThree);
        levelThree.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                levelZero.getBackground().setAlpha(0);
                levelOne.getBackground().setAlpha(0);
                levelTwo.getBackground().setAlpha(0);
                levelFour.getBackground().setAlpha(0);
                levelFive.getBackground().setAlpha(0);
                Spicy = "3";
                levelThree.setBackgroundColor(Color.GRAY);
            }
        });

        levelFour = (Button) findViewById(R.id.levelFour);
        levelFour.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                levelZero.getBackground().setAlpha(0);
                levelOne.getBackground().setAlpha(0);
                levelTwo.getBackground().setAlpha(0);
                levelThree.getBackground().setAlpha(0);
                Spicy = "4";
                levelFour.setBackgroundColor(Color.GRAY);

            }
        });

        levelFive = (Button) findViewById(R.id.levelFive);
        levelFive.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                levelZero.getBackground().setAlpha(0);
                levelOne.getBackground().setAlpha(0);
                levelTwo.getBackground().setAlpha(0);
                levelThree.getBackground().setAlpha(0);
                levelFour.getBackground().setAlpha(0);
                Spicy = "5";
                levelFive.setBackgroundColor(Color.GRAY);
            }
        });

        radioVeganYes = (RadioButton) findViewById(R.id.radioVeganYes);
        radioVeganNo = (RadioButton) findViewById(R.id.radioVeganNo);

        radioVegetarianYes = (RadioButton) findViewById(R.id.radioVegetarianYes);
        radioVegetarianNo = (RadioButton) findViewById(R.id.radioVegetarianNo);

        radioVeganGroup = (RadioGroup) findViewById(R.id.radioVegan);
        radioVegetarianGroup = (RadioGroup) findViewById(R.id.radioVegetarian);
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

    public void retriveItem(String itemID)
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

        itemDetailRequest getItemDetails = new itemDetailRequest(itemID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditMenuItemActivity.this);
        queue.add(getItemDetails);
    }

    private void showJSON(String response)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                Category = jsonData.getString(Config.KEY_Category);
                itemID = jsonData.getString(Config.KEY_ItemId);
                Name = jsonData.getString(Config.KEY_Name);
                Ingredients = jsonData.getString(Config.KEY_Ingredients);
                Allergy = jsonData.getString(Config.KEY_Allergy);
                Vegan = jsonData.getString(Config.KEY_Vegan);
                Vegetarian  = jsonData.getString(Config.KEY_Vegetarian);
                Price = jsonData.getString(Config.KEY_Price);
                Cal = jsonData.getString(Config.KEY_Cal);
                Spicy = jsonData.getString(Config.KEY_Spicy);
                Servings = jsonData.getString(Config.KEY_Servings);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        switch (Category)
        {
            case "Starters":
                //Do something
                spinner.setSelection(0);
                break;
            case "Salad":
                //Do something
                spinner.setSelection(1);
                break;
            case "Burgers":
                //Do something
                spinner.setSelection(2);
                break;
            case "Pasta":
                //Do something
                spinner.setSelection(3);
                break;
            case "Dessert":
                spinner.setSelection(4);
                break;
            case "Soft Drinks":
                spinner.setSelection(5);
                break;
        }

        item_id.setText(itemID);
        item_id.setEnabled(false);

        item_name.setText(Name);
        item_ingredients.setText(Ingredients);
        item_allergys.setText(Allergy);

        switch (Vegan)
        {
            case "Yes":
                radioVeganYes.setChecked(true);
                break;
            case "No":
                radioVeganNo.setChecked(true);
                break;
        }

        switch (Vegetarian)
        {
            case "Yes":
                radioVegetarianYes.setChecked(true);
                break;
            case "No":
                radioVegetarianNo.setChecked(true);
                break;
        }

        item_cal.setText(Cal);

        switch (Spicy)
        {
            case "0":
                levelZero.setBackgroundColor(Color.GRAY);
                break;
            case "1":
                levelOne.setBackgroundColor(Color.GRAY);
                break;
            case "2":
                levelTwo.setBackgroundColor(Color.GRAY);
                break;
            case "3":
                levelThree.setBackgroundColor(Color.GRAY);
                break;
            case "4":
                levelFour.setBackgroundColor(Color.GRAY);
                break;
            case "5":
                levelFive.setBackgroundColor(Color.GRAY);
                break;
            default:
                Toast.makeText(this, "Spicy level is not one of the available options, change it.", Toast.LENGTH_LONG).show();
                break;
        }

        item_servings.setText(Servings);
        item_price.setText(Price);
    }

    public void updateMenuItem(View view)
    {
        Category = spinner.getSelectedItem().toString();
        itemID = item_id.getText().toString();
        Name = item_name.getText().toString();
        Price = item_price.getText().toString();
        Ingredients = item_ingredients.getText().toString();
        Allergy = item_allergys.getText().toString();
        Cal = item_cal.getText().toString();
        Servings = item_servings.getText().toString();

        //Vegan option
        int veganId = radioVeganGroup.getCheckedRadioButtonId();
        radioVeganButton = (RadioButton) findViewById(veganId);
        Vegan = radioVeganButton.getText().toString();

        //Vegetarian
        int vegetarianId = radioVegetarianGroup.getCheckedRadioButtonId();
        radioVegetarianButton = (RadioButton) findViewById(vegetarianId);
        Vegetarian = radioVegetarianButton.getText().toString();

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
                        Toast.makeText(EditMenuItemActivity.this,"Item has been updated",Toast.LENGTH_SHORT).show();
                        openKitchenMenu();
                    }
                    else
                    {
                        Toast.makeText(EditMenuItemActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        updateItemRequest updateItem = new updateItemRequest(Category,itemID,Name,Ingredients,Allergy,Vegan, Vegetarian, Servings, Cal, Spicy,Price, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditMenuItemActivity.this);
        queue.add(updateItem);
    }

    /**
     * Method is called when user adds a new menu item which calls an intent to send
     * the user back to the Kitchen Menu Activity.
     */
    public void openKitchenMenu()
    {
        Intent menuIntent = new Intent(this,KitchenMenuActivity.class);
        startActivity(menuIntent);
    }
}