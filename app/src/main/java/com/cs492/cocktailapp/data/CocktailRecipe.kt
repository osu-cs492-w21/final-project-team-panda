package com.cs492.cocktailapp.data

import java.io.Serializable
import java.util.*

data class CocktailRecipe(
        val drinkName: String,
        val drinkId: Int,
        val drinkImage: String,
        val drinkInstructions: String,
        val drinkGlass: String,
        val ingredientList: ArrayList<MeasureIngredient>
) : Serializable {
