package com.cs492.cocktailapp.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs492.cocktailapp.data.CocktailItem;
import com.cs492.cocktailapp.data.CocktailList;
import com.cs492.cocktailapp.data.CocktailRecipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailSearchRepository {
    private static final String TAG = CocktailSearchRepository.class.getSimpleName();
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";

    private MutableLiveData<List<CocktailRecipe>> searchResults;

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

    public LiveData<List<CocktailRecipe>> getSearchResults() {
        return this.searchResults;
    }

    private List<CocktailRecipe> convertCocktailItemstoRecipes(List<CocktailItem> items){
        List<CocktailRecipe> recipes = new ArrayList<>();

        for (CocktailItem item : items) {
            CocktailRecipe obj = new CocktailRecipe(item.getDrinkName(),Integer.parseInt(item.getDrinkId()),item.getDrinkInstructions(), item.getDrinkGlass(), item.getMeasureIngredients());
            recipes.add(obj);
        }

        return recipes;
    }

    public void loadSearchResultsforName(String query) {
        this.currentQuery = query;
        this.searchResults.setValue(null);
        Log.d(TAG, "running new search for query: " + query);

        Call<CocktailList> results = this.cocktailService.searchCocktailByName(query);
        results.enqueue(new Callback<CocktailList>() {
            @Override
            public void onResponse(Call<CocktailList> call, Response<CocktailList> response) {
                if (response.code() == 200) {
                    List<CocktailItem> res = response.body().getCocktailRecipes();
                    searchResults.setValue(convertCocktailItemstoRecipes(res));
                }
            }

            @Override
            public void onFailure(Call<CocktailList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
