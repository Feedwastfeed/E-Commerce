package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signin = (Button) findViewById(R.id.login);
        Button signup = (Button) findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(MainActivity.this , Login.class);
                startActivity(log);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(MainActivity.this , Signup.class);
                startActivity(sign);
            }
        });
    }
}