package com.cs492.cocktailapp.ui.cocktail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cs492.cocktailapp.data.CocktailRecipe;
import com.cs492.cocktailapp.db.CocktailEntity;
import com.cs492.cocktailapp.db.CocktailEntityWithIngredients;
import com.cs492.cocktailapp.db.SavedCocktailsRepository;

public class DetailedCocktailViewModel extends AndroidViewModel {
    private SavedCocktailsRepository savedCocktailsRepository;

    public DetailedCocktailViewModel(Application application) {
        super(application);
        savedCocktailsRepository = new SavedCocktailsRepository(application);
    }

    public void insertCocktail(CocktailRecipe cocktail) {
        savedCocktailsRepository.insertCocktail(cocktail);
    }

    public void deleteCocktail(CocktailRecipe cocktail) {
        savedCocktailsRepository.deleteCocktail(cocktail);
    }

    public LiveData<CocktailEntityWithIngredients> getSavedCocktailById(int drinkId) {

        return savedCocktailsRepository.getSavedCocktailById(drinkId);
    }
}
