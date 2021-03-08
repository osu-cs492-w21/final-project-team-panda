package com.cs492.cocktailapp.ui.main.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CocktailList {
    @SerializedName("drinks")
    private ArrayList<CocktailRecipe> cocktailRecipes;

    public CocktailList() {
        this.cocktailRecipes = null;
    }

    public ArrayList<CocktailRecipe> getCocktailRecipes() {
        return this.cocktailRecipes;
    }

}
