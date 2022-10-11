package com.sgu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        register = findViewById(R.id.register);

        register.setOnClickListener(view ->{

            Toast.makeText(getApplicationContext(), "Registered successfully",Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);


        });

    }





}
