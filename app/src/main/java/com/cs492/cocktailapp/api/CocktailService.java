package com.cs492.cocktailapp.api;

import com.cs492.cocktailapp.data.CocktailList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailService {
    @GET("search.php")
    Call<CocktailList> searchCocktailByName(@Query("s") String query);
}
