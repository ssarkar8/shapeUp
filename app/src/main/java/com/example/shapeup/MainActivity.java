package com.example.shapeup;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;

import android.os.Bundle;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.*;
import com.squareup.picasso.Picasso;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView pic;
    TextView textBox;
    TextView instructionBox;

    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        instructionBox = findViewById((R.id.instruction));
        instructionBox.setText("Or Shake Me!");

        pic = findViewById(R.id.image);


        final SensorEventListener mSensorListener = new SensorEventListener() {

            public void onSensorChanged(SensorEvent se) {
                float x = se.values[0];
                float y = se.values[1];
                float z = se.values[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta; // perform low-cut filter
                if (mAccel > 3) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Device has shaken.", Toast.LENGTH_LONG);
                    toast.show();
                    startAPI();
                }
            }

            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;


        textBox = findViewById(R.id.thing);
        textBox.setText("");
        Button messageButton = findViewById(R.id.message);
        //when button is clicked, load new image
        messageButton.setOnClickListener(unused -> {

            startAPI();

        });
    }
    void startAPI() {


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://dog.ceo/api/breeds/image/random";
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("My App", "success!!!!!!");
                            try {
                                String newUrl = response.get("message").toString();

                                Picasso.get().load(newUrl).resize(600, 500).centerCrop().into(pic);
                                Log.d("My App", "success!!!!!!");
                            } catch (JSONException e) {
                                Log.e("My App", "json exception");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("My App", "Sad" + error.toString());
                    return;
                }
            });
            queue.add(request);
        } catch (Exception e) {
            System.out.println("uh oh");
        }
        try {
            String url2 = "https://thesimpsonsquoteapi.glitch.me/quotes";
            JsonArrayRequest request2 = new JsonArrayRequest(Request.Method.GET, url2, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("My App", "success!!!!!!");
                            try {
                                String quote = "";
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject obj = response.getJSONObject(i);
                                    quote = obj.getString("quote");
                                }

                                //Picasso.get().load(newUrl).resize(600, 500).centerCrop().into(view);
                                textBox.setText(quote);
                                Log.d("My App", "success!!!!!!");
                            } catch (JSONException e) {
                                Log.e("My App", "json exception");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textBox.setText(error.toString());
                    Log.d("My App", "Sad" + error.toString());
                    return;
                }
            });
            queue.add(request2);
        } catch (Exception e) {
            System.out.println("uh oh");
        }
    }
}




