package com.example.shapeup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.*;
import com.google.gson.JsonParser;
import com.google.gson.*;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);


        //from documentation
        ImageView pic = findViewById(R.id.image);
        // ...

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://dog.ceo/api/breeds/image/random";


        // Request a string response from the provided URL.


//JsonParser().parse(resp).getAsJsonObject()

        TextView textBox = findViewById(R.id.thing);
        textBox.setText("");
        Button messageButton = findViewById(R.id.message);

        //https://dog.ceo/api/breeds/image/random
        messageButton.setOnClickListener(unused -> {

            textBox.setText("The button was pressed!");

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("My App", "success!!!!!!");

                            String resp = response;

                            try {
                                JSONObject obj = new JSONObject(resp);
                                String newUrl = obj.getString("message");

                                URL bla = new URL(newUrl);
                                Bitmap image = BitmapFactory.decodeStream(bla.openConnection().getInputStream());
                                pic.setImageBitmap(image);
                                //textBox.setText(",erp");
                                Log.d("My App", "success!!!!!!");

                                //Picasso.with(context).load(newUrl).into(pic);
                            } catch (JSONException e) {
                                Log.e("My App", "json exception");
                            } catch (IOException e) {
                                Log.e("My App", "IO exception");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("My App", "Sad" + error.toString());
                    return;
                }
            });


// Add the request to the RequestQueue.
            queue.add(stringRequest);


        });
    }


}
