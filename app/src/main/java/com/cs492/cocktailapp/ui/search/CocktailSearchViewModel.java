package com.cs492.cocktailapp.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.cs492.cocktailapp.api.CocktailRepository;
import com.cs492.cocktailapp.data.CocktailRecipe;
import com.cs492.cocktailapp.data.LoadingStatus;

import java.util.ArrayList;


public class CocktailSearchViewModel extends ViewModel {
    private final CocktailRepository cocktailSearchRepository;
    private final MutableLiveData<ArrayList<CocktailRecipe>> searchResults;
    private final MutableLiveData<LoadingStatus> loadingStatus;

    public CocktailSearchViewModel(CocktailRepository repository) {
        this.cocktailSearchRepository = repository;
        this.searchResults = new MutableLiveData<>();
        this.loadingStatus = new MutableLiveData<>();
        this.loadingStatus.setValue(LoadingStatus.Success);
    }

    public LiveData<ArrayList<CocktailRecipe>> getSearchResults() {
        return this.searchResults;
    }

    public void loadSearchResultsForName(String query) {
        cocktailSearchRepository.getSearchResults(query)
                .thenAccept(cocktailRecipes -> {
                    this.loadingStatus.setValue(LoadingStatus.Success);
                    this.searchResults.setValue(cocktailRecipes);
                })
                .exceptionally(ex -> {
                    this.loadingStatus.setValue(LoadingStatus.Error);
                    return null;
                });

    }
}
