package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Database.EcommerceDatabase;

public class Login extends AppCompatActivity {

    Button log;
    EditText email , pass;
    TextView forget;
    CheckBox checkBox;
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    EcommerceDatabase Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log = (Button) findViewById(R.id.signin);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        checkBox = (CheckBox)findViewById(R.id.remember);
        Db = EcommerceDatabase.getInstance(this);
        forget = (TextView)findViewById(R.id.forget);
        sh = getSharedPreferences("LoginPrefs" , MODE_PRIVATE);
        editor = sh.edit();

        String mail=sh.getString("email","");
        String passwords=sh.getString("passowrd","");
        email.setText(mail);
        pass.setText(passwords);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forget = new Intent(Login.this , Forgetpassword.class);
                startActivity(forget);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    editor.putString("email",email.getText().toString());
                    editor.putString("passowrd",pass.getText().toString());
                    editor.commit();
                }else{
                    editor.putString("email","");
                    editor.putString("passowrd","");
                    editor.commit();
                }

                if(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Please Fill All Text", Toast.LENGTH_LONG).show();
                }else if(Db.customers_dao().validEmail(email.getText().toString() , pass.getText().toString()) == 0){
                    Toast.makeText(Login.this, "Invalid email Or password", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, "mostafa36ahmed@gamil.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "a7aaaaaaaaaaaaa");

                        startActivity(intent);

                    Intent login = new Intent(Login.this , HomePage.class);
                    startActivity(login);
                }

            }
        });
    }

}