package com.cs492.cocktailapp.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.cs492.cocktailapp.data.MeasureIngredient;

import java.util.List;

/*
This class defines the relationship between the cocktail and cocktail-ingredients entities as
defined here: https://developer.android.com/training/data-storage/room/relationships. This is what
gets returned by the DAO when the database is queried.
 */

public class CocktailEntityWithIngredients {
    @Embedded public CocktailEntity cocktailEntity;
    @Relation(
            parentColumn = "id",
            entityColumn = "drinkId"
    )
    public List<MeasureIngredient> cocktailIngredients;

    public CocktailEntityWithIngredients(CocktailEntity cocktailEntity, List<MeasureIngredient> cocktailIngredients) {
        this.cocktailEntity = cocktailEntity;
        this.cocktailIngredients = cocktailIngredients;
    }

    public CocktailEntity getCocktailItem() { return this.cocktailEntity; }
    public List<MeasureIngredient> getCocktailIngredients() {
        return this.cocktailIngredients;
    }
}
