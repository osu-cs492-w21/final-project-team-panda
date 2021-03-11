package com.cs492.cocktailapp.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/*
This class defines the relationship between the cocktail and cocktail-ingredients entities as
defined here: https://developer.android.com/training/data-storage/room/relationships. This is what
gets returned by the DAO when the database is queried.
 */

public class SavedCocktail {
    @Embedded public CocktailEntity cocktailItem;
    @Relation(
            parentColumn = "id",
            entityColumn = "drinkId"
    )
    public List<CocktailIngredientsEntity> cocktailIngredients;

    // getters
    public CocktailEntity getCocktailItem() { return this.cocktailItem; }
    public List<CocktailIngredientsEntity> getCocktailIngredients() {
        return this.cocktailIngredients;
    }
}
