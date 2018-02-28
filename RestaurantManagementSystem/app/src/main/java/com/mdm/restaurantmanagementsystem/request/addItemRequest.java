package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.OrderItemInsert_URL;

public class addItemRequest extends StringRequest
{
    private Map<String, String> params;

    public addItemRequest(String itemID, String Name, String Price, String OrderId, Response.Listener<String> listener)
    {
        super(Method.POST, OrderItemInsert_URL, listener, null);
        params = new HashMap<>();
        params.put("itemID", itemID);
        params.put("Name", Name);
        params.put("Price", Price);
        params.put("OrderId", OrderId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}