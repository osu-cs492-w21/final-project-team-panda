package com.cs492.cocktailapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cs492.cocktailapp.data.MeasureIngredient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Dao
public interface SavedCocktailsDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCocktail(CocktailEntity cocktail);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIngredients(List<MeasureIngredient> ingredients);

    @Delete
    void deleteCocktail(CocktailEntity cocktail);

    @Transaction
    @Query("SELECT * FROM cocktails")
    LiveData<List<CocktailEntityWithIngredients>> getAllSavedCocktails();

    @Transaction
    @Query("SELECT * FROM cocktails WHERE id = :drinkId LIMIT 1")
    LiveData<CocktailEntityWithIngredients> getSavedCocktailById(int drinkId);
}
