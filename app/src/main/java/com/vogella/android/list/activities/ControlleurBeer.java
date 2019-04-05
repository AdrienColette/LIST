package com.vogella.android.list.activities;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vogella.android.list.model.Beer;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControlleurBeer {
    private final MainActivity mainActivity;
    private static ControlleurBeer controllerBeer = null;
    public List<Beer> listBeer;
    private SharedPreferences sharedPreferences;

    public ControlleurBeer(MainActivity mainActivity, SharedPreferences sharedPreferences) {
        this.mainActivity = mainActivity;
        this.sharedPreferences = sharedPreferences;
    }

    public void onCreat() {
        if(sharedPreferences.contains("done")){
            mainActivity.showLoader();
            String listB=sharedPreferences.getString("done",null);
            Type listT=new TypeToken<List<Beer>>(){}.getType();
            List<Beer> listBeer=new Gson().fromJson(listB,listT);
            mainActivity.showList(listBeer);
            mainActivity.hideLoader();
        }

        else{
            mainActivity.showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ontariobeerapi.ca/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            BeerApi beerApi = retrofit.create(BeerApi.class);

            Call<List<Beer>> call = beerApi.getListBeer();
            call.enqueue(new Callback<List<Beer>>() {
                @Override
                public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                    listBeer = response.body();
                    mainActivity.showList(listBeer);
                    mainActivity.hideLoader();
                }


                @Override
                public void onFailure(Call<List<Beer>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });
        }
    }
}
