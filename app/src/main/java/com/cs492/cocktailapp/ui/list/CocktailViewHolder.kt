package com.cs492.cocktailapp.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cs492.cocktailapp.data.CocktailRecipe

abstract class CocktailViewHolder(itemView: View) : ViewHolder(itemView) {
    abstract fun bind(data: CocktailRecipe)
}