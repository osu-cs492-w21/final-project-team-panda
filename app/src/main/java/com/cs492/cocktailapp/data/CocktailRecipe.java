package com.cs492.cocktailapp.data;

import java.util.ArrayList;

public class CocktailRecipe {
    private final String name;
    private final int id;
    private final String imageUrl;
    private final String instructions;
    private final String glass;
    private final ArrayList<MeasureIngredient> ingredientList;

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
