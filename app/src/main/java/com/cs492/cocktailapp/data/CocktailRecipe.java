package com.cs492.cocktailapp.data;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class CocktailRecipe implements Serializable {
    private String name;
    private int id;
    private String imageUrl;
    private String instructions;
    private String glass;
    private ArrayList<MeasureIngredient> ingredientList;

    public CocktailRecipe() {
        this.name = null;
        this.id = 0;
        this.instructions = null;
        this.glass = null;
        this.ingredientList = null;
    }

    public CocktailRecipe(String name, int id, String imageUrl,String instructions, String glass, ArrayList<MeasureIngredient> ingredientList) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
        this.instructions = instructions;
        this.glass = glass;
        this.ingredientList = ingredientList;
    }

    public String getDrinkName() {
        return this.name;
    }

    public int getDrinkId() {
        return this.id;
    }

    public String getDrinkImage() {
        return this.imageUrl;
    }

    public String getDrinkInstructions() {
        return this.instructions;
    }

    public String getDrinkGlass() {
        return this.glass;
    }

    public ArrayList<MeasureIngredient> getIngredientList() {
        return this.ingredientList;
    }

}
