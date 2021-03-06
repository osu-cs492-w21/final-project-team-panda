package com.cs492.cocktailapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BrowseViewModel : ViewModel() {

    private val category = MutableLiveData<BrowseCategory>()

    val text: LiveData<String> = Transformations.map(category) {
        "Hello world from section: $it"
    }

    fun setCategory(category: BrowseCategory) {
        this.category.value = category
    }
}