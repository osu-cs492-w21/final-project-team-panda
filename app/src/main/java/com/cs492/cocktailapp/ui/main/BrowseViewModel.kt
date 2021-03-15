package com.cs492.cocktailapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cs492.cocktailapp.api.CocktailRepository
import com.cs492.cocktailapp.data.BrowseCategory
import com.cs492.cocktailapp.data.CocktailRecipe
import com.cs492.cocktailapp.data.LoadingStatus
import com.cs492.cocktailapp.db.SavedCocktailsRepository

class BrowseViewModel(application: Application) : AndroidViewModel(application) {

    private val cocktailRepository = CocktailRepository()
    private lateinit var category: BrowseCategory

    private val mutableBrowseItems = MutableLiveData<ArrayList<CocktailRecipe>>()
    private val mutableLoadingStatus = MutableLiveData<LoadingStatus>().apply {
        value = LoadingStatus.Success
    }

    val browseItems: LiveData<ArrayList<CocktailRecipe>>
        get() = mutableBrowseItems

    val loadingStatus: LiveData<LoadingStatus>
        get() = mutableLoadingStatus

    fun setCategory(category: BrowseCategory) {
        this.category = category
        loadBrowseItems()
    }

    fun loadBrowseItems() {
        cocktailRepository.getBrowse(category)
                .thenAccept{ recipes ->
                    mutableLoadingStatus.value = LoadingStatus.Success
                    mutableBrowseItems.value = recipes as ArrayList<CocktailRecipe>
                }.exceptionally {
                    mutableLoadingStatus.value = LoadingStatus.Error
                    null
                }
    }
}