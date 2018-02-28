package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.GetNoteDetails_URL;

public class noteRequest extends StringRequest
{
    private Map<String, String> params;

    public noteRequest(String noteId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, GetNoteDetails_URL, listener, null);
        params = new HashMap<>();
        params.put("noteId", noteId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}