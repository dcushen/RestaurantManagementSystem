package com.mdm.restaurantmanagementsystem;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.model.Order;
import com.mdm.restaurantmanagementsystem.request.ordersRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WaitersOrdersService extends IntentService
{
    private final Handler handler = new Handler();

    String TAG = "Waiters Orders Service";
    ArrayList<Order> ordersList;
    ArrayList<String> displayedOrders;

    String orderId = "";
    String employeeId = "";
    String employeeID = ""; //Used to retirve orders by this employeeID
    String tableId = "";
    String datePlaced = "";
    String totalPrice = "";
    String status = "";

    // Must create a default constructor
    public WaitersOrdersService()
    {
        super("Waiters Orders Service"); // Used to name the worker thread, important only for debugging.
        displayedOrders = new ArrayList<>();
    }

    @Override
    public void onCreate()
    {
        super.onCreate(); // if you override onCreate(), make sure to call super().

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeID = preferences.getString("employeeId", "");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        // This describes what will happen when service is triggered
        getData(employeeID);
    }

    private Runnable sendUpdatesToUI = new Runnable()
    {
        public void run()
        {
            getData(employeeID);
            compareLists(ordersList);
            handler.postDelayed(this, 10000); // 10 seconds
        }
    };

    public void getData(String employeeID) //Request information from server
    {
        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);
                    showJSON(response);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        ordersRequest retriveOrders = new ordersRequest(employeeID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(WaitersOrdersService.this);
        queue.add(retriveOrders);
    }

    public void showJSON(String response) //Display menu from JSON response
    {
        ordersList = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                orderId = jsonData.getString(Config.KEY_OrderId);
                employeeId = jsonData.getString(Config.KEY_EmployeeId);
                tableId = jsonData.getString(Config.KEY_TableId);
                datePlaced = jsonData.getString(Config.KEY_DatePlaced);
                totalPrice = jsonData.getString(Config.KEY_TotalPrice);
                status = jsonData.getString(Config.KEY_Status);

                ordersList.add(new Order(orderId, employeeId, tableId, datePlaced, totalPrice, status));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
    }

    public void compareLists(final ArrayList<Order> ordersList)
    {
        for (int i = 0; i < ordersList.size(); i++)
        {
            if (ordersList.get(i).getStatus().equals("Completed") && !displayedOrders.contains(ordersList.get(i).getOrderId()))
            {
                Log.v(TAG, "Order " + ordersList.get(i).getOrderId() + " has been completed.");
                displayedOrders.add(ordersList.get(i).getOrderId());

                //Testing Notifications
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                                .setDefaults(Notification.DEFAULT_VIBRATE)
                                .setSmallIcon(R.drawable.logo_black)
                                .setContentTitle("Order has been completed")
                                .setContentText("Order " + ordersList.get(i).getOrderId() + " has been completed.");

                Intent resultIntent = new Intent(this, OrdersListActivity.class);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent); //Set the Notification's Click Behavior

                // Sets an ID for the notification
                int mNotificationId = 1;
                // Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
            else
            {
                Log.v(TAG,"No change in the order: " + ordersList.get(i).getOrderId() + " status: " + ordersList.get(i).getStatus());
            }
        }
        Log.v(TAG, "--------------- End of comparison method ---------------");
    }
}