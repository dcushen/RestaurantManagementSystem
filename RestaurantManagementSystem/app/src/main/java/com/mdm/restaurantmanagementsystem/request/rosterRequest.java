package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.ManagementRoster_URL;

public class rosterRequest extends StringRequest
{
    private Map<String, String> params;

    public rosterRequest(String shiftId, Response.Listener<String> listener)
    {
        super(Method.POST, ManagementRoster_URL, listener, null);
        params = new HashMap<>();
        params.put("shiftId", shiftId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}