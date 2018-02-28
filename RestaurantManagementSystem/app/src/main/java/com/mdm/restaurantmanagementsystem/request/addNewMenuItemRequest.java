package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.MenuItemInsert_URL;

public class addNewMenuItemRequest  extends StringRequest
{
    private Map<String, String> params;

    public addNewMenuItemRequest(String itemID, String Category,String Name,String Ingredients,String Allergy,String Vegan,String Vegetarian,String Cal,String Spicy,String Servings,String Price, Response.Listener<String> listener)
    {
        super(Request.Method.POST, MenuItemInsert_URL, listener, null);
        params = new HashMap<>();
        params.put("itemID", itemID);
        params.put("Category", Category);
        params.put("Name", Name);
        params.put("Ingredients", Ingredients);
        params.put("Allergy", Allergy);
        params.put("Vegan", Vegan);
        params.put("Vegetarian", Vegetarian);
        params.put("Cal", Cal);
        params.put("Spicy", Spicy);
        params.put("Servings", Servings);
        params.put("Price", Price);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}