package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.RemoveNote_URL;

public class removeNoteRequest extends StringRequest
{
    private Map<String, String> params;

    public removeNoteRequest(String noteId, Response.Listener<String> listener)
    {
        super(Method.POST, RemoveNote_URL, listener, null);
        params = new HashMap<>();
        params.put("noteId", noteId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}