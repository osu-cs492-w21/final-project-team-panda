package com.cs492.cocktailapp.ui.main.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CocktailSearchList {
    @SerializedName("drinks")
    private ArrayList<CocktailSearchItem> cocktailSearchItems;

    public CocktailSearchList() {
        this.cocktailSearchItems = null;
    }

    public ArrayList<CocktailSearchItem> getCocktailSearchItems() {
        return this.cocktailSearchItems;
    }
}
