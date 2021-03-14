package com.cs492.cocktailapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs492.cocktailapp.api.CocktailRepository
import com.cs492.cocktailapp.data.BrowseCategory
import com.cs492.cocktailapp.data.CocktailRecipe
import com.cs492.cocktailapp.data.LoadingStatus
import com.cs492.cocktailapp.db.CocktailEntityWithIngredients

class BrowseViewModel : ViewModel() {

    private val cocktailRepository = CocktailRepository()
    private val mutableCategory = MutableLiveData<BrowseCategory>()
    private val mutableBrowseItems = MutableLiveData<ArrayList<CocktailRecipe>>()
    private val mutableLoadingStatus = MutableLiveData<LoadingStatus>().apply {
        value = LoadingStatus.Success
    }

    val category: BrowseCategory?
        get() = mutableCategory.value

    val browseItems: LiveData<ArrayList<CocktailRecipe>>
        get() = mutableBrowseItems

    val loadingStatus: LiveData<LoadingStatus>
        get() = mutableLoadingStatus

    fun setCategory(category: BrowseCategory) {
        this.mutableCategory.value = category
        loadBrowseItems()
    }

    fun loadBrowseItems() {
        category?.let {
            cocktailRepository.getBrowse(it)
                    .thenAccept{ recipes ->
                        mutableLoadingStatus.value = LoadingStatus.Success
                        Log.d("BrowseViewModel", recipes.toString())
                        mutableBrowseItems.value = recipes as ArrayList<CocktailRecipe>
                    }.exceptionally {
                        mutableLoadingStatus.value = LoadingStatus.Error
                        null
                    }

        } ?: run {
            Log.w("BrowseViewModel", "Tried to load request without category set")
        }

    }

//    fun getAllSavedCocktails(): LiveData<List<CocktailEntityWithIngredients?>?>? {
//        return savedCocktailsRepository.getAllSavedCocktails()
//    }


}