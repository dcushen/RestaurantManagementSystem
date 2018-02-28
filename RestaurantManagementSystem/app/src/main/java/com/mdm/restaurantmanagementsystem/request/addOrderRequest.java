package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.OrderInsert_URL;

public class addOrderRequest extends StringRequest
{
    private Map<String, String> params;

    public addOrderRequest(String OrderId, String tableId, String stringTotalPrice, String employeeId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, OrderInsert_URL, listener, null);
        params = new HashMap<>();
        params.put("orderId", OrderId);
        params.put("tableId", tableId);
        params.put("totalPrice", stringTotalPrice);
        params.put("employeeId", employeeId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}