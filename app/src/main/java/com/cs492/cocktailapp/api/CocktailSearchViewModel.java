package com.cs492.cocktailapp.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cs492.cocktailapp.data.CocktailItem;
import com.cs492.cocktailapp.data.CocktailRecipe;

import java.util.List;

public class CocktailSearchViewModel extends ViewModel {
    private CocktailSearchRepository cocktailSearchRepository;
    private LiveData<List<CocktailRecipe>> searchResults;

    public CocktailSearchViewModel() {
        this.cocktailSearchRepository = new CocktailSearchRepository();
        this.searchResults = this.cocktailSearchRepository.getSearchResults();
    }

    public LiveData<List<CocktailRecipe>> getSearchResults() {
        return this.searchResults;
    }

    public void loadSearchResultsforName(String query) {
        this.cocktailSearchRepository.loadSearchResultsforName(query);
    }
}
