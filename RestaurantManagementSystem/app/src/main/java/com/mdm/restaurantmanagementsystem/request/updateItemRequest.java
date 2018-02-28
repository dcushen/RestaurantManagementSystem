package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.UpdateItemRequest_URL;

public class updateItemRequest extends StringRequest
{
    private Map<String, String> params;

    public updateItemRequest(String Category, String itemID, String Name, String Ingredients, String Allergy, String Vegan, String Vegetarian, String Servings, String Cal, String Spicy, String Price, Response.Listener<String> listener)
    {
        super(Method.POST, UpdateItemRequest_URL, listener, null);
        params = new HashMap<>();
        params.put("Category", Category);
        params.put("itemID", itemID);
        params.put("Name", Name);
        params.put("Ingredients", Ingredients);
        params.put("Allergy", Allergy);
        params.put("Vegan", Vegan);
        params.put("Vegetarian", Vegetarian);
        params.put("Servings", Servings);
        params.put("Cal", Cal);
        params.put("Spicy", Spicy);
        params.put("Price", Price);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}