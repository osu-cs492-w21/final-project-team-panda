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

    val ingredientSummary: String
        get() = {
            when (ingredientList.size) {
                0 -> ""
                1 -> ingredientList[0].ingredient + "."
                2 -> "${ingredientList[0].ingredient} and ${ingredientList[1].ingredient}."
                else -> {
                    val end = ingredientList.size - 1
                    ingredientList.slice(0 until end).joinToString {
                        it.ingredient
                    } + ", and ${ingredientList[end].ingredient}."
                }
            }
        }()

}