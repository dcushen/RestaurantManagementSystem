package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.PersonalRoster_URL;

public class shiftRequest extends StringRequest
{
    private Map<String, String> params;

    public shiftRequest(String shiftId, String employeeId, Response.Listener<String> listener)
    {
        super(Method.POST, PersonalRoster_URL, listener, null);
        params = new HashMap<>();
        params.put("shiftId", shiftId);
        params.put("employeeId", employeeId);

    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}