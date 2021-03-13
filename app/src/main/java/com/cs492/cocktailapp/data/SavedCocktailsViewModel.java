package com.cs492.cocktailapp.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class SavedCocktailsViewModel extends AndroidViewModel {
    private SavedCocktailsRepository savedCocktailsRepository;

    public SavedCocktailsViewModel(Application application) {
        super(application);
        savedCocktailsRepository = new SavedCocktailsRepository(application);
    }

    public void insertCocktail(CocktailRecipe cocktail) {
        SavedCocktail savedCocktail = convertRecipeToEntity(cocktail);
        savedCocktailsRepository.insertCocktail(savedCocktail);
    }

    public void deleteCocktail(CocktailRecipe cocktail) {
        SavedCocktail savedCocktail = convertRecipeToEntity(cocktail);
        savedCocktailsRepository.deleteCocktail(savedCocktail);
    }

    public LiveData<List<SavedCocktail>> getAllSavedCocktails() {
        return savedCocktailsRepository.getAllSavedCocktails();
    }

    public LiveData<SavedCocktail> getSavedCocktailById(int drinkId) {
        return savedCocktailsRepository.getSavedCocktailById(drinkId);
    }


    // Helper function to convert from SavedCocktail entity to CocktailRecipe
    private CocktailRecipe convertEntityToRecipe(SavedCocktail cocktail) {
        CocktailEntity entity = cocktail.getCocktailItem();
        ArrayList<MeasureIngredient> ingredients =
                (ArrayList<MeasureIngredient>) cocktail.getCocktailIngredients();
        CocktailRecipe recipe = new CocktailRecipe(
                entity.getName(),
                entity.getId(),
                entity.getImageUrl(),
                entity.getInstructions(),
                entity.getGlass(),
                ingredients
        );
        return recipe;
    }

    // Helper function to convert CocktailRecipe to SavedCocktail entity
    private SavedCocktail convertRecipeToEntity(CocktailRecipe cocktail) {
        List<MeasureIngredient> cocktailIngredients = new ArrayList<>();
        CocktailEntity cocktailEntity = new CocktailEntity(
                                    cocktail.getDrinkId(),
                                    cocktail.getDrinkName(),
                                    cocktail.getDrinkGlass(),
                                    cocktail.getDrinkInstructions(),
                                    cocktail.getDrinkImage());

        for (MeasureIngredient ingredient:cocktail.getIngredientList()) {
            cocktailIngredients.add(new MeasureIngredient(
                                            cocktail.getDrinkId(),
                                            ingredient.getIngredient(),
                                            ingredient.getMeasurement()));
        }
        return new SavedCocktail(cocktailEntity, cocktailIngredients);
    }
}
