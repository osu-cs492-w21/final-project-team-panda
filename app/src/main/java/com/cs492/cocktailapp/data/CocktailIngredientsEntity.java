package com.cs492.cocktailapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cocktail-ingredients")
public class CocktailIngredientsEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public int drinkId;
    public int ingredientNumber;
    public String ingredient;
    public String measurement;

    // getters - required to have one for each member variable
    public int getId() { return this.id; }
    public int getDrinkId() { return this.drinkId; }
    public int getIngredientNumber() { return this.ingredientNumber; }
    public String getIngredient() { return this.ingredient; }
    public String getMeasurement() { return this.measurement; }

    // setters - required to have one for each member variable
    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    public void setIngredientNumber(int ingredientNumber) { this.ingredientNumber = ingredientNumber; }
    public void setIngredient(String ingredient) { this.ingredient = ingredient; }
    public void setMeasurement(String measurement) { this.measurement = measurement; }
}
