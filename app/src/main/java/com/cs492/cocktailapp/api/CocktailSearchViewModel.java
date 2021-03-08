package com.cs492.cocktailapp.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cs492.cocktailapp.api.CocktailSearchRepository;
import com.cs492.cocktailapp.data.CocktailSearchItem;

import java.util.List;

public class CocktailSearchViewModel extends ViewModel {
    private CocktailSearchRepository cocktailSearchRepository;
    private LiveData<List<CocktailSearchItem>> searchResults;

    public CocktailSearchViewModel() {
        this.cocktailSearchRepository = new CocktailSearchRepository();
        this.searchResults = this.cocktailSearchRepository.getSearchResults();
    }

    public LiveData<List<CocktailSearchItem>> getSearchResults() {
        return this.searchResults;
    }

    public void loadSearchResultsforName(String query) {
        this.cocktailSearchRepository.loadSearchResultsforName(query);
    }
}
