package com.cs492.cocktailapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cs492.cocktailapp.data.CocktailRecipe
import com.cs492.cocktailapp.db.SavedCocktailsRepository

class SavedViewModel(application: Application) : AndroidViewModel(application) {

    private val savedCocktailsRepository = SavedCocktailsRepository(application)

    val savedCocktails: LiveData<ArrayList<CocktailRecipe>>
        get() = savedCocktailsRepository.getAllSavedCocktails()

}