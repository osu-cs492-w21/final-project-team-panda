package com.cs492.cocktailapp.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedCocktailsViewModel extends AndroidViewModel {
    private SavedCocktailsRepository savedCocktailsRepository;

    public SavedCocktailsViewModel(Application application) {
        super(application);
        savedCocktailsRepository = new SavedCocktailsRepository(application);
    }

    public void insertCocktail(SavedCocktail cocktail) {
        savedCocktailsRepository.insertCocktail(cocktail);
    }

    public void deleteCocktail(CocktailEntity cocktail) {
        savedCocktailsRepository.deleteCocktail(cocktail);
    }

    public LiveData<List<SavedCocktail>> getAllSavedCocktails() {
        return savedCocktailsRepository.getAllSavedCocktails();
    }
}
