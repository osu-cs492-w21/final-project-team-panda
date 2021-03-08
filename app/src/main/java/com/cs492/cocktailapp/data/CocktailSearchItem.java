package com.cs492.cocktailapp.data;

import com.google.gson.annotations.SerializedName;

public class CocktailSearchItem {
    @SerializedName("strDrink")
    private String name;

    @SerializedName("strDrinkThumb")
    private String imageUrl;

    @SerializedName("idDrink")
    private String id;

    public CocktailSearchItem() {
        this.name = null;
        this.imageUrl = null;
        this.id = null;
    }

    public String getDrinkName() {
        return this.name;
    }

    public String getDrinkImage() {
        return this.imageUrl;
    }

    public String getDrinkId() {
        return this.id;
    }

}
