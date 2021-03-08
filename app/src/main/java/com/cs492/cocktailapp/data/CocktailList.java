package com.cs492.cocktailapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CocktailList {
    @SerializedName("drinks")
    private ArrayList<CocktailItem> cocktailRecipes;

    public CocktailList() {
        this.cocktailRecipes = null;
    }

    public ArrayList<CocktailItem> getCocktailRecipes() {
        return this.cocktailRecipes;
    }

}
