package com.vogella.android.list.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vogella.android.list.R;
import com.vogella.android.list.adapters.AdapterBeer;
import com.vogella.android.list.model.Beer;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private AdapterBeer adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ControlleurBeer controller;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        progressBar = findViewById(R.id.loader);
        controller = new ControlleurBeer(this,getSharedPreferences("done", Context.MODE_PRIVATE));
        controller.onCreat();
    }

        public void showLoader(){
            progressBar.setVisibility(View.VISIBLE);
        }
        public void hideLoader(){
            progressBar.setVisibility(View.GONE);
        }

        public void showList(List<Beer> listBeer) {
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new AdapterBeer(listBeer, getApplicationContext(), new OnBeerClick() {
                @Override
                public void onBeerClick(Beer beer) {
                    Toast.makeText(getApplicationContext(),beer.getName(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Activity_Details.class);
                    intent.putExtra("name",beer.getName());
                    intent.putExtra("price",beer.getPrice());
                    MainActivity.this.startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        }
}
