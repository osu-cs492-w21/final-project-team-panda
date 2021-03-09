package com.cs492.cocktailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs492.cocktailapp.data.BrowseCategory
import com.cs492.cocktailapp.data.CocktailRecipe
import com.cs492.cocktailapp.data.LoadingStatus
import com.cs492.cocktailapp.data.MeasureIngredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BrowseViewModel : ViewModel() {

    private val mutableCategory = MutableLiveData<BrowseCategory>()

    private val mutableBrowseItems = MutableLiveData<ArrayList<CocktailRecipe>>().apply {
        value = arrayListOf(
                CocktailRecipe(
                        "Old Fashioned",
                        0,
                        "https://www.thecocktaildb.com/images/media/drink/vrwquq1478252802.jpg",
                        "Put it in a glass, baby!",
                        "Serve in chill glass",
                        arrayListOf(MeasureIngredient("Bourbon", "One bottle")),
                ),
                CocktailRecipe(
                        "Moscow Mule",
                        1,
                        "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
                        "Put it in a glass, baby!",
                        "Serve in copper mug",
                        arrayListOf(MeasureIngredient("Vodka", "Two shots")),
                ),
                CocktailRecipe(
                        "Manhattan",
                        1,
                        "https://www.thecocktaildb.com/images/media/drink/yk70e31606771240.jpg",
                        "Shook not stirren!",
                        "Serve in fancy triangle glass",
                        arrayListOf(MeasureIngredient("IDK", "A lot")),
                ),
                CocktailRecipe(
                        "Margarita",
                        0,
                        "https://www.thecocktaildb.com/images/media/drink/vrwquq1478252802.jpg",
                        "Squeeze the lime!",
                        "Serve in chilled martini glass.",
                        arrayListOf(MeasureIngredient("Tequila", "Ba Bum Ba Dum Ba Dum Dum Dum")),
                ),
        )
    }

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