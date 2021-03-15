package com.cs492.cocktailapp.data

import com.cs492.cocktailapp.R

enum class Tab {
    New,
    Popular,
    Random,
    Saved;

    val stringResource: Int
        get() = when (this) {
            New -> R.string.browse_new
            Popular -> R.string.browse_popular
            Random -> R.string.browse_random
            Saved -> R.string.browse_saved
        }

    val isBrowseCategory: Boolean
        get() = this != Saved

    companion object {
        fun fromBrowseCategory(category: BrowseCategory) : Tab {
            return when (category) {
                BrowseCategory.New -> New
                BrowseCategory.Popular -> Popular
                BrowseCategory.Random -> Random
            }
        }
    }
}