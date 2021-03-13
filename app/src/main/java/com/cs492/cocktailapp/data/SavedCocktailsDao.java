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
    void insertIngredients(List<CocktailIngredientsEntity> ingredients);

    @Delete
    void deleteCocktail(CocktailEntity cocktail);

    @Delete
    void deleteIngredients(List<CocktailIngredientsEntity> ingredientsEntities);

    @Query("DELETE FROM `cocktail-ingredients`")
    void deleteIngredients();

    @Transaction
    @Query("SELECT * FROM cocktails")
    public LiveData<List<CocktailEntity>> getAllSavedCocktails();

}
