package com.cs492.cocktailapp.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

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


    public void insertCocktail(SavedCocktail cocktail) {
        new insertAsync(this.savedCocktailsDao).execute(cocktail);
    }

    private static class insertAsync extends AsyncTask<SavedCocktail, Void, Void> {
        private SavedCocktailsDao savedCocktailsDaoAsync;

        insertAsync(SavedCocktailsDao savedCocktailsDao) {
            savedCocktailsDaoAsync = savedCocktailsDao;
        }

        @Override
        protected Void doInBackground(SavedCocktail... cocktail) {
            savedCocktailsDaoAsync.insertCocktail(cocktail[0].cocktailEntity);
            savedCocktailsDaoAsync.insertIngredients(cocktail[0].cocktailIngredients);
            return null;
        }
    }

    public void deleteCocktail(SavedCocktail cocktail) {
        AppDatabase.databaseWriteExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        savedCocktailsDao.deleteCocktail(cocktail.cocktailEntity);
                    }
                }
        );
    }

    public LiveData<List<CocktailEntity>> getAllSavedCocktails() {
        return savedCocktailsDao.getAllSavedCocktails();
    }

    public SavedCocktail getSavedCocktailById(int drinkId) {
        return savedCocktailsDao.getSavedCocktailById(drinkId);
    }
}
