package com.cs492.cocktailapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface SavedCocktailsDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertCocktail(CocktailEntity cocktail);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIngredients(List<MeasureIngredient> ingredients);

    @Delete
    void deleteCocktail(CocktailEntity cocktail);

    @Transaction
    @Query("SELECT * FROM cocktails")
    LiveData<List<SavedCocktail>> getAllSavedCocktails();

    @Transaction
    @Query("SELECT * FROM cocktails WHERE id = :drinkId LIMIT 1")
    LiveData<SavedCocktail> getSavedCocktailById(int drinkId);
}
