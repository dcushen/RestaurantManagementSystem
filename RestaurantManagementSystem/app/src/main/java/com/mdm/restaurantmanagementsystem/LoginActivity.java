package com.mdm.restaurantmanagementsystem;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.LoginRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity
{
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText etEmployeeId = (EditText) findViewById(R.id.etEmployeeId);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);

        bLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String employeeId = etEmployeeId.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                level = jsonResponse.getInt(Config.KEY_Level);
                                Log.v("Login Activity", "User level: " + level);

                                if(level == 4) //Kitchen staff
                                {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("employeeId", employeeId);
                                    editor.putInt("level", level);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, KitchenDashboardActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }
                                else if(level == 0) //Management
                                {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("employeeId", employeeId);
                                    editor.putInt("level", level);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, ManagementDashboardActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }
                                else if(level == 1) //Host/Hostess
                                {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("employeeId", employeeId);
                                    editor.putInt("level", level);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, HostDashboardActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                }
                                else //Wait staff
                                {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("employeeId", employeeId);
                                    editor.putInt("level", level);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, WaitersDashboardActivity.class);
                                    LoginActivity.this.startActivity(intent);

                                    // Construct our Intent specifying the Service
                                    Intent serviceIntent = new Intent(LoginActivity.this, WaitersOrdersService.class);
                                    // Start the service
                                    startService(serviceIntent);
                                }
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(employeeId, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}