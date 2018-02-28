package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.MenuItemRemove_URL;

public class removeMenuItemRequest extends StringRequest
{
    private Map<String, String> params;

    public removeMenuItemRequest(String itemID,  Response.Listener<String> listener)
    {
        super(Method.POST, MenuItemRemove_URL, listener, null);
        params = new HashMap<>();
        params.put("itemID", itemID);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}