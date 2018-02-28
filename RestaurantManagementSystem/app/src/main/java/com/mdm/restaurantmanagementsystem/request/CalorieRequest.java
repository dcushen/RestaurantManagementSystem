package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.CalRequest_URL;

/**
 * Created by Mehreen Malik (B00067565) on 14/02/2017.
 * Institute of Technology Blanchardstown
 * mehreenmalik95@gmail.com
 */

public class CalorieRequest extends StringRequest
{
    private Map<String, String> params;

    //Calorie Request
    public CalorieRequest(String IngredientName, Response.Listener<String> listener)
    {
        super(Request.Method.POST, CalRequest_URL, listener, null);
        params = new HashMap<>();
        params.put("IngredientName", IngredientName);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}