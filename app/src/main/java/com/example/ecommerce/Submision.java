package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Submision extends AppCompatActivity {

    TextView address , totalprice;
    Button confirom , locatio;
    ListView listView;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submision);

        address = (TextView)findViewById(R.id.Address);
        confirom = (Button) findViewById(R.id.confirom);
        locatio = (Button) findViewById(R.id.location);
        totalprice = (TextView) findViewById(R.id.totalprice);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        listView = (ListView) findViewById(R.id.list_confirm);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 );
        listView.setAdapter(arrayAdapter);
        for(int i=0 ; i<cart.cartProductsList.size() ; i++){
            arrayAdapter.add(cart.cartProductsList.get(i).getProName());
        }

        totalprice.setText(String.valueOf(total_cost()));

        confirom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.cartProductsList.clear();
                Intent intent = new Intent(Submision.this , HomePage.class);
                startActivity(intent);
            }
        });

        locatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(Submision.this , Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){
                    showlocation();
                }else{
                    ActivityCompat.requestPermissions(Submision.this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION} ,1 );
                }
            }
        });
    }
    public int total_cost(){
        int sum = 0;
        for(int i=0 ; i<cart.cartProductsList.size() ; i++){
            sum += cart.cartProductsList.get(i).getPrice() * cart.cartProductsList.get(i).getQuantity();
        }
        return sum;
    }

    @SuppressLint("MissingPermission")
    public void showlocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if(location != null){
                    Geocoder geocoder = new Geocoder(Submision.this , Locale.getDefault());
                    List<Address> addressList = null;
                    try {
                        addressList = geocoder.getFromLocation(location.getLatitude() , location.getLongitude() , 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    address.setText(addressList.get(0).getAddressLine(0));
                }else{
                    Toast.makeText(Submision.this , "Location null " , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}