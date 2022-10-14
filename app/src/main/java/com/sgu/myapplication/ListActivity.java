package com.sgu.myapplication;

import android.app.Application;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.sgu.myapplication.models.DataModel;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity{

    ArrayList<DataModel> dataModels = new ArrayList<>();
    private static final String TAG = "Daquiz";
    private String url = "https://reqres.in/api/users";


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        VolleyCustom vreq = VolleyCustom.getInstance(ListActivity.this, this);

        vreq.getJSONReq(url, dataModels);


        //prepareListView();



    }



    public void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(dataModels);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }

}

class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

    ArrayList<DataModel> dataModels;

    public DataModelAdapter(ArrayList<DataModel> dataModels) {
        this.dataModels = dataModels;
        System.out.print("0329485092483958230-9458-2385");
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

        ImageView zeimage = model.getImg();

        zeimage.setOnClickListener( view -> {

System.out.println("RAAAAAAAAAAAAI");
//                                Intent intent = new Intent(ListActivity.this, MainActivity.class);
//                                startActivity(intent);

        });


        if(zeimage.getDrawable() != null) {

            holder.avatar.setImageBitmap(((BitmapDrawable)model.getImg().getDrawable()).getBitmap());
        }

        System.out.println("HOOOOOOOOOOO");
        System.out.println(model.getImg().toString());

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