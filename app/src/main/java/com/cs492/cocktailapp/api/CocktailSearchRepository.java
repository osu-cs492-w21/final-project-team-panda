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
    private String currentQuery;
    private CocktailService cocktailService;

    public CocktailSearchRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cocktailService = retrofit.create(CocktailService.class);
    }


    private List<CocktailRecipe> convertCocktailItemstoRecipes(List<CocktailItem> items){
        List<CocktailRecipe> recipes = new ArrayList<>();

        for (CocktailItem item : items) {
            CocktailRecipe obj = new CocktailRecipe(item.getDrinkName(),Integer.parseInt(item.getDrinkId()), item.getDrinkImage(), item.getDrinkInstructions(), item.getDrinkGlass(), item.getMeasureIngredients());
            recipes.add(obj);
        }

        return recipes;
    }

    public LiveData<List<CocktailRecipe>> loadSearchResultsforName(String query) {
        this.currentQuery = query;
        MutableLiveData<List<CocktailRecipe>> searchResults = new MutableLiveData<>();
        searchResults.setValue(null);
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

        return searchResults;
    }

}
