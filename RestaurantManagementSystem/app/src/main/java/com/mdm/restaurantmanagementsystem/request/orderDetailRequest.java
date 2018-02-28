package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.GetOrderDetails_URL;

public class orderDetailRequest extends StringRequest
{
    private Map<String, String> params;

    public orderDetailRequest(String orderId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, GetOrderDetails_URL, listener, null);
        params = new HashMap<>();
        params.put("orderId", orderId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}