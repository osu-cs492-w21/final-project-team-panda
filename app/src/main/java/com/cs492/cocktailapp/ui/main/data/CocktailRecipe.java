package com.cs492.cocktailapp.ui.main.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CocktailRecipe {
    @SerializedName("idDrink")
    private String id;

    @SerializedName("strDrink")
    private String name;

    @SerializedName("strCategory")
    private String category;

    @SerializedName("strIBA")
    private String iba;

    @SerializedName("strGlass")
    private String glass;

    @SerializedName("strInstructions")
    private String instructions;

    @SerializedName("strDrinkThumb")
    private String imageUrl;

    // ingredients
    @SerializedName("strIngredient1")
    private String ingredient1;

    @SerializedName("strIngredient2")
    private String ingredient2;

    @SerializedName("strIngredient3")
    private String ingredient3;

    @SerializedName("strIngredient4")
    private String ingredient4;

    @SerializedName("strIngredient5")
    private String ingredient5;

    @SerializedName("strIngredient6")
    private String ingredient6;

    @SerializedName("strIngredient7")
    private String ingredient7;

    @SerializedName("strIngredient8")
    private String ingredient8;

    @SerializedName("strIngredient9")
    private String ingredient9;

    @SerializedName("strIngredient10")
    private String ingredient10;

    @SerializedName("strIngredient11")
    private String ingredient11;

    @SerializedName("strIngredient12")
    private String ingredient12;

    @SerializedName("strIngredient13")
    private String ingredient13;

    @SerializedName("strIngredient14")
    private String ingredient14;

    @SerializedName("strIngredient15")
    private String ingredient15;

    // measurements
    @SerializedName("strMeasure1")
    private String measure1;

    @SerializedName("strMeasure2")
    private String measure2;

    @SerializedName("strMeasure3")
    private String measure3;

    @SerializedName("strMeasure4")
    private String measure4;

    @SerializedName("strMeasure5")
    private String measure5;

    @SerializedName("strMeasure6")
    private String measure6;

    @SerializedName("strMeasure7")
    private String measure7;

    @SerializedName("strMeasure8")
    private String measure8;

    @SerializedName("strMeasure9")
    private String measure9;

    @SerializedName("strMeasure10")
    private String measure10;

    @SerializedName("strMeasure11")
    private String measure11;

    @SerializedName("strMeasure12")
    private String measure12;

    @SerializedName("strMeasure13")
    private String measure13;

    @SerializedName("strMeasure14")
    private String measure14;

    @SerializedName("strMeasure15")
    private String measure15;
    
    public CocktailRecipe() {
        this.id = null;
        this.name = null;
        this.category = null;
        this.iba = null;
        this.glass = null;
        this.instructions = null;
        this.imageUrl = null;
        
        this.ingredient1 = null;
        this.ingredient2 = null;
        this.ingredient3 = null;
        this.ingredient4 = null;
        this.ingredient5 = null;
        this.ingredient6 = null;
        this.ingredient7 = null;
        this.ingredient8 = null;
        this.ingredient9 = null;
        this.ingredient10 = null;
        this.ingredient11 = null;
        this.ingredient12 = null;
        this.ingredient13 = null;
        this.ingredient14 = null;
        this.ingredient15 = null;

        this.measure1 = null;
        this.measure2 = null;
        this.measure3 = null;
        this.measure4 = null;
        this.measure5 = null;
        this.measure6 = null;
        this.measure7 = null;
        this.measure8 = null;
        this.measure9 = null;
        this.measure10 = null;
        this.measure11 = null;
        this.measure12 = null;
        this.measure13 = null;
        this.measure14 = null;
        this.measure15 = null;
    }

    public String getDrinkId() {
        return this.id;
    }

    public String getDrinkName() {
        return this.name;
    }

    public String getDrinkCategory() {
        return this.category;
    }

    public String getDrinkIba() {
        return this.iba;
    }

    public String getDrinkGlass() {
        return this.glass;
    }

    public String getDrinkInstructions() {
        return this.instructions;
    }

    public String getDrinkImage() {
        return this.imageUrl;
    }

    // returns all drink ingredients
    public ArrayList<String> getIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();

        ingredients.add(this.ingredient1);
        ingredients.add(this.ingredient2);
        ingredients.add(this.ingredient3);
        ingredients.add(this.ingredient4);
        ingredients.add(this.ingredient5);
        ingredients.add(this.ingredient6);
        ingredients.add(this.ingredient7);
        ingredients.add(this.ingredient8);
        ingredients.add(this.ingredient9);
        ingredients.add(this.ingredient10);
        ingredients.add(this.ingredient11);
        ingredients.add(this.ingredient12);
        ingredients.add(this.ingredient13);
        ingredients.add(this.ingredient14);
        ingredients.add(this.ingredient15);

        return ingredients;
    }

    // returns all drink measurements
    public ArrayList<String> getMeasurements() {
        ArrayList<String> measurements = new ArrayList<>();

        measurements.add(this.measure1);
        measurements.add(this.measure2);
        measurements.add(this.measure3);
        measurements.add(this.measure4);
        measurements.add(this.measure5);
        measurements.add(this.measure6);
        measurements.add(this.measure7);
        measurements.add(this.measure8);
        measurements.add(this.measure9);
        measurements.add(this.measure10);
        measurements.add(this.measure11);
        measurements.add(this.measure12);
        measurements.add(this.measure13);
        measurements.add(this.measure14);
        measurements.add(this.measure15);

        return measurements;
    }

}
