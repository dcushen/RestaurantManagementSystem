package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.mdm.restaurantmanagementsystem.request.addNewMenuItemRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class NewMenuItemActivity extends AppCompatActivity
{
    //String for spicy level selected
    String levelSelected, category;

    String itemID,Category,Name,Ingredients,Allergy,Vegan,Vegetarian,Cal,Spicy,Servings,Price;

    Spinner spinner;
    EditText item_id, item_name, item_ingredients, item_allergys, item_cal, item_servings, item_price;
    private RadioGroup radioVeganGroup;

    private RadioGroup radioVegetarianGroup;

    Button levelZero,levelOne, levelTwo, levelThree, levelFour, levelFive;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_menu_item);

        item_id = (EditText) findViewById(R.id.item_id);

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
                levelSelected = "0";
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
                levelSelected = "1";
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
                levelSelected = "2";
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
                levelSelected = "3";
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
                levelSelected = "4";
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
                levelSelected = "5";
                levelFive.setBackgroundColor(Color.GRAY);
            }
        });

        radioVeganGroup = (RadioGroup) findViewById(R.id.radioVegan);
        radioVegetarianGroup = (RadioGroup) findViewById(R.id.radioVegetarian);

        item_cal = (EditText) findViewById(R.id.item_cal);
        item_servings = (EditText) findViewById(R.id.item_servings);
        item_price = (EditText) findViewById(R.id.item_price);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String Pref_Item_ID = preferences.getString("itemID",null);
        String Pref_Name = preferences.getString("Name",null);
        String Pref_Ingredients = preferences.getString("Ingredients",null);
        String Pref_Allergy = preferences.getString("Allergy",null);
        //String Pref_Vegan = preferences.getString("Vegan",null);
        //String Pref_Vegetarian = preferences.getString("Vegetarian",null);
        String Pref_Cal = preferences.getString("Cal",null);
        //String Pref_Spicy = preferences.getString("Spicy",null);
        String Pref_Servings = preferences.getString("Servings",null);
        String Pref_Price = preferences.getString("Price",null);

        if(Pref_Item_ID != null)
        {
            item_id.setText(Pref_Item_ID);
        }
        if(Pref_Name != null)
        {
            item_name.setText(Pref_Name);
        }
        if(Pref_Ingredients != null)
        {
            item_ingredients.setText(Pref_Ingredients);
        }
        if(Pref_Allergy != null)
        {
            item_allergys.setText(Pref_Allergy);
        }
        if(Pref_Cal != null)
        {
            item_cal.setText(Pref_Cal);
        }
        if(Pref_Servings != null)
        {
            item_servings.setText(Pref_Servings);
        }
        if(Pref_Price != null)
        {
            item_price.setText(Pref_Price);
        }

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String getIngredientsList = (String) bd.get("ingredientsList");
            item_ingredients.setText(getIngredientsList);

            Double getTotalCal = (Double) bd.get("totalCal");
            item_cal.setText(getTotalCal.toString());
        }
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

    public void retrieveNewItemInfo(View view)
    {
        int errorCheck = 0;

        //Retrieve Item Id
        if(item_id.getText().toString().length() == 0)
        {
            item_id.setError("Item id is required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item ID: " + item_id.getText().toString());
            itemID = item_id.getText().toString();
        }

        //Retrieve Category when ad item button is selected.
        category = spinner.getSelectedItem().toString();
        Log.v("New Item ---->", "Category: " + category);
        Category = category;

        //Retrieve Item name
        if(item_name.getText().toString().length() == 0)
        {
            item_name.setError("Item name is required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item name: " + item_name.getText().toString());
            Name = item_name.getText().toString();
        }

        //Retrieve item_ingredients
        if(item_ingredients.getText().toString().length() == 0)
        {
            item_ingredients.setError("Item ingredients are required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item ingredients: " + item_ingredients.getText().toString());
            Ingredients = item_ingredients.getText().toString();
        }

        //Retrieve item allergy's
        if(item_allergys.getText().toString().length() == 0)
        {
            item_allergys.setError("Item allergy's are required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item allergy's: " + item_allergys.getText().toString());
            Allergy = item_allergys.getText().toString();
        }

        //Vegan
        int veganId = radioVeganGroup.getCheckedRadioButtonId();
        RadioButton radioVeganButton = (RadioButton) findViewById(veganId);
        if (radioVeganGroup.getCheckedRadioButtonId() != -1)
        {
            Log.v("New Item ---->","Vegan: " + radioVeganButton.getText());
            Vegan = radioVeganButton.getText().toString();
        }
        else
        {
            Toast.makeText(NewMenuItemActivity.this,"Check one vegan option",Toast.LENGTH_LONG).show();
            errorCheck+=1;
        }

        //Vegetarian
        int vegetarianId = radioVegetarianGroup.getCheckedRadioButtonId();
        RadioButton radioVegetarianButton = (RadioButton) findViewById(vegetarianId);
        if (radioVegetarianGroup.getCheckedRadioButtonId() != -1)
        {
            Log.v("New Item ---->","Vegetarian: " + radioVegetarianButton.getText());
            Vegetarian = radioVegetarianButton.getText().toString();
        }
        else
        {
            Toast.makeText(NewMenuItemActivity.this,"Check one vegetarian option",Toast.LENGTH_LONG).show();
            errorCheck+=1;
        }

        //item_cal
        if(item_cal.getText().toString().length() == 0)
        {
            item_cal.setError("Item cal's are required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item cal's: " + item_cal.getText().toString());
            Cal = item_cal.getText().toString();
        }

        //Spicy level
        if(levelSelected !=null  &&  !levelSelected.isEmpty())
        {
            Log.v("New Item ---->","Spicy level: " + levelSelected);
            Spicy = levelSelected;
        }
        else
        {
            Toast.makeText(NewMenuItemActivity.this,"Check a spicy level.",Toast.LENGTH_LONG).show();
            errorCheck+=1;
        }

        //item_servings
        if(item_servings.getText().toString().length() == 0)
        {
            item_servings.setError("Item servings's are required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item serving's: " + item_servings.getText().toString());
            Servings = item_servings.getText().toString();
        }

        //item_price
        if(item_price.getText().toString().length() == 0)
        {
            item_price.setError("Item servings's are required!");
            errorCheck+=1;
        }
        else
        {
            Log.v("New Item ---->", "Item price: " + item_price.getText().toString());
            Price = item_price.getText().toString();
        }

        if(errorCheck==0)
        {
            insertNewMenuItem();
        }
        else
        {
            Log.v("New Item ---->", "Errors in form: " + errorCheck);
        }
    }

    public void insertNewMenuItem()
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
                        Toast.makeText(NewMenuItemActivity.this,"Item added to menu",Toast.LENGTH_SHORT).show();
                        openKitchenMenu();
                    }
                    else
                    {
                        Toast.makeText(NewMenuItemActivity.this,"Failed, try again.",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        addNewMenuItemRequest newMenuItem = new addNewMenuItemRequest(itemID,Category,Name,Ingredients,Allergy,Vegan,Vegetarian,Cal,Spicy,Servings,Price, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NewMenuItemActivity.this);
        queue.add(newMenuItem);
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

    /**
     * Method is called when user clicks ingredients button.
     */
    public void IngredientsIntent(View view)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("itemID", item_id.getText().toString());
        editor.putString("Name", item_name.getText().toString());
        editor.putString("Ingredients", item_ingredients.getText().toString());
        editor.putString("Allergy", item_allergys.getText().toString());
        editor.putString("Cal", item_cal.getText().toString());
        editor.putString("Servings", item_servings.getText().toString());
        editor.putString("Price", item_price.getText().toString());
        editor.apply();

        Intent ingredientsIntent = new Intent(NewMenuItemActivity.this,IngredientsActivity.class);
        startActivity(ingredientsIntent);
    }

    @Override
    protected void onDestroy()
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("itemID");//clean this key.
        editor.remove("Name");//clean this key.
        editor.remove("Ingredients");//clean this key.
        editor.remove("Allergy");//clean this key.
        editor.remove("Cal");//clean this key.
        editor.remove("Servings");//clean this key.
        editor.remove("Price");//clean this key.
        editor.apply();//clean all the preference
        super.onDestroy();
    }
}