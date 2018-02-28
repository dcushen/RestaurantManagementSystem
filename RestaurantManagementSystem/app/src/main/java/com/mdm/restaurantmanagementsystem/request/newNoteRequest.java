package com.mdm.restaurantmanagementsystem.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.mdm.restaurantmanagementsystem.Config.NewNote_URL;

public class newNoteRequest extends StringRequest
{
    private Map<String, String> params;

    public newNoteRequest(String title, String content, String noteId, String employeeId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, NewNote_URL, listener, null);
        params = new HashMap<>();
        params.put("title", title);
        params.put("content", content);
        params.put("noteId", noteId);
        params.put("employeeId", employeeId);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
