package com.cs492.cocktailapp.data

import com.cs492.cocktailapp.R

enum class BrowseCategory {
    New,
    Popular,
    Random;

    val stringResource: Int
        get() = when (this) {
            New -> R.string.browse_new
            Popular -> R.string.browse_popular
            Random -> R.string.browse_random
        }

}