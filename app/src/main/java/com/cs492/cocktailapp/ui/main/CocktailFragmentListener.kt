package com.cs492.cocktailapp.ui.main

import com.cs492.cocktailapp.data.CocktailRecipe

/*
 * Interface for an activity that uses the browse fragments (in a pagerview)
 */
interface CocktailFragmentListener {

    fun showSavedCocktails()

    fun navigateTo(cocktailRecipe: CocktailRecipe)

}