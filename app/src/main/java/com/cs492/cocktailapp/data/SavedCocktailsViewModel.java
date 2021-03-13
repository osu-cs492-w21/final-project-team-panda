package com.cs492.cocktailapp.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class SavedCocktailsViewModel extends AndroidViewModel {
    private SavedCocktailsRepository savedCocktailsRepository;
    private CocktailEntity cocktailEntity;
    private SavedCocktail savedCocktail;

    public SavedCocktailsViewModel(Application application) {
        super(application);
        savedCocktailsRepository = new SavedCocktailsRepository(application);
    }

    public void insertCocktail(CocktailRecipe cocktail) {
        convertRecipeToEntity(cocktail);
        savedCocktailsRepository.insertCocktail(this.savedCocktail);
    }

    public void deleteCocktail(CocktailRecipe cocktail) {
        convertRecipeToEntity(cocktail);
        savedCocktailsRepository.deleteCocktail(this.savedCocktail);
    }

    public LiveData<List<CocktailEntity>> getAllSavedCocktails() {
        return savedCocktailsRepository.getAllSavedCocktails();
    }

    private void convertRecipeToEntity(CocktailRecipe cocktail) {
        List<CocktailIngredientsEntity> cocktailIngredients = new ArrayList<>();
        this.cocktailEntity = new CocktailEntity(
                                    cocktail.getDrinkId(),
                                    cocktail.getDrinkName(),
                                    cocktail.getDrinkGlass(),
                                    cocktail.getDrinkInstructions(),
                                    cocktail.getDrinkImage());

        for (MeasureIngredient ingredient:cocktail.getIngredientList()) {
            cocktailIngredients.add(new CocktailIngredientsEntity(
                                            cocktail.getDrinkId(),
                                            ingredient.getIngredient(),
                                            ingredient.getMeasurement()));
        }

        this.savedCocktail = new SavedCocktail(this.cocktailEntity, cocktailIngredients);
    }
}
