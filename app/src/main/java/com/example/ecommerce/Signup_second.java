package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import Database.Customers;
import Database.EcommerceDatabase;

public class Signup_second extends AppCompatActivity {

    Button sign;
    EditText job;
    CalendarView date;
    Customers customers = new Customers();
    String birthdate = "";
    EcommerceDatabase Db;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_second);

        job = (EditText)findViewById(R.id.job);
        sign = (Button)findViewById(R.id.signupsignup);
        date = (CalendarView) findViewById(R.id.brithdate);
        Db = EcommerceDatabase.getInstance(this);
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                birthdate =String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/"+String.valueOf(year);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(job.getText().toString().isEmpty() || birthdate.isEmpty()){
                    Toast.makeText(Signup_second.this, "Please Fill All Text" , Toast.LENGTH_LONG).show();
                }else {
                    bundle = getIntent().getExtras();
                    customers.setCustName(bundle.getString("name"));
                    customers.setUserName(bundle.getString("email"));
                    customers.setPassword(bundle.getString("pass"));
                    customers.setGender(bundle.getString("gender"));
                    customers.setBirthdate(birthdate);
                    customers.setJob(job.getText().toString());
                    Db.customers_dao().insertCustomer(customers);
                    Toast.makeText(Signup_second.this, "Sign up successfully", Toast.LENGTH_LONG).show();
                    Intent sign = new Intent(Signup_second.this , MainActivity.class);
                    startActivity(sign);
                }

            }
        });

    }
}