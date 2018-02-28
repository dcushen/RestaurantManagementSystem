package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.UpdateOrderStatus_URL;

public class updateOrderStatusRequest extends StringRequest
{
    private Map<String, String> params;

    public updateOrderStatusRequest(String OrderId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, UpdateOrderStatus_URL, listener, null);
        params = new HashMap<>();
        params.put("orderId", OrderId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}