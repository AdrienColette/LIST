package com.vogella.android.list.activities;

import com.vogella.android.list.model.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerApi {
    @GET("beers")
    Call<List<Beer>> getListBeer();
}
