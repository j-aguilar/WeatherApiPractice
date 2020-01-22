package com.example.yahooweatherapipractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String TAG = "Main Activity";
        WeatherApi wApi = new WeatherApi();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(wApi.getUrl(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    org.json.JSONArray weatherArray = response.getJSONArray("weather");
                    org.json.JSONObject weather = weatherArray.getJSONObject(0);
                    org.json.JSONObject main = response.getJSONObject("main");
                    String temperature = main.getString("temp");
                    String condition = weather.getString("description");
                    String location = response.getString("name");
                    String iconString = weather.getString("icon");
                    Log.i("Volley Log", "onResponse: " + temperature);
                    Log.i("Volley Log", "onResponse: " + condition);
                    Log.i("Volley Log", "onResponse: " + location);
                    Log.i("Volley Log", "onResponse: " + iconString);
                    TextView t = findViewById(R.id.temperature);
                    t.setText(temperature);
                    TextView c = findViewById(R.id.condition);
                    c.setText(condition);
                    TextView l = findViewById(R.id.location);
                    l.setText(location);
                    int resourceId = getResources().getIdentifier("drawable/s_" + iconString, null, getPackageName());
                    Drawable icon = getResources().getDrawable(resourceId);
                    ImageView i = findViewById(R.id.currentIcon);
                    i.setImageDrawable(icon);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        queue.add(jsonObjectRequest);
    }
}
