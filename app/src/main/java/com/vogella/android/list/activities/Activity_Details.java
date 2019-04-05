package com.vogella.android.list.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vogella.android.list.R;

public class Activity_Details extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        getScdIntent();
    }

    private void getScdIntent(){
        if(getIntent().hasExtra("name")){
            String nameString = getIntent().getStringExtra("name");
            String priceString = getIntent().getStringExtra("price");

            initTxt(nameString,priceString);



        }
    }

    private void initTxt(String name, String price){
        TextView nameTxtV=findViewById(R.id.beer_name);
        nameTxtV.setText(name);

        TextView priceTxtV=findViewById(R.id.beer_price);
        priceTxtV.setText(price + " $");

    }

}
