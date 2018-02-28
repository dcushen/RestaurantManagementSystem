package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.UpdateNote_URL;

public class updateNoteRequest extends StringRequest
{
    private Map<String, String> params;

    public updateNoteRequest(String title, String content, String noteId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, UpdateNote_URL, listener, null);
        params = new HashMap<>();
        params.put("title", title);
        params.put("content", content);
        params.put("noteId", noteId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
