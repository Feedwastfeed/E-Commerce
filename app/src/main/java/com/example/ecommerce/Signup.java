package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import Database.*;

public class Signup extends AppCompatActivity {

    EditText name , email , pass ;
    Button con;
    RadioButton male , female;
    String gender;
    EcommerceDatabase Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        con = (Button) findViewById(R.id.continuo);
        name =  (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.emailSignup);
        pass = (EditText) findViewById(R.id.passwordSignup);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        Db = EcommerceDatabase.getInstance(this);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                    pass.getText().toString().isEmpty() ) {
                    Toast.makeText(Signup.this, "Please Fill All Text", Toast.LENGTH_LONG).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    Toast.makeText(Signup.this, "Enter vaild email", Toast.LENGTH_LONG).show();
                }else if(Db.customers_dao().getEmail(email.getText().toString()) > 0 ){
                    Toast.makeText(Signup.this, "this email taken" , Toast.LENGTH_LONG).show();
                }else{
                    if(male.isChecked())
                        gender = "male";
                    else
                        gender = "female";

                    Intent sign = new Intent(Signup.this , Signup_second.class);
                    sign.putExtra("name" ,name.getText().toString());
                    sign.putExtra("email" ,email.getText().toString());
                    sign.putExtra("pass" ,pass.getText().toString());
                    sign.putExtra("gender" ,gender);
                    startActivity(sign);
                }

            }
        });
    }
}