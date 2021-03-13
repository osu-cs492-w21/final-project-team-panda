package com.cs492.cocktailapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "cocktail-ingredients")
public class CocktailIngredientsEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public int drinkId;
    public String ingredient;
    public String measurement;

    public CocktailIngredientsEntity(int drinkId, String ingredient, String measurement) {
        this.drinkId = drinkId;
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    // getters - required to have one for each member variable
//    public int getId() { return this.id; }
    public int getDrinkId() { return this.drinkId; }
    public String getIngredient() { return this.ingredient; }
    public String getMeasurement() { return this.measurement; }

    // setters - required to have one for each member variable
    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    public void setIngredient(String ingredient) { this.ingredient = ingredient; }
    public void setMeasurement(String measurement) { this.measurement = measurement; }
}
