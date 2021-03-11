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
            long id = savedCocktailsDaoAsync.insertCocktail(cocktail[0].cocktailItem);

            for (CocktailIngredientsEntity ingredient : cocktail[0].cocktailIngredients) {
                ingredient.setDrinkId((int)id);
            }
            savedCocktailsDaoAsync.insertIngredients(cocktail[0].cocktailIngredients);
            return null;
        }
    }

//    public void insertCocktail(CocktailEntity cocktail, List<CocktailIngredientsEntity> ingredients) {
//        AppDatabase.databaseWriteExecutor.execute(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        savedCocktailsDao.insertCocktail(cocktail);
//                        savedCocktailsDao.insertIngredients(ingredients);
//                    }
//                }
//        );
//    }
//
    public void deleteCocktail(CocktailEntity cocktail) {
        AppDatabase.databaseWriteExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        savedCocktailsDao.delete(cocktail);
                    }
                }
        );
    }

    public LiveData<List<SavedCocktail>> getAllSavedCocktails() {
        return savedCocktailsDao.getAllSavedCocktails();
    }
}
