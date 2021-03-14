package com.cs492.cocktailapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CocktailItem {
    @SerializedName("idDrink")
    private final String id;

    @SerializedName("strDrink")
    private final String name;

    @SerializedName("strGlass")
    private final String glass;

    @SerializedName("strInstructions")
    private final String instructions;

    @SerializedName("strDrinkThumb")
    private final String imageUrl;

    // ingredients
    @SerializedName("strIngredient1")
    private final String ingredient1;

    @SerializedName("strIngredient2")
    private final String ingredient2;

    @SerializedName("strIngredient3")
    private final String ingredient3;

    @SerializedName("strIngredient4")
    private final String ingredient4;

    @SerializedName("strIngredient5")
    private final String ingredient5;

    @SerializedName("strIngredient6")
    private final String ingredient6;

    @SerializedName("strIngredient7")
    private final String ingredient7;

    @SerializedName("strIngredient8")
    private final String ingredient8;

    @SerializedName("strIngredient9")
    private final String ingredient9;

    @SerializedName("strIngredient10")
    private final String ingredient10;

    @SerializedName("strIngredient11")
    private final String ingredient11;

    @SerializedName("strIngredient12")
    private final String ingredient12;

    @SerializedName("strIngredient13")
    private final String ingredient13;

    @SerializedName("strIngredient14")
    private final String ingredient14;

    @SerializedName("strIngredient15")
    private final String ingredient15;

    // measurements
    @SerializedName("strMeasure1")
    private final String measure1;

    @SerializedName("strMeasure2")
    private final String measure2;

    @SerializedName("strMeasure3")
    private final String measure3;

    @SerializedName("strMeasure4")
    private final String measure4;

    @SerializedName("strMeasure5")
    private final String measure5;

    @SerializedName("strMeasure6")
    private final String measure6;

    @SerializedName("strMeasure7")
    private final String measure7;

    @SerializedName("strMeasure8")
    private final String measure8;

    @SerializedName("strMeasure9")
    private final String measure9;

    @SerializedName("strMeasure10")
    private final String measure10;

    @SerializedName("strMeasure11")
    private final String measure11;

    @SerializedName("strMeasure12")
    private final String measure12;

    @SerializedName("strMeasure13")
    private final String measure13;

    @SerializedName("strMeasure14")
    private final String measure14;

    @SerializedName("strMeasure15")
    private final String measure15;
    
    public CocktailItem() {
        this.id = null;
        this.name = null;
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

    public String getDrinkGlass() {
        return this.glass;
    }

    public String getDrinkInstructions() {
        return this.instructions;
    }

    public String getDrinkImage() {
        return this.imageUrl;
    }

    public ArrayList<MeasureIngredient> getMeasureIngredients(){
        ArrayList<MeasureIngredient> measuredIngredients = new ArrayList<>();

        MeasureIngredient measureIngredient1 = new MeasureIngredient(this.ingredient1, this.measure1);
        MeasureIngredient measureIngredient2 = new MeasureIngredient(this.ingredient2, this.measure2);
        MeasureIngredient measureIngredient3 = new MeasureIngredient(this.ingredient3, this.measure3);
        MeasureIngredient measureIngredient4 = new MeasureIngredient(this.ingredient4, this.measure4);
        MeasureIngredient measureIngredient5 = new MeasureIngredient(this.ingredient5, this.measure5);
        MeasureIngredient measureIngredient6 = new MeasureIngredient(this.ingredient6, this.measure6);
        MeasureIngredient measureIngredient7 = new MeasureIngredient(this.ingredient7, this.measure7);
        MeasureIngredient measureIngredient8 = new MeasureIngredient(this.ingredient8, this.measure8);
        MeasureIngredient measureIngredient9 = new MeasureIngredient(this.ingredient9, this.measure9);
        MeasureIngredient measureIngredient10 = new MeasureIngredient(this.ingredient10, this.measure10);
        MeasureIngredient measureIngredient11 = new MeasureIngredient(this.ingredient11, this.measure11);
        MeasureIngredient measureIngredient12 = new MeasureIngredient(this.ingredient12, this.measure12);
        MeasureIngredient measureIngredient13 = new MeasureIngredient(this.ingredient13, this.measure13);
        MeasureIngredient measureIngredient14 = new MeasureIngredient(this.ingredient14, this.measure14);
        MeasureIngredient measureIngredient15 = new MeasureIngredient(this.ingredient15, this.measure15);

        if(measureIngredient1.getIngredient() != null && !measureIngredient1.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient1);
        }
        if(measureIngredient2.getIngredient() != null && !measureIngredient2.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient2);
        }
        if(measureIngredient3.getIngredient() != null && !measureIngredient3.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient3);
        }
        if(measureIngredient4.getIngredient() != null && !measureIngredient4.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient4);
        }
        if(measureIngredient5.getIngredient() != null && !measureIngredient5.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient5);
        }
        if(measureIngredient6.getIngredient() != null && !measureIngredient6.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient6);
        }
        if(measureIngredient7.getIngredient() != null && !measureIngredient7.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient7);
        }
        if(measureIngredient8.getIngredient() != null && !measureIngredient8.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient8);
        }
        if(measureIngredient9.getIngredient() != null && !measureIngredient9.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient9);
        }
        if(measureIngredient10.getIngredient() != null && !measureIngredient10.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient10);
        }
        if(measureIngredient11.getIngredient() != null && !measureIngredient11.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient11);
        }
        if(measureIngredient12.getIngredient() != null && !measureIngredient12.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient12);
        }
        if(measureIngredient13.getIngredient() != null && !measureIngredient13.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient13);
        }
        if(measureIngredient14.getIngredient() != null && !measureIngredient14.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient14);
        }
        if(measureIngredient15.getIngredient() != null && !measureIngredient15.getIngredient().equals("")) {
            measuredIngredients.add(measureIngredient15);
        }

        return measuredIngredients;

    }

}
