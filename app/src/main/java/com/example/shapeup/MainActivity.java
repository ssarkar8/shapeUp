package com.example.shapeup;

import android.os.Bundle;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        pic = findViewById(R.id.image);
        //from documentation


        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "https://dog.ceo/api/breeds/image/random";


        // Request a string response from the provided URL.
        //JsonParser().parse(resp).getAsJsonObject()

        TextView textBox = findViewById(R.id.thing);
        textBox.setText("");
        Button messageButton = findViewById(R.id.message);
        messageButton.setOnClickListener(unused -> {

            textBox.setText("The button was pressed!");
            startAPI(pic);
            // AsyncTaskRunner runner = new AsyncTaskRunner();
            //  runner.doInBackground();

        });
    }

    void startAPI(ImageView view) {

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


                                //Picasso.get().load(newUrl).tag("current").into(view);
                                Picasso.get().load(newUrl).resize(600, 500).centerCrop().into(view);
                                //textBox.setText(",erp");
                                Log.d("My App", "success!!!!!!");


                                //Picasso.with(context).load(newUrl).into(pic);
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


    }
}
/*
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        //private String url;
        //private String resp;
        //ProgressDialog progressDialog;

        //@Override
        protected void doInBackground() {
            String url = "https://dog.ceo/api/breeds/image/random";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {

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

        }



        //@Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            //progressDialog.dismiss();
            //finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            //progressDialog = ProgressDialog.show(MainActivity.this,
                    //"ProgressDialog",
                    //"Wait for "+time.getText().toString()+ " seconds");
        }


        //@Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

 */



