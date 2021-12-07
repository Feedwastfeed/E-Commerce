package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Update extends AppCompatActivity {

    TextView count ;
    Button blus , minus , update , back;
    ListView listView;
    ArrayList<String> list;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        list = new ArrayList<String>();
        count = (TextView) findViewById(R.id.count_update);
        blus = (Button) findViewById(R.id.blus_update);
        minus = (Button) findViewById(R.id.minus_update);
        update = (Button) findViewById(R.id.update_back);
        back = (Button) findViewById(R.id.backfromupdate);

        listView = (ListView) findViewById(R.id.recycle_update);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 );
        listView.setAdapter(arrayAdapter);
        for(int i=0 ; i<cart.cartProductsList.size() ; i++){
            list.add(cart.cartProductsList.get(i).getProName());
            arrayAdapter.add(cart.cartProductsList.get(i).getProName());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                num = position;
                count.setText(String.valueOf(cart.cartProductsList.get(position).getQuantity()));
            }
        });

        blus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count.getText().toString().isEmpty()){
                    Toast.makeText(Update.this , "choose item" , Toast.LENGTH_LONG).show();
                }else{
                    count.setText(String.valueOf(Integer.parseInt( count.getText().toString()) + 1));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count.getText().toString().isEmpty()){
                    Toast.makeText(Update.this , "choose item" , Toast.LENGTH_LONG).show();
                }else{
                    count.setText(String.valueOf( Math.max( 0, Integer.parseInt( count.getText().toString()) - 1)));
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!count.getText().toString().isEmpty()){
                    if(Integer.parseInt(count.getText().toString()) == 0){
                        cart.cartProductsList.remove(num);
                    }else{
                        cart.cartProductsList.get(num).setQuantity(Integer.parseInt(count.getText().toString()));
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this , HomePage.class);
                startActivity(intent);
            }
        });
    }
}