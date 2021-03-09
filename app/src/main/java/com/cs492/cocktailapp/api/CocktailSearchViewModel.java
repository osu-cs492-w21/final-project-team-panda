package com.cs492.cocktailapp.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cs492.cocktailapp.data.CocktailItem;
import com.cs492.cocktailapp.data.CocktailRecipe;

import java.util.List;

public class CocktailSearchViewModel extends ViewModel {
    private CocktailSearchRepository cocktailSearchRepository;

    public CocktailSearchViewModel() {
        this.cocktailSearchRepository = new CocktailSearchRepository();
    }

    public LiveData<List<CocktailRecipe>> loadSearchResultsforName(String query) {
        return cocktailSearchRepository.loadSearchResultsforName(query);
    }
}
