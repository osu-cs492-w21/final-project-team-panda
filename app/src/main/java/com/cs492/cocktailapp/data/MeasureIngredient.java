package com.cs492.cocktailapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "measure-ingredients",
        primaryKeys = {"drinkId", "ingredient"},
        foreignKeys = {
                @ForeignKey(entity = CocktailEntity.class,
                        parentColumns = "id",
                        childColumns = "drinkId",
                        onDelete = CASCADE)
        },
        indices = {
                @Index("drinkId"),
        })
public class MeasureIngredient implements Serializable {
    @NonNull
    private int drinkId;
    @NonNull
    private String ingredient;
    private String measurement;

    public MeasureIngredient() {
        this.ingredient = null;
        this.measurement = null;
    }

    public MeasureIngredient(String ingredient) {
        this.ingredient = ingredient;
        this.measurement = null;
    }

    public MeasureIngredient(String ingredient, String measurement) {
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    public MeasureIngredient(int drinkId, String ingredient, String measurement) {
        this.drinkId = drinkId;
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    public int getDrinkId() { return this.drinkId; }
    public String getIngredient() {
        return this.ingredient;
    }
    public String getMeasurement() {
        return this.measurement;
    }

    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    public void setIngredient(String ingredient) { this.ingredient = ingredient; }
    public void setMeasurement(String measurement) { this.measurement = measurement; }

}
