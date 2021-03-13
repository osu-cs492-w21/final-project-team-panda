package com.cs492.cocktailapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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


    public void insertCocktail(CocktailEntityWithIngredients cocktail) {
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

    public void deleteCocktail(CocktailEntityWithIngredients cocktail) {
        AppDatabase.databaseWriteExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        savedCocktailsDao.deleteCocktail(cocktail.cocktailEntity);
                    }
                }
        );
    }

    public LiveData<List<CocktailEntityWithIngredients>> getAllSavedCocktails() {
        return savedCocktailsDao.getAllSavedCocktails();
    }

    public LiveData<CocktailEntityWithIngredients> getSavedCocktailById(int drinkId) {
        return savedCocktailsDao.getSavedCocktailById(drinkId);
    }
}