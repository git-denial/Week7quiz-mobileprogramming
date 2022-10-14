package com.sgu.myapplication;

import androidx.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.sgu.myapplication.models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailActivity extends AppCompatActivity {

    Button goBackBtn;
    final String url = "https://reqres.in/api/user/";
    private RequestQueue reque;
    String[] bio = new String[]{
            "I am a good person",
            "I am a bad person",
            "I am a crazy person",
            "I am a lazy person",
            "Nothing to see here",
            "Nothing to see here",
            "Nothing to see here",
            "Nothing to see here",
            "Nothing to see here",
            "Nothing to see here",
            "Nothing to see here",
            "Nothing to see here"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        reque = Volley.newRequestQueue(this);

        String user_id = getIntent().getExtras().getString("user_id");
        String imgurl = getIntent().getExtras().getString("imgurl");


         JsonObjectRequest jsr = new JsonObjectRequest
                    (Request.Method.GET, url+user_id, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            try {
                                System.out.println(Util.getJSONkeys(response));
                                //JSONObject o = gson.fromJson(response,DataModel.class)
                                JSONObject majson = response.getJSONObject("data");
                                int yearBirth = majson.getInt("year");
                                String alias = majson.getString("name");
                                String color = majson.getString("color");
                                String pantone = majson.getString("pantone_value");

                                TextView name = findViewById(R.id.tvName);
                                TextView views = findViewById(R.id.tv1);
                                TextView fans = findViewById(R.id.tv2);
                                TextView description = findViewById(R.id.tvDescription);
                                ImageView dp = findViewById(R.id.ivProfile);


                                name.setText(alias);
                                views.setText(pantone.substring(pantone.length()-3,pantone.length()-1));
                                fans.setText(pantone.substring(0,1));
                                description.setText(bio[Integer.parseInt(user_id)]);

                                Glide.with(getApplicationContext())
                                        .load(imgurl)
                                        .into(dp);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

            reque.add(jsr);




        Toast.makeText(this, "Welcome, " + user_id, Toast.LENGTH_LONG).show();

    }
}
