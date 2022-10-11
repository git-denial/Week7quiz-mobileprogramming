package com.sgu.myapplication;

import androidx.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


public class DetailActivity extends AppCompatActivity {

    Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        goBackBtn = findViewById(R.id.btnGoBack);

        String username = getIntent().getExtras().getString("username");

        Toast.makeText(this, "Welcome, " + username, Toast.LENGTH_LONG).show();

        goBackBtn.setOnClickListener(view ->{
            finish();
        });
    }
}
