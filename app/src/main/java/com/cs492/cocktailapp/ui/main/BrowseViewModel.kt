package com.cs492.cocktailapp.ui.main

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cs492.cocktailapp.model.BrowseCategory
import com.cs492.cocktailapp.model.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BrowseViewModel : ViewModel() {

    private val mutableCategory = MutableLiveData<BrowseCategory>()

    private val mutableBrowseItems = MutableLiveData<ArrayList<String?>>().apply {
        value = null
    }

    private val mutableLoadingStatus = MutableLiveData<LoadingStatus>().apply {
        value = LoadingStatus.Error
    }

    val category: BrowseCategory?
        get() = mutableCategory.value

    val browseItems: LiveData<ArrayList<String?>>
        get() = mutableBrowseItems

    val loadingStatus: LiveData<LoadingStatus>
        get() = mutableLoadingStatus

    fun showSavedCocktails(): (() -> Unit)? = null

    fun setCategory(category: BrowseCategory) {
        this.mutableCategory.value = category
    }

    fun loadBrowseItems() {
        // Mock
        mutableLoadingStatus.value = LoadingStatus.Loading

        GlobalScope.launch(Dispatchers.IO) {
            Thread.sleep(1000)
            GlobalScope.launch(Dispatchers.Main) {
                mutableLoadingStatus.value = LoadingStatus.Error
            }
        }

//        Handler(Looper.getMainLooper()).post {
//
//        }

        // TODO(Julian): Call repository with category
    }

}