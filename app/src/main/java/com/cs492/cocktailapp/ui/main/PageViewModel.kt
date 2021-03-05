package com.cs492.cocktailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val currentIndex = MutableLiveData<Int>()

    val text: LiveData<String> = Transformations.map(currentIndex) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        currentIndex.value = index
    }
}