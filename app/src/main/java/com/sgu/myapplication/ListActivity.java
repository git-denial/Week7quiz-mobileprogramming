package com.sgu.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.sgu.myapplication.models.DataModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ImageView avatar;

    private static final String TAG = "Daquiz";
    private Button btnRequest;

    private RequestQueue mRequestQueue;
    private String url = "https://reqres.in/api/users";

    private JSONArray rawjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getJSONReq(url);




        prepareListView();
    }

    private void getStringReq(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        System.out.println(response.substring(0,100));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getJSONReq(String url){
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            response = response.getJSONObject("args");
                            rawjson = new JSONArray(response.getString("data"));
                            System.out.println(rawjson);

                            dataModels = new ArrayList<DataModel>();

                            for (int i=0; i < rawjson.length(); i++) {
                                try {

                                    JSONObject o = rawjson.getJSONObject(i);
                                    dataModels.add(new DataModel(o.getString("first_name"), o.getString("last_name"), o.getString("avatar")));
                                }
                                catch (JSONException e){
                                    System.out.println(e.toString());
                                }
                            }

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

        Volley.newRequestQueue(this).add(jsonRequest);
    }

    public void getImgReq(String url, ImageView mImageView){
        ImageRequest imgRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mImageView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mImageView.setBackgroundColor(Color.parseColor("#ff0000"));
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(imgRequest);
    }

    private void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(dataModels);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }

}

class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

    ArrayList<DataModel> dataModels;
    private ImageLoader mImageLoader;

    public DataModelAdapter(ArrayList<DataModel> dataModels) {
        this.dataModels = dataModels;

    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_card_view, parent, false);

        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        DataModel model = dataModels.get(position);

        holder.titleText.setText(model.getFirstName());
        holder.descriptionText.setText(model.getLastName());
        //holder.avatar.setImageBitmap(getImg);

    }

    @Override
    public int getItemCount() {
        return dataModels == null ? 0 : dataModels.size();
    }
}

class DataModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    TextView titleText;
    TextView descriptionText;
    ImageView avatar;

    public DataModelViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        titleText = itemView.findViewById(R.id.title_text);
        descriptionText = itemView.findViewById(R.id.description_text);
        avatar = itemView.findViewById(R.id.img);
    }
}