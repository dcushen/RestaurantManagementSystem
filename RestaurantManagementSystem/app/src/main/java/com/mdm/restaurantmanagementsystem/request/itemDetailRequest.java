package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.GetItemDetails_URL;

public class itemDetailRequest extends StringRequest
{
    private Map<String, String> params;

    public itemDetailRequest(String itemID, Response.Listener<String> listener)
    {
        super(Request.Method.POST, GetItemDetails_URL, listener, null);
        params = new HashMap<>();
        params.put("itemID", itemID);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}