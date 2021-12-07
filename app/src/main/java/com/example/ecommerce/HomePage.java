package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Database.Categories;
import Database.EcommerceDatabase;
import Database.Products;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView textserach , voiceserach , barcodeserach;
    EditText serach;
    Button crat ;
    ListView listView;
    EcommerceDatabase Db;
    Spinner spinner;
    List<String> catlist;
    ArrayList<String>namelist;
    Button update , sumb;
    private final int VOICE_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Db = EcommerceDatabase.getInstance(this);
        namelist = new ArrayList<String>();
        serach = (EditText) findViewById(R.id.serach);
        update = (Button)findViewById(R.id.update);
        sumb = (Button) findViewById(R.id.submision);
        add_data();
        textserach = (ImageView) findViewById(R.id.text_serach);
        voiceserach = (ImageView) findViewById(R.id.voice_serach);
        barcodeserach = (ImageView) findViewById(R.id.barcode_serach);

        crat = (Button) findViewById(R.id.submision);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        catlist = new ArrayList<>();
        catlist = Db.categories_dao().select_catList();
        catlist.add(0 , "All Categories");
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, catlist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
        listView = (ListView) findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent  =new Intent(HomePage.this , ShowProduct.class);
                intent.putExtra("name" , namelist.get(position));
                startActivity(intent);
            }
        });

        sumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this , Submision.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this , Update.class);
                startActivity(intent);
            }
        });

        barcodeserach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bar = new Intent(HomePage.this , Barcoded.class);
                startActivity(bar);
            }
        });

        textserach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serach.getText().toString().isEmpty()){
                    Toast.makeText(HomePage.this, "Please Fill All Text", Toast.LENGTH_LONG).show();
                }else {
                    listView = (ListView) findViewById(R.id.list_view);
                    Products p = Db.products_dao().select_product_by_name(serach.getText().toString());
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HomePage.this, android.R.layout.simple_list_item_1);
                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.add(p.getProName());
                    namelist.clear();
                    namelist.add(p.getProName());
                }
            }
        });

        voiceserach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, VOICE_CODE);
            }
        });

    }

    public void add_data(){
        //Db = EcommerceDatabase.getInstance(this);
        Categories cat = new Categories();
        cat.setCatName("Clothes");
        Db.categories_dao().insertCategories(cat);
        cat = new Categories();
        cat.setCatName("phone");
        Db.categories_dao().insertCategories(cat);
        cat = new Categories();
        cat.setCatName("Labs");
        Db.categories_dao().insertCategories(cat);
        Products products = new Products();
        products.setProName("T-shirt"); products.setPrice(50); products.setQuantity(20);
        products.setCatID(Db.categories_dao().select_catid("Clothes"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Jeans"); products.setPrice(150); products.setQuantity(15);
        products.setCatID(Db.categories_dao().select_catid("Clothes"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Sweatshirt"); products.setPrice(250); products.setQuantity(10);
        products.setCatID(Db.categories_dao().select_catid("Clothes"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Kochi"); products.setPrice(175); products.setQuantity(12);
        products.setCatID(Db.categories_dao().select_catid("Clothes"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Honor_9_lite"); products.setPrice(2600); products.setQuantity(5);
        products.setCatID(Db.categories_dao().select_catid("phone"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Samsung_j6"); products.setPrice(3250); products.setQuantity(4);
        products.setCatID(Db.categories_dao().select_catid("phone"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Samsung note 10 plus"); products.setPrice(3950); products.setQuantity(3);
        products.setCatID(Db.categories_dao().select_catid("phone"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("lenovo"); products.setPrice(15550); products.setQuantity(3);
        products.setCatID(Db.categories_dao().select_catid("Labs"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Dell"); products.setPrice(17650); products.setQuantity(2);
        products.setCatID(Db.categories_dao().select_catid("Labs"));
        Db.products_dao().insertProducts(products);
        products = new Products();
        products.setProName("Apple"); products.setPrice(32000); products.setQuantity(2);
        products.setCatID(Db.categories_dao().select_catid("Labs"));
        Db.products_dao().insertProducts(products);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_CODE && resultCode == this.RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HomePage.this, android.R.layout.simple_list_item_1);
            listView.setAdapter(arrayAdapter);
            Db = EcommerceDatabase.getInstance(this);
            Toast.makeText(getApplicationContext(), text.get(0) + "", Toast.LENGTH_LONG).show();
            listView = (ListView) findViewById(R.id.list_view);
            Products p = Db.products_dao().select_product_by_name(text.get(0));
            arrayAdapter.add(p.getProName());
            namelist.clear();
            namelist.add(p.getProName());

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        List<Products> productsList ;
        listView = (ListView) findViewById(R.id.list_view);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 );
        if(position == 0){
            productsList = Db.products_dao().select_product();
        }else{
            productsList = Db.products_dao().select_product_id(Db.categories_dao().select_catid(catlist.get(position)));
        }
        listView.setAdapter(arrayAdapter);
        namelist.clear();
        arrayAdapter.clear();
        for(int i=0 ; i<productsList.size() ; i++){
            namelist.add(productsList.get(i).getProName());
            arrayAdapter.add(productsList.get(i).getProName());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}