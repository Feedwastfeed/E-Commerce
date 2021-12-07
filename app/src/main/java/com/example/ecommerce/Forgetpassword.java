package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.Customers;
import Database.EcommerceDatabase;

public class Forgetpassword extends AppCompatActivity {

    EcommerceDatabase Db;
    EditText resetemail , resetpass , confirmpass;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        reset = (Button)findViewById(R.id.reset);
        resetemail = (EditText)findViewById(R.id.email_reset);
        resetpass = (EditText)findViewById(R.id.resetpass);
        confirmpass = (EditText)findViewById(R.id.confirmpass);
        Db = EcommerceDatabase.getInstance(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resetemail.getText().toString().isEmpty() || resetpass.getText().toString().isEmpty() ||
                        confirmpass.getText().toString().isEmpty()){
                    Toast.makeText(Forgetpassword.this, "Please Fill All Text" , Toast.LENGTH_LONG).show();
                }else if(Db.customers_dao().getEmail(resetemail.getText().toString()) == 0){
                    Toast.makeText(Forgetpassword.this, "Enter valid email" , Toast.LENGTH_LONG).show();
                }else if(!resetpass.getText().toString().equals(confirmpass.getText().toString())){
                    Toast.makeText(Forgetpassword.this, "reset password and confirm password must same" , Toast.LENGTH_LONG).show();
                }else{
                    Db.customers_dao().updatepass(resetemail.getText().toString() , resetpass.getText().toString());
                    Toast.makeText(Forgetpassword.this, "Reset Password successfully" , Toast.LENGTH_LONG).show();
                    Intent sign = new Intent(Forgetpassword.this , Login.class);
                    startActivity(sign);
                }
            }
        });
    }
}