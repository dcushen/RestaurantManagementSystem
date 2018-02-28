package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.Login_URL;

public class LoginRequest extends StringRequest
{
    private Map<String, String> params;

    public LoginRequest(String employeeId, String password, Response.Listener<String> listener)
    {
        super(Method.POST, Login_URL, listener, null);
        params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}