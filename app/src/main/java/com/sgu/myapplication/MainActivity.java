package com.sgu.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button forgotPassword;
    Button register;

    EditText username;
    EditText password;

    HashMap<String, String> users = new HashMap<String, String>() {{
        put("admin","a123min");
        put("user","user");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username_field);
        password = findViewById(R.id.password_field);
        login = findViewById(R.id.login_btn);
        forgotPassword = findViewById(R.id.forget_btn);
        register = findViewById(R.id.register);

        login.setOnClickListener(view ->{

            String uname = username.getText().toString();
            String pass = password.getText().toString();

            String x = users.get(uname);

            if(x == null){
                Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                return;
            }


            if (x.equals(pass)) {
                Toast.makeText(getApplicationContext(), "Login succssful",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, ListActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("username", uname);

                myIntent.putExtras(bundle);
                startActivity(myIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

            }

        });

        register.setOnClickListener(view ->{

            Intent myIntent = new Intent(this, RegisterActivity.class);
            startActivity(myIntent);
        });

    }





}
