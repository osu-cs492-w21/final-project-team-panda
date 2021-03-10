package com.cs492.cocktailapp.data;

import java.io.Serializable;

public class MeasureIngredient implements Serializable {
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

    public String getIngredient() {
        return this.ingredient;
    }

    public String getMeasurement() {
        return this.measurement;
    }

}
