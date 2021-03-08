package com.cs492.cocktailapp.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs492.cocktailapp.data.CocktailSearchItem;
import com.cs492.cocktailapp.data.CocktailSearchList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailSearchRepository {
    private static final String TAG = CocktailSearchRepository.class.getSimpleName();
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";

    private MutableLiveData<List<CocktailSearchItem>> searchResults;

    private String currentQuery;

    private CocktailService cocktailService;

    public CocktailSearchRepository() {
        this.searchResults = new MutableLiveData<>();
        this.searchResults.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cocktailService = retrofit.create(CocktailService.class);
    }

    public LiveData<List<CocktailSearchItem>> getSearchResults() {
        return this.searchResults;
    }

    public void loadSearchResultsforName(String query) {
        this.currentQuery = query;
        this.searchResults.setValue(null);
        Log.d(TAG, "running new search for query: " + query);

        Call<CocktailSearchList> results = this.cocktailService.searchCocktailByName(query);
        results.enqueue(new Callback<CocktailSearchList>() {
            @Override
            public void onResponse(Call<CocktailSearchList> call, Response<CocktailSearchList> response) {
                if (response.code() == 200) {
                    searchResults.setValue(response.body().getCocktailSearchItems());
                }
            }

            @Override
            public void onFailure(Call<CocktailSearchList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
