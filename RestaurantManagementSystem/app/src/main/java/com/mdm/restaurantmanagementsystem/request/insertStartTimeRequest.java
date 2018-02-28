package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.InsertStartTime_URL;

public class insertStartTimeRequest extends StringRequest
{
    private Map<String, String> params;

    public insertStartTimeRequest(String employeeId, String formattedTime, Response.Listener<String> listener)
    {
        super(Request.Method.POST, InsertStartTime_URL, listener, null);
        params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("formattedTime", formattedTime);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
