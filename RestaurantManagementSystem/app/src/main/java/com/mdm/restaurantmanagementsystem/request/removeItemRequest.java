package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.OrderItemRemove_URL;

public class removeItemRequest extends StringRequest
{
    private Map<String, String> params;

    public removeItemRequest(String itemID, String Name, String Price, Response.Listener<String> listener)
    {
        super(Method.POST, OrderItemRemove_URL, listener, null);
        params = new HashMap<>();
        params.put("itemID", itemID);
        params.put("Name", Name);
        params.put("Price", Price);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}