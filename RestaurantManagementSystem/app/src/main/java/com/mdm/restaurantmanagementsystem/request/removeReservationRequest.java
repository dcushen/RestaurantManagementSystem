package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.removeReservation_URL;

public class removeReservationRequest extends StringRequest
{
    private Map<String, String> params;

    public removeReservationRequest(String bookingId, Response.Listener<String> listener)
    {
        super(Method.POST, removeReservation_URL, listener, null);
        params = new HashMap<>();
        params.put("bookingId", bookingId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}