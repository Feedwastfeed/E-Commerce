package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Database.EcommerceDatabase;
import Database.Products;

public class ShowProduct extends AppCompatActivity {

    TextView name , price , count;
    Button blus , minus , back;
    Bundle bundle;
    EcommerceDatabase Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        Db = EcommerceDatabase.getInstance(this);
        name = (TextView) findViewById(R.id.nameofitem);
        price = (TextView) findViewById(R.id.priceofitem);
        count = (TextView) findViewById(R.id.countofitem);
        blus = (Button) findViewById(R.id.blus);
        minus = (Button) findViewById(R.id.minus);
        back = (Button) findViewById(R.id.back);
        bundle = getIntent().getExtras();
        Products p =  Db.products_dao().select_product_by_name(bundle.getString("name"));
        name.setText(p.getProName());
        price.setText(String.valueOf(p.getPrice()));
        count.setText("1");

        blus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p.getQuantity() == Integer.parseInt(count.getText().toString())){
                    Toast.makeText(ShowProduct.this , "this all Quantity Found" , Toast.LENGTH_LONG).show();
                }else{
                    count.setText(String.valueOf(Integer.parseInt( count.getText().toString()) + 1));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.setText(String.valueOf(Math.max(0 , Integer.parseInt(count.getText().toString()) - 1 ) ));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(count.getText().toString()) > 0){
                    p.setQuantity(Integer.parseInt(count.getText().toString()));
                    cart.cartProductsList.add(p);
                }
                Intent intent = new Intent(ShowProduct.this , HomePage.class);
                startActivity(intent);
            }
        });

    }
}