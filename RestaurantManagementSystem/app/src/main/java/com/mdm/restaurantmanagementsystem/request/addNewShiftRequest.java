package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.NewShift_URL;

public class addNewShiftRequest extends StringRequest
{
    private Map<String, String> params;

    public addNewShiftRequest(String shiftId, String name, String start, String end, Response.Listener<String> listener)
    {
        super(Method.POST, NewShift_URL, listener, null);
        params = new HashMap<>();
        params.put("shiftId", shiftId);
        params.put("name", name);
        params.put("start", start);
        params.put("end", end);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}