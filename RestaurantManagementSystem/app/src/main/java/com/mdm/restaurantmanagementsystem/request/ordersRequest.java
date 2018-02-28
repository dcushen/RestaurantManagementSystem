package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.KitchenOrders_URL;
import static com.mdm.restaurantmanagementsystem.Config.Orders_URL;

public class ordersRequest extends StringRequest
{
    private Map<String, String> params;

    //Server order request
    public ordersRequest(String employeeID, Response.Listener<String> listener)
    {
        super(Request.Method.POST, Orders_URL, listener, null);
        params = new HashMap<>();
        params.put("employeeId", employeeID);
    }

    //Kitchen orders request
    public ordersRequest(Response.Listener<String> listener)
    {
        super(Request.Method.POST, KitchenOrders_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}