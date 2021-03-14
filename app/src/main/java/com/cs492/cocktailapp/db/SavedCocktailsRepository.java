package com.cs492.cocktailapp.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cs492.cocktailapp.data.CocktailRecipe;
import com.cs492.cocktailapp.data.MeasureIngredient;

import java.util.ArrayList;
import java.util.List;


/*
https://dev.to/normanaspx/android-room-how-works-one-to-many-relationship-example-5ad0
 */

public class SavedCocktailsRepository {
    private SavedCocktailsDao savedCocktailsDao;

    public SavedCocktailsRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        this.savedCocktailsDao = database.savedCocktailsDao();
    }


    public void insertCocktail(CocktailRecipe cocktailRecipe) {
        CocktailEntityWithIngredients cocktail = convertRecipeToEntity(cocktailRecipe);
        new insertAsync(this.savedCocktailsDao).execute(cocktail);
    }

    private static class insertAsync extends AsyncTask<CocktailEntityWithIngredients, Void, Void> {
        private SavedCocktailsDao savedCocktailsDaoAsync;

        insertAsync(SavedCocktailsDao savedCocktailsDao) {
            savedCocktailsDaoAsync = savedCocktailsDao;
        }

        @Override
        protected Void doInBackground(CocktailEntityWithIngredients... cocktail) {
            savedCocktailsDaoAsync.insertCocktail(cocktail[0].cocktailEntity);
            savedCocktailsDaoAsync.insertIngredients(cocktail[0].cocktailIngredients);
            return null;
        }
    }

    public void deleteCocktail(CocktailRecipe cocktailRecipe) {
        CocktailEntityWithIngredients cocktail = convertRecipeToEntity(cocktailRecipe);
        AppDatabase.databaseWriteExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        savedCocktailsDao.deleteCocktail(cocktail.cocktailEntity);
                    }
                }
        );
    }

    public LiveData<CocktailEntityWithIngredients> getSavedCocktailById(int drinkId) {
        return savedCocktailsDao.getSavedCocktailById(drinkId);
    }

    // Helper function to convert from CocktailEntityWithIngredients to CocktailRecipe
    public CocktailRecipe convertEntityToRecipe(CocktailEntityWithIngredients cocktail) {
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

    // Helper function to convert CocktailRecipe to CocktailEntityWithIngredients
    public CocktailEntityWithIngredients convertRecipeToEntity(CocktailRecipe cocktail) {
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
        return new CocktailEntityWithIngredients(cocktailEntity, cocktailIngredients);
    }
}
