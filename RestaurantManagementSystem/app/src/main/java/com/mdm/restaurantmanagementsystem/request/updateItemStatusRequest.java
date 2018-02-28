package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.UpdateItemStatusRequest_URL;

public class updateItemStatusRequest extends StringRequest
{
    private Map<String, String> params;

    public updateItemStatusRequest(String itemID,  String orderId, Response.Listener<String> listener)
    {
        super(Method.POST, UpdateItemStatusRequest_URL, listener, null);
        params = new HashMap<>();
        params.put("itemID", itemID);
        params.put("orderId", orderId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}