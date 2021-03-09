package com.cs492.cocktailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs492.cocktailapp.data.BrowseCategory
import com.cs492.cocktailapp.data.CocktailRecipe
import com.cs492.cocktailapp.data.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BrowseViewModel : ViewModel() {

    private val mutableCategory = MutableLiveData<BrowseCategory>()

    private val mutableBrowseItems = MutableLiveData<ArrayList<CocktailRecipe>>().apply {
        value = arrayListOf()
    }

    private val mutableLoadingStatus = MutableLiveData<LoadingStatus>().apply {
        value = LoadingStatus.Error
    }

    val category: BrowseCategory?
        get() = mutableCategory.value

    val browseItems: LiveData<ArrayList<CocktailRecipe>>
        get() = mutableBrowseItems

    val loadingStatus: LiveData<LoadingStatus>
        get() = mutableLoadingStatus

    fun setCategory(category: BrowseCategory) {
        this.mutableCategory.value = category
    }

    fun loadBrowseItems() {
        // TODO(ThuyVy/Julian/Anyone): Hook this up to repository by the category

        // TODO(ThuyVy/Julian/Anyone): Remove; This is all just a mock to help develop the UI
        // - - -
        mutableLoadingStatus.value = LoadingStatus.Loading

        GlobalScope.launch(Dispatchers.IO) {
            Thread.sleep(1000)
            GlobalScope.launch(Dispatchers.Main) {
                mutableLoadingStatus.value = LoadingStatus.Error
            }
        }
        // - - -
    }

}